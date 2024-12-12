package io.elasticore.base.model;

import io.elasticore.base.model.entity.Field;

import java.util.List;

public interface ShadowModel {

    String getParentName();

    String getName();

    List<Field> listFields();

    Field getField(String name);

    void setECoreModel(ECoreModel eCoreModel);

    boolean hasField(Field feild);

    boolean hasField(String fieldName);
}
