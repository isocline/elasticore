package io.elasticore.base.model.entity;


/**
 *
 */


public class TypeInfo {

    private BaseFieldType baseFieldType;

    private String initTypeInfo;

    private String baseTypeName;
    private String typeParameterName;

    private boolean isGenericType = false;


    TypeInfo(String typeInfo) {
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
            typeParameterName = initTypeInfo.substring(p + 1, initTypeInfo.length() - 2);
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


    public String getDefaultTypeName() {
        if(isBaseType() && !isList()) {
            return this.baseFieldType.getName();
        }

        return this.initTypeInfo;
    }

    public boolean isBaseType() {
        if (baseFieldType == BaseFieldType.UNKNOWN) {
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

    public boolean isGenericType() {
        return isGenericType;
    }
}
