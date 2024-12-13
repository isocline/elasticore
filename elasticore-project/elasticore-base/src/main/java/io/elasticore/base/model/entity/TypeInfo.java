package io.elasticore.base.model.entity;


import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.model.DataModelComponent;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.BaseECoreModelContext;

import java.util.Map;

/**
 *
 */


public class TypeInfo {

    private BaseFieldType baseFieldType;

    private String initTypeInfo;

    private String baseTypeName;
    private String typeParameterName;

    private boolean isGenericType = false;

    private Map<String, Annotation> annotationMap;

    TypeInfo(String typeInfo, Map<String, Annotation> annotationMap) {
        this.annotationMap = annotationMap;
        if (typeInfo == null || typeInfo.length() < 2)
            throw new IllegalArgumentException("type info is not normal. [" + typeInfo + "]");

        this.initTypeInfo = typeInfo;
        loadInfo();
    }

    /**
     *
     */
    private void loadInfo() {


        int p = initTypeInfo.indexOf("<");
        if (p > 0) {
            isGenericType = true;
            baseTypeName = initTypeInfo.substring(0, p);
            typeParameterName = initTypeInfo.substring(p + 1, initTypeInfo.length() - 1);

        } else
            baseTypeName = initTypeInfo;


        baseFieldType = BaseFieldType.findByName(baseTypeName);
    }


    public boolean isStringType() {
        if(baseFieldType==BaseFieldType.STRING)
            return true;

        return false;
    }

    public boolean isList() {
        if (baseFieldType == BaseFieldType.LIST)
            return true;
        return false;
    }

    public String getCoreItemType() {
        if(isGenericType()) {
            return getTypeParameterName();
        }

        return getDefaultTypeName();
    }


    public String getDefaultTypeName() {
        if(isBaseType() && !isList()) {

            if(annotationMap!=null && annotationMap.containsKey("id")) {
                return this.baseFieldType.getWrapperClassName();
            }

            return this.baseFieldType.getWrapperClassName();
        }

        //if(this.initTypeInfo.indexOf(":")>0)
        {
            // external domain type
            ECoreModelContext context = BaseECoreModelContext.getContext();
            if(context!=null) {
                DataModelComponent modelComponent = context.findModelComponent(this.initTypeInfo);
                if(modelComponent!=null) {
                    return modelComponent.getFullName();
                }
            }
        }

        return this.initTypeInfo;
    }

    public boolean isBaseType() {
        if (baseFieldType == BaseFieldType.UNKNOWN) {
            return false;
        }
        if (baseFieldType == BaseFieldType.LIST) {
            return false;
        }
        return true;
    }

    public BaseFieldType getBaseFieldType() {
        return this.baseFieldType;
    }


    public String getInitTypeInfo() {
        return initTypeInfo;
    }

    public String getBaseTypeName() {
        return baseTypeName;
    }

    public String getTypeParameterName() {
        return typeParameterName;
    }

    public boolean isTypeParameterBaseType() {
        if(typeParameterName==null || typeParameterName.isEmpty())
            return false;
        String typeNm = typeParameterName.toLowerCase();
        if("string".equals(typeNm) || "integer".equals(typeNm) || "long".equals(typeNm)) {
            return true;
        }
        return false;
    }

    public boolean isGenericType() {
        return isGenericType;
    }
}
