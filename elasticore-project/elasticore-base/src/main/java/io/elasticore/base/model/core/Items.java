package io.elasticore.base.model.core;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ModelComponent;

import java.util.*;

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

    public List<T> getItemList() {
        Object[] preItemArray = this.itemMap.values().toArray();


        List<T> newList = new ArrayList<>();
        for (int i = 0; i < preItemArray.length; i++) {
            newList.add((T) preItemArray[i]);
        }

        return newList;

        //return this.itemMap.values().stream().map(e -> (T) e).toArray();
        //return this.itemMap.values().toArray(new ModelComponent[0]);
    }


}

