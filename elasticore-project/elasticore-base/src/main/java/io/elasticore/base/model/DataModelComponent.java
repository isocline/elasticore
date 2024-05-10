package io.elasticore.base.model;

import io.elasticore.base.model.entity.Field;

public interface DataModelComponent extends ModelComponent {

    MetaInfo getMetaInfo();

    ModelComponentItems<Field> getItems();

}
