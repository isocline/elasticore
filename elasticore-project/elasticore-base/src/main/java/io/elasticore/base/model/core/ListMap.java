package io.elasticore.base.model.core;

import java.util.*;

public class ListMap<K, V> extends LinkedHashMap<K,V>{

    private List<V> list;

    public ListMap() {
        super();
    }

    @Override
    public V put(K key, V value) {
        list = null;
        return super.put(key,value);
    }

    public V remove(Object key) {
        list = null;
        return super.remove(key);
    }


    public List<V> getList() {
        if(this.list ==null) {
            Collection<V> values = this.values();
            this.list = new ArrayList<>(values);
        }

        return this.list;
    }

}
