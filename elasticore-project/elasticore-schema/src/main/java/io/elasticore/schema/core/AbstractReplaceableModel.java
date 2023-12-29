package io.elasticore.schema.core;

import io.elasticore.schema.Replaceable;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class AbstractReplaceableModel<T extends Replaceable<T>> implements Replaceable<T> {

    public final static String TYPE_ENTITY = "entity";
    public final static String TYPE_IO = "io";
    public final static String TYPE_OPERTATION = "op";

    private final String id;

    private String name;
    private String type;
    private String domain;

    private final static Map<String, Replaceable> replaceableMap = new HashMap<>();

    protected AbstractReplaceableModel(String type, String domain, String name) {
        this(type + "://" + domain + "." + name);

        this.domain = domain;
        this.type = type;
        this.name = name;
    }

    private AbstractReplaceableModel(String id) {
        this.id = id;
        replaceableMap.put(id, this);
    }

    @Override
    public T getObject() {
        return getModel(this.id);
    }

    public static <T extends Replaceable<T>> T getModel(String id) {
        return (T) replaceableMap.get(id);
    }
}
