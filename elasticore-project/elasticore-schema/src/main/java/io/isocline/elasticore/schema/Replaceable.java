package io.isocline.elasticore.schema;

public interface Replaceable<T extends Replaceable<T>> {

    T getObject();
}
