package io.elasticore.base.model.core;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.ModelComponentItems;

import java.util.List;

public final class BaseModelComponentItem<T extends ModelComponent> implements ModelComponentItems {
    private final Items<T> items;

    private final List<T> itemList;

    public BaseModelComponentItem(Items<T> items) {
        this.items = items;
        this.itemList = items.getItemList();
    }

    public int size() {
        return this.items.size();
    }

    public T find(ComponentIdentity identity) {
        return this.items.find(identity);
    }

    public T get(int idx) {
        return this.itemList.get(idx);
    }


    private int point = 0;
    @Override
    public boolean hasNext() {

        if(this.items.size()>point){
            return true;
        }

        return false;
    }

    @Override
    public Object next() {
        int p = this.point;
        this.point=this.point+1;
        return this.get(p);
    }
}
