package io.elasticore.base;

import io.elasticore.base.exeption.ProcessException;

import java.io.Writer;
import java.util.List;

public interface ECoreModelContext {


    String[] getDomanNames();


    ModelDomain getDomain();

    ModelDomain getDomain(String name);

    boolean publish(CodePublisher publisher) throws ProcessException;


}
