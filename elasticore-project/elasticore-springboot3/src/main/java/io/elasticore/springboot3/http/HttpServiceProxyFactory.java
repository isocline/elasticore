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
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            header.put("Cookie", "_ga="); // for SFA

            String apiKey = environment.getProperty("external.services." + this.externalService.id() + ".apiKey", "");

            if (apiKey != null && !apiKey.isEmpty()) {

                header.put("Authorization", "Bearer " + apiKey);
                headerMapList.add(header);
            }

            return headerMapList;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

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