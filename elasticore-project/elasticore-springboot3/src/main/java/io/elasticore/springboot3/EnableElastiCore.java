package io.elasticore.springboot3;


import io.elasticore.springboot3.bean.ApplicationContextProvider;
import io.elasticore.springboot3.bean.DbTransactionGateway;
import io.elasticore.springboot3.bean.PortServiceRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;
import io.elasticore.springboot3.bean.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({PortServiceRegistrar.class, DbTransactionGateway.class, ApplicationContextProvider.class})
public @interface EnableElastiCore {
}
