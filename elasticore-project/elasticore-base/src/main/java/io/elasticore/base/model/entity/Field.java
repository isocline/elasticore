package io.elasticore.base.model.entity;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.core.BaseComponentIdentity;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class Field implements ModelComponent {

    private ComponentIdentity identity;

    private String name;

    private String type;


    private String description;

    @Builder.Default
    private boolean nullable = true;

    private int length;

    // javax.persistence.EnumType
    private String enumType;

    @Builder.Default
    private boolean isPrimaryKey = false;

    private String generationType;

    private String columnDefinition;

    @Builder.Default
    private boolean unique = false;



    @Override
    public ComponentIdentity getIdentity() {
        return BaseComponentIdentity.create(ComponentType.FIELD, "field", this.name);
    }

    private Map<String, Annotation> annotationMap;

    public Annotation getAnnotation(String name) {
        if(annotationMap==null) {
            return null;
        }

        return annotationMap.get(name);
    }


}
