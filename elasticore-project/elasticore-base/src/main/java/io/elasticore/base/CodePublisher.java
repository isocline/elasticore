package io.elasticore.base;

import io.elasticore.base.exeption.ProcessException;
import io.elasticore.base.model.ModelComponent;

public interface CodePublisher {

    void setSrcCodeWriterFactory(SrcCodeWriterFactory srcCodeWriterFactory);

    SrcCodeWriterFactory getSrcCodeWriterFactory();

    ECoreModelContext getECoreModelContext();

    boolean publish(ECoreModelContext ctx) throws ProcessException;

    void errorOnPublish(ModelComponent modelComponent, Throwable e);
}
