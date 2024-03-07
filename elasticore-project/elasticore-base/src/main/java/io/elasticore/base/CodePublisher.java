package io.elasticore.base;

import io.elasticore.base.exeption.ProcessException;

public interface CodePublisher {


    boolean publish(ECoreModelContext ctx) throws ProcessException;

    ECoreModelContext getECoreModelContext();
}
