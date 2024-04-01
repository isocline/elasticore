package io.elasticore.base.model.relation;

import io.elasticore.base.model.ModelComponent;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ModelRelationship {

    private String fromName;
    private String toName;
    private RelationType relationType;


    private ModelRelationship(String fromName, String toName, RelationType relationType) {
        this.fromName = fromName;
        this.toName = toName;
        this.relationType = relationType;
    }

    public static ModelRelationship create(String fromName, String toName, RelationType relationType) {
        return new ModelRelationship(fromName, toName, relationType);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelRelationship that = (ModelRelationship) o;
        return Objects.equals(fromName, that.fromName) && Objects.equals(toName, that.toName) && relationType == that.relationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromName, toName, relationType);
    }
}
