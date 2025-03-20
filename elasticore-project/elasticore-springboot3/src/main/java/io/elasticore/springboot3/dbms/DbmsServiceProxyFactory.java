package io.elasticore.springboot3.dbms;

import io.elasticore.runtime.port.DbmsService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
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
                    applicationContext = beanFactory.getBean(ApplicationContext.class);
                }catch (Throwable e) {
                    e.printStackTrace();
                }
            }

            return applicationContext;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }


        public Object invoke2(Object proxy, Method method, Object[] args) throws Throwable {

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

                if (firstParamType.isPrimitive()) {
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
                    return executor.executeUpdate(externalService, methodName, input );
                }

            } else if (Page.class.isAssignableFrom(returnType)) {
                qeuryReturnType = extractGenericReturnType(method.getGenericReturnType());
                return executor.inqueryPage(externalService, methodName, input,qeuryReturnType, (Pageable) input);
            }

            return executor.inqueryList(externalService, methodName, input,returnType );

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
