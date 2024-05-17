package io.elasticore.base.model.entity;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.BaseComponentIdentity;
import io.elasticore.base.util.StringUtils;
import lombok.Builder;
import lombok.Getter;

import java.util.Locale;
import java.util.Map;

@Getter
public class Field implements ModelComponent {


    private ComponentIdentity identity;

    private String name;

    private String type;

    private TypeInfo typeInfo;

    private String description;

    @Builder.Default
    private boolean nullable = true;


    @Builder(builderMethodName = "builder")
    private Field(String name, String type, boolean isPrimaryKey, boolean unique, Map<String, Annotation> annotationMap) {
        this.name = name;
        this.type = type;
        this.isPrimaryKey = isPrimaryKey;
        this.unique = unique;
        this.annotationMap = annotationMap;
    }

    public int getLength() {
        Annotation annotation = getAnnotation("length");
        if(annotation!=null) {
            return Integer.parseInt(annotation.getValue());
        }

        return -1;
    }

    public String getDbColumnName() {
        Annotation annotation = getAnnotation("col");
        if(annotation!=null) {
            return annotation.getValue();
        }

        return StringUtils.camelToSnake(name);
    }



    @Builder.Default
    private boolean isPrimaryKey = false;

    private String generationType;

    private String columnDefinition;

    @Builder.Default
    private boolean unique = false;

    private Map<String, Annotation> annotationMap;



    @Override
    public ComponentIdentity getIdentity() {
        return BaseComponentIdentity.create(ComponentType.FIELD, "field", this.name);
    }



    public Annotation getAnnotation(String name) {
        if (annotationMap == null) {
            return null;
        }

        return annotationMap.get(name);
    }

    public boolean hasAnnotation(String name) {
        if (annotationMap == null || name ==null) {
            return false;
        }
        String key = name.toLowerCase(Locale.ROOT);
        return annotationMap.containsKey(key);
    }


    public TypeInfo getTypeInfo() {
        if (this.typeInfo == null) {
            this.typeInfo = new TypeInfo(this.type ,annotationMap);
        }
        return this.typeInfo;
    }


    private String getType() {
        return this.type;
    }

}
