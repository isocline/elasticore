package io.elasticore.runtime.port;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define an external service.
 * This annotation is applied at the class level and specifies the service type
 * and a unique identifier for the service.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExternalService {

    /**
     * Specifies the type of the external service protocol.
     *
     * @return the protocole
     */
    String protocol();

    /**
     * Specifies the unique identifier for this external service.
     *
     * @return the unique service identifier
     */
    String id();


    String url();

}
