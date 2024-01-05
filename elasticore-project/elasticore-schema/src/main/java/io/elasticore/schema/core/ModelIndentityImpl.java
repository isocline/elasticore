package io.elasticore.schema.core;

import io.elasticore.schema.ModelIndentity;

import java.util.Objects;

public class ModelIndentityImpl implements ModelIndentity {

    private String id;


    ModelIndentityImpl(String type, String domain, String name) {
        this.id = type+"://"+domain+"."+name;
    }

    ModelIndentityImpl(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelIndentityImpl that = (ModelIndentityImpl) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String getId() {
        return this.id;
    }
}
