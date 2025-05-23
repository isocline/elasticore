package io.elasticore.schema;

public interface Replaceable<T extends Replaceable<T>> {

    String TYPE_ENTITY = "entity";
    String TYPE_IO = "io";
    String TYPE_OPERTATION = "op";
    String TYPE_REPOSITORY = "repo";
    String TYPE_SERVICE = "service";

    T getObject();

    ModelIndentity getIdentity();
}
