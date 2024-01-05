package io.elasticore.base;

public interface ECoreModelContext {

    ModelDomain getDomain();

    ModelDomain getDomain(String name);

    boolean publish(CodePublisher publisher);

}
