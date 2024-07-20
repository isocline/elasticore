package io.elasticore.base.model.relation;

import io.elasticore.base.model.ModelComponent;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ModelRelationship {

    private String fromName;
    private String toName;
    private RelationType relationType;

    private String relationName;


    private ModelRelationship(String fromName, String toName, RelationType relationType ,String relationName) {

        if(fromName==null || fromName.trim().isEmpty()) {
            throw new IllegalArgumentException("fromName is null");
        }
        if(toName==null || toName.trim().isEmpty()) {
            throw new IllegalArgumentException("toName is null");
        }

        this.fromName = fromName.trim();
        this.toName = toName.trim();
        this.relationType = relationType;
        this.relationName = relationName;
    }

    public static ModelRelationship create(String fromName, String toName, RelationType relationType) {
        return new ModelRelationship(fromName, toName, relationType, null);
    }

    public static ModelRelationship create(String fromName, String toName, RelationType relationType,String relationName) {
        return new ModelRelationship(fromName, toName, relationType, relationName);
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
