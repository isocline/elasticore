package io.elasticore.springboot3.dbms;

import io.elasticore.runtime.port.DbmsService;
import io.elasticore.springboot3.bean.ApplicationContextProvider;
import io.elasticore.springboot3.util.ReflectUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class DbmsServiceProxyFactory {

    public static <T> T createService(Class<T> serviceInterface, DbmsService dbmsPortService, BeanFactory beanFactory, Environment environment) {

        return (T) Proxy.newProxyInstance(
                serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface},
                new DbmsInvocationHandler(serviceInterface, dbmsPortService, beanFactory, environment)
        );

    }


    /**
     *
     */
    private static class DbmsInvocationHandler implements InvocationHandler {

        private final Class<?> serviceInterface;

        private final DbmsService externalService;

        private final BeanFactory beanFactory;

        private final Environment environment;

        private ApplicationContext applicationContext;

        public DbmsInvocationHandler(Class<?> serviceInterface, DbmsService externalService, BeanFactory beanFactory, Environment environment) {
            this.serviceInterface = serviceInterface;
            this.externalService = externalService;
            this.beanFactory = beanFactory;
            this.environment = environment;
        }

        protected ApplicationContext getApplicationContext() {
            if(applicationContext==null) {
                try {
                    applicationContext = ApplicationContextProvider.getApplicationContext();
                }catch (Throwable e) {
                    e.printStackTrace();
                }
            }

            return applicationContext;
        }

        private Object presetMethod(Object proxy, Method method,  Object[] args) {
            if ("hashCode".equals(method.getName())) {
                return System.identityHashCode(proxy);
            } else if ("equals".equals(method.getName())) {
                return proxy == args[0];
            } else if ("toString".equals(method.getName())) {
                return "Proxy for " + proxy.getClass().getName();
            }

            return null;
        }

        protected Pageable getPageableObject(Object input) {
            Pageable pageable = null;

            if (input instanceof Pageable) {
                return (Pageable) input;
            }

            int defaultPage = 0;
            int defaultPageSize = 50;

            for (Method method : input.getClass().getMethods()) {
                if ((method.getName().equals("getPageable") || method.getName().equals("pageable"))
                        && Pageable.class.isAssignableFrom(method.getReturnType())
                        && method.getParameterCount() == 0) {
                    try {
                        return (Pageable) method.invoke(input);
                    } catch (Exception e) {

                    }
                }
            }

            if (input instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) input;
                Object pageVal = map.get("pageNumber");
                if(pageVal==null)
                    pageVal = map.get("page");

                Object sizeVal = map.get("sizeVal");
                if(sizeVal==null)
                    sizeVal = map.get("size");

                if (pageVal instanceof Number && sizeVal instanceof Number) {
                    return PageRequest.of(((Number) pageVal).intValue(), ((Number) sizeVal).intValue());
                }
            }else {
                try {
                    Class c = input.getClass();
                    Field pageField = ReflectUtils.getField(c,"pageNumber");
                    if(pageField==null)
                        pageField = ReflectUtils.getField(c,"page");

                    Object pageVal = pageField.get(input);
                    if (pageVal instanceof Integer)
                        defaultPage= (Integer) pageVal;


                    Field sizeField = ReflectUtils.getField(c,"sizeVal");
                    if(sizeField==null)
                        sizeField = ReflectUtils.getField(c,"size");

                    Object sizeVal = sizeField.get(input);

                    if (sizeVal instanceof Integer) {
                        defaultPageSize= (Integer) sizeVal;
                    }
                } catch (NullPointerException | IllegalAccessException ignored) {

                }
            }

            return PageRequest.of(defaultPage, defaultPageSize);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Object preset = presetMethod(proxy, method, args);
            if (preset != null)
                return preset;

            //String processId, I input, Class<O> outputType, String dataSource)

            if(getApplicationContext()==null)
                return null;

            DbmsSqlExecutor executor = getApplicationContext().getBean(DbmsSqlExecutor.class);

            String processId = externalService.id() + "." + method.getName();
            String datasource = externalService.datasource();

            String methodName = method.getName();



            Parameter[] parameters = method.getParameters();

            Object input = null;

            boolean isSelfObj = true;
            if(parameters.length==1) {
                Class<?> firstParamType = parameters[0].getType();

                if (firstParamType.isPrimitive() || firstParamType == String.class) {
                    isSelfObj = false;
                }
            }
            else{
                isSelfObj = false;
            }
            if(!isSelfObj)
            {
                Map<String, Object> paramMap = new HashMap<>();

                for (int i = 0; i < parameters.length; i++) {
                    paramMap.put(parameters[i].getName(), args[i]);
                }
                input = paramMap;
            }
            else {
                input = args[0];
            }


            Class<?> returnType = method.getReturnType();
            Class<?> qeuryReturnType = null;

            if (List.class.isAssignableFrom(returnType)) {
                qeuryReturnType = extractGenericReturnType(method.getGenericReturnType());
                return executor.inqueryList(externalService, methodName, input,qeuryReturnType );
            } else if (returnType == Integer.class || returnType == int.class) {
                String lowerCaseMethodNm = methodName.toLowerCase();
                if(lowerCaseMethodNm.indexOf("update")>=0
                        || lowerCaseMethodNm.indexOf("delete")>=0
                        || lowerCaseMethodNm.indexOf("insert")>=0
                ) {
                    //return executor.executeUpdate(externalService, methodName, input );
                }

            } else if (Page.class.isAssignableFrom(returnType)) {
                qeuryReturnType = extractGenericReturnType(method.getGenericReturnType());
                Pageable pageable = getPageableObject(input);
                return executor.inqueryPage(externalService, methodName, input,qeuryReturnType, pageable);
            }

            return executor.inqueryFirst(externalService, methodName, input,returnType );

        }

        /**
         * Extracts the first generic type parameter from a given Type.
         * Supports List<T>, Set<T>, Optional<T>, and other parameterized types.
         *
         * @param genericType The Type object to analyze.
         * @return The extracted generic type (Class<?>), or null if no valid generic type is found.
         */
        public static Class<?> extractGenericReturnType(Type genericType) {
            if (genericType instanceof ParameterizedType) {
                ParameterizedType paramType = (ParameterizedType) genericType;
                Type[] typeArgs = paramType.getActualTypeArguments();

                if (typeArgs.length > 0 && typeArgs[0] instanceof Class<?>) {
                    return (Class<?>) typeArgs[0]; // Extracts the generic type (e.g., TestDTO)
                } else {
                    System.out.println("[LOG] Generic type is not a concrete class: " + typeArgs[0].getTypeName());
                }
            }
            return null; // No generic type found
        }


    }
}
