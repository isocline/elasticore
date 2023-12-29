package io.elasticore.schema.io;

import io.elasticore.schema.core.AbstractReplaceableModel;

import java.util.ArrayList;
import java.util.List;

public final class IoModel extends AbstractReplaceableModel<IoModel> {

    IoModel(String domain, String name) {
        super(AbstractReplaceableModel.TYPE_IO, domain, name);
    }

    private final List<Field> fieldList = new ArrayList<>();
}
