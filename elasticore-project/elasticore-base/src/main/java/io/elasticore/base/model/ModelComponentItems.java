package io.elasticore.base.model;

public interface ModelComponentItems<T> {
    
    int size();

    T find(ComponentIdentity identity);

    T get(int idx);
}
