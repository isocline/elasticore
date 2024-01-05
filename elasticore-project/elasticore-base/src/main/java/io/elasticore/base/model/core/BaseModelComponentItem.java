package io.elasticore.base.model.core;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.ModelComponentItems;

public final class BaseModelComponentItem<T extends ModelComponent> implements ModelComponentItems {
    private final Items<T> items;

    private final T[] itemArray;

    public BaseModelComponentItem(Items<T> items) {
        this.items = items;
        this.itemArray = items.getItemArray();
    }

    public int size() {
        return this.items.size();
    }

    public T find(ComponentIdentity identity) {
        return this.items.find(identity);
    }

    public T get(int idx) {
        return this.itemArray[idx];
    }


}
