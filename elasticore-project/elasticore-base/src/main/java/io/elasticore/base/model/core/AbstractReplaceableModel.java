package io.elasticore.base.model.core;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.Replaceable;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class AbstractReplaceableModel<T extends Replaceable<T>> implements Replaceable<T> {


    private final ComponentIdentity ComponentIdentity;

    private final static Map<ComponentIdentity, Replaceable> replaceableMap = new HashMap<>();

    protected AbstractReplaceableModel(ComponentIdentity ComponentIdentity) {
        this.ComponentIdentity = ComponentIdentity;
        replaceableMap.put(this.ComponentIdentity, this);
    }


    @Override
    public T getObject() {
        return getModel(this.ComponentIdentity);
    }

    public static <T extends Replaceable<T>> T getModel(ComponentIdentity id) {
        return (T) replaceableMap.get(id);
    }

    @Override
    public ComponentIdentity getIdentity() {
        return ComponentIdentity;
    }
}
