package io.elasticore.base.model.core;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ModelComponent;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Items<T extends ModelComponent> {
    private Map<ComponentIdentity, T> itemMap = new LinkedHashMap<>();

    private Items() {
    }

    public static <T extends ModelComponent> Items<T> newInstance(Class<T> type) {
        return new Items<>();
    }


    public void addItem(T item) {
        itemMap.put(item.getIdentity(), item);
    }


    public int size() {
        return this.itemMap.size();
    }

    public T find(ComponentIdentity identity) {
        return this.itemMap.get(identity);
    }

    public T[] getItemArray() {
        return (T[]) this.itemMap.values().toArray();
    }


}

