package io.elasticore.schema.entity;

import io.elasticore.schema.core.AbstractReplaceableModel;

import java.util.ArrayList;
import java.util.List;

public class EntityModel extends AbstractReplaceableModel<EntityModel> {

    private final List<Column> columnList = new ArrayList<>();


    EntityModel(String domain, String name) {
        super(AbstractReplaceableModel.TYPE_ENTITY, domain, name);
    }


    void addColumn(Column column) {
        this.columnList.add(column);
    }

    public int getColumnSize() {
        return this.columnList.size();
    }

    public Column getColumnByIndex(int idx) {
        return this.columnList.get(idx);
    }

    @Override
    public EntityModel getObject() {
        return null;
    }
}
