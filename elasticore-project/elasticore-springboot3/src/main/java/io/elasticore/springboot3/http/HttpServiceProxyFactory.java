package io.elasticore.springboot3.http;

import io.elasticore.runtime.port.ExternalService;
import io.elasticore.runtime.port.HttpAuthProvider;
import io.elasticore.runtime.port.HttpEndpoint;
import io.elasticore.springboot3.bean.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.lang.reflect.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class HttpServiceProxyFactory {


    private final static Logger logger = LoggerFactory.getLogger(HttpServiceProxyFactory.class);

    public static <T> T createService(Class<T> serviceInterface, ExternalService externalService, BeanFactory beanFactory, Environment environment) {
        return (T) Proxy.newProxyInstance(
                serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface},
                new HttpInvocationHandler(serviceInterface, externalService, beanFactory, environment)
        );
    }

    private static class HttpInvocationHandler implements InvocationHandler {

        private final Class<?> serviceInterface;

        private final ExternalService externalService;

        private final BeanFactory beanFactory;

        private final Environment environment;

        private ApplicationContext applicationContext;

        public HttpInvocationHandler(Class<?> serviceInterface, ExternalService externalService, BeanFactory beanFactory,  Environment environment) {
            this.serviceInterface = serviceInterface;
            this.externalService = externalService;
            this.beanFactory = beanFactory;
            this.environment = environment;
        }

        protected ApplicationContext getApplicationContext() {
            if(applicationContext==null) {
                applicationContext = ApplicationContextProvider.getApplicationContext();
            }

            return applicationContext;
        }

        private Object presetMethod(Object proxy, Method method, Object[] args) {
            if ("hashCode".equals(method.getName())) {
                return System.identityHashCode(proxy);
            } else if ("equals".equals(method.getName())) {
                return proxy == args[0];
            } else if ("toString".equals(method.getName())) {
                return "Proxy for " + proxy.getClass().getName();
            }

            return null;
        }

        private HttpMethod getHttpMethod(HttpEndpoint endpoint) {
            String httpMethod = endpoint.method().toUpperCase();
            return HttpMethod.valueOf(httpMethod);
        }

        private List<Map<String, String>> getHeaderMapList() {
            List<Map<String, String>> headerMapList = new ArrayList<>();
            Map<String, String> header = new HashMap<>();
           //header.put("Cookie", "_ga="); // for SFA


            String authKey = environment.getProperty(this.externalService.id() + ".authKey"
                    , this.externalService.authKey());
            if(authKey!=null && authKey.length()>0) {
                header.put("Authorization" ,authKey);
            }

            String apiKey = environment.getProperty(  this.externalService.id() + ".apiKey", "");

            if (apiKey != null && !apiKey.isEmpty()) {
                String authorization = header.put("Authorization", "Bearer " + apiKey);
            }
            headerMapList.add(header);

            return headerMapList;
        }


        private static String encode(String value) {
            try {
                return URLEncoder.encode(value, StandardCharsets.UTF_8.toString())
                        .replace("+", "%20");
            } catch (Exception e) {
                throw new RuntimeException("URL encoding fail", e);
            }
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object preset = presetMethod(proxy, method, args);
            if (preset != null)
                return preset;

            HttpEndpoint endpoint = method.getAnnotation(HttpEndpoint.class);
            HttpMethod httpMethod = getHttpMethod(endpoint);
            String callUrl = endpoint.url();
            String contentType = endpoint.contentType();
            String svrUrl = environment.getProperty(this.externalService.id() + ".url", this.externalService.url());
            List<Map<String, String>> header = getHeaderMapList();

            String keyNames = endpoint.paramNames(); // e.g., "catalogId,modelId"
            String[] paramNames = keyNames != null ? keyNames.split(",") : new String[0];

            Map<String, Object> pathParams = new HashMap<>();
            Map<String, Object> queryParams = new LinkedHashMap<>();
            Object mainReqObject = null;
            HttpAuthProvider authProvider = null;

            if(args!=null) {
                for (int i = 0; i < args.length; i++) {
                    Object arg = args[i];

                    if (arg instanceof HttpAuthProvider) {
                        authProvider = (HttpAuthProvider) arg;
                        continue;
                    }

                    String paramName = (i < paramNames.length) ? paramNames[i].trim() : "arg" + i;

                    if (isPrimitiveOrWrapper(arg)) {

                        Object value = arg;
                        if (arg != null && arg.getClass().isArray()) {
                            int length = Array.getLength(arg);
                            List<String> items = new ArrayList<>();
                            for (int k = 0; k < length; k++) {
                                Object element = Array.get(arg, k);
                                items.add(String.valueOf(element));
                            }
                            value = String.join(",", items);
                        }


                        if (callUrl.contains("{" + paramName + "}")) {
                            pathParams.put(paramName, value);
                        } else {
                            queryParams.put(paramName, value);
                        }
                    } else {
                        mainReqObject = arg;
                    }
                }
            }


            if (authProvider == null && getApplicationContext() != null) {
                String authProviderBeanId = this.externalService.id() + ".httpAuthProvider";
                try {
                    authProvider = (HttpAuthProvider) getApplicationContext().getBean(authProviderBeanId);
                } catch (BeansException | ClassCastException ex) {
                    logger.warn(authProviderBeanId + " is not a valid HttpAuthProvider.");
                }
            }

            for (Map.Entry<String, Object> entry : pathParams.entrySet()) {
                callUrl = callUrl.replace("{" + entry.getKey() + "}", entry.getValue() != null ? entry.getValue().toString() : "");
            }

            callUrl = callUrl.replaceAll("\\{[^/]+}", "");

            if (!queryParams.isEmpty()) {
                String queryString = queryParams.entrySet().stream()
                        .map(e -> e.getKey() + "=" + (e.getValue() != null ? encode(e.getValue().toString()) : ""))
                        .collect(Collectors.joining("&"));

                if (callUrl.contains("?")) {
                    callUrl += "&" + queryString;
                } else {
                    callUrl += "?" + queryString;
                }

                /*
                if (mainReqObject == null && !callUrl.contains("{")) {
                    mainReqObject = queryParams;
                }

                 */
            }

            ParameterizedTypeReference<?> responseType = new ParameterizedTypeReference<Object>() {
                @Override
                public Type getType() {
                    return method.getGenericReturnType();
                }
            };
            logger.debug("HTTP-CALL-PROXY: "+svrUrl+callUrl);

            try {
                Object response = HttpApiClient.exchange(httpMethod, svrUrl, callUrl,
                                mainReqObject, responseType, header, authProvider, contentType)
                        .onErrorMap(WebClientResponseException.class, ex ->
                                new IllegalStateException("HTTP Error: " + ex.getStatusCode(), ex))
                        .block();

                return response;
            }catch (HttpApiClient.WebClientException we) {
                //we.printStackTrace();

                //throw new FileNotFoundException("xx");
                throw new ResponseStatusException(HttpStatusCode.valueOf(we.getStatus()), "API CALL FAIL: " + we.getMessage(), we);
            }
        }

        // Helper: Checks if the object is primitive or a wrapper/string
        private boolean isPrimitiveOrWrapper(Object obj) {
            if (obj == null) return false;
            Class<?> clazz = obj.getClass();

            if (clazz.isArray()) {
                Class<?> componentType = clazz.getComponentType();
                return componentType.isPrimitive()
                        || componentType == String.class
                        || Number.class.isAssignableFrom(componentType)
                        || componentType == Boolean.class
                        || componentType == Character.class;
            }

            return clazz.isPrimitive()
                    || clazz == String.class
                    || Number.class.isAssignableFrom(clazz)
                    || clazz == Boolean.class
                    || clazz == Character.class;
        }



        public Object invoke_old(Object proxy, Method method, Object[] args) throws Throwable {

            Object preset = presetMethod(proxy, method, args);
            if (preset != null)
                return preset;


            Class<?> returnType = method.getReturnType();

            // Construct the ParameterizedTypeReference dynamically
            ParameterizedTypeReference<?> responseType = new ParameterizedTypeReference<Object>() {
                @Override
                public Type getType() {
                    return returnType;
                }
            };

            HttpEndpoint endpoint = method.getAnnotation(HttpEndpoint.class);

            HttpMethod httpMethod = getHttpMethod(endpoint);

            String svrUrl = environment.getProperty(this.externalService.id() + ".url"
                    , this.externalService.url());

            List<Map<String, String>> header = getHeaderMapList();



            String callUrl = endpoint.url();

            HttpAuthProvider authProvider = null;
            List orgList = new ArrayList();
            for (Object arg : args) {
                if (arg instanceof HttpAuthProvider) {
                    authProvider = (HttpAuthProvider) arg;
                } else
                    orgList.add(arg);
            }
            if(authProvider==null && getApplicationContext() !=null) {
                String authProviderBeanId = this.externalService.id()+".httpAuthProvider";
                try {
                    authProvider = (HttpAuthProvider) getApplicationContext().getBean(authProviderBeanId);
                }catch (BeansException re) {

                }catch (ClassCastException cce) {
                    logger.warn(authProviderBeanId+ " is not HttpAuthProvider implements. [ClassCastException]");
                }
            }


            if (orgList.size() > 1) {
                String params = toQueryString(orgList.get(1));
                if (callUrl.indexOf("?") > 0) {
                    callUrl = callUrl + "&" + params;
                } else {
                    callUrl = callUrl + "?" + params;
                }

            }

            Object mainReqObject = null;

            if (orgList.size() > 0) {
                mainReqObject = orgList.get(0);
            }

            String contentType = endpoint.contentType();


            Object shoortenUrl = HttpApiClient.exchange(httpMethod, svrUrl, callUrl,
                            mainReqObject, responseType, header, authProvider ,contentType)

                    .onErrorMap(WebClientResponseException.class, ex ->
                            new IllegalStateException("HTTP Error: " + ex.getStatusCode(), ex)
                    )

                    .block();

            return shoortenUrl;
        }
    }

    public static String toQueryString(Object input) throws Exception {
        return Stream.of(input.getClass().getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .map(field -> {
                    try {
                        String name = field.getName();
                        Object value = field.get(input);
                        if (value != null) {
                            return URLEncoder.encode(name, StandardCharsets.UTF_8.name()) + "=" +
                                    URLEncoder.encode(value.toString(), StandardCharsets.UTF_8.name());
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                })
                .filter(param -> param != null)
                .collect(Collectors.joining("&"));
    }
}