package io.elasticore.springboot3.dbms;

import io.elasticore.runtime.port.DbmsService;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

public class DbmsServiceProxyFactory {

    public static <T> T createService(Class<T> serviceInterface, DbmsService dbmsPortService, ApplicationContext applicationContext, Environment environment) {
        return null;
    }
}
