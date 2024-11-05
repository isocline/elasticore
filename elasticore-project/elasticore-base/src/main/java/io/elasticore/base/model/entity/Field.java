package io.elasticore.base.model.entity;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.BaseComponentIdentity;
import io.elasticore.base.util.StringUtils;
import lombok.Builder;
import lombok.Getter;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;

@Getter
public class Field implements ModelComponent {


    private ComponentIdentity identity;

    private String name;

    private String type;

    private TypeInfo typeInfo;

    private String description;

    private MetaInfo parentMetaInfo;

    @Builder.Default
    private boolean nullable = true;


    @Builder(builderMethodName = "builder")
    private Field(String name, String type, boolean isPrimaryKey, boolean unique, String description, Map<String, Annotation> annotationMap ,MetaInfo parentMetaInfo) {
        this.name = name;
        this.type = type;
        this.isPrimaryKey = isPrimaryKey;
        this.unique = unique;
        this.description = description;
        this.annotationMap = annotationMap;
        this.parentMetaInfo = parentMetaInfo;
    }

    public int getLength() {
        String lenTxt = getAnnotationValue(EntityAnnotation.LENGTH);
        if(lenTxt!=null)
            return Integer.parseInt(lenTxt);

        return -1;
    }

    public String getDbColumnName() {

        if(!this.typeInfo.isBaseType())
            return null;

        String dbColumnName = getAnnotationValue(EntityAnnotation.COLUMN_NAME);
        if(dbColumnName!=null)
            return dbColumnName;

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
        // TODO
        return BaseComponentIdentity.create(ComponentType.FIELD, null, this.name);
    }



    public Annotation getAnnotation(String name) {
        if (annotationMap == null) {
            return null;
        }

        return annotationMap.get(name);
    }

    public String getAnnotationValue(String... names) {
        for(String name: names) {
            // searchable.entity ->  @Searchable(entity=Customer)
            String[] nmItems = name.split("\\.");
            String propertyName = null;
            if(nmItems.length==2){
                name = nmItems[0];
                propertyName = nmItems[1];
            }

            Annotation an = getAnnotation(name);
            if(an!=null) {
                if(propertyName!=null) {
                    Properties props = an.getProperties();
                    if(props!=null) {
                        String val = props.getProperty(propertyName);
                        if(val!=null) return val;
                    }
                }else {
                    String val = an.getValue();
                    if(val !=null) return val;
                }
            }
        }

        return null;
    }



    public boolean hasAnnotation(String... names) {
        if (annotationMap == null || names ==null) {
            return false;
        }
        for (String name : names) {
            if (name == null) {
                continue;
            }
            String key = name.toLowerCase(Locale.ROOT);
            if(annotationMap.containsKey(key))
                return true;
        }
        return false;
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
