package io.elasticore.schema.core;

import io.elasticore.schema.ModelIndentity;
import io.elasticore.schema.Replaceable;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class AbstractReplaceableModel<T extends Replaceable<T>> implements Replaceable<T> {




    private String name;
    private String type;
    private String domain;

    private ModelIndentity modelIndentity;

    private final static Map<ModelIndentity, Replaceable> replaceableMap = new HashMap<>();

    protected AbstractReplaceableModel(String type, String domain, String name) {
        this(type + "://" + domain + "." + name);

        this.domain = domain;
        this.type = type;
        this.name = name;
    }

    private AbstractReplaceableModel(String id) {
        this.modelIndentity = new ModelIndentityImpl(id);
        replaceableMap.put(this.modelIndentity , this);
    }

    @Override
    public T getObject() {
        return getModel(this.modelIndentity.getId());
    }

    public static <T extends Replaceable<T>> T getModel(String id) {
        return (T) replaceableMap.get(new ModelIndentityImpl(id));
    }

    @Override
    public ModelIndentity getIdentity() {
        return null;
    }
}
