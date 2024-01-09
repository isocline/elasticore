package io.elasticore.base.model.entity;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.core.BaseComponentIdentity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Field implements ModelComponent {

    private ComponentIdentity identity;

    private String name;

    private String type;

    @Override
    public ComponentIdentity getIdentity() {
        return BaseComponentIdentity.newInstance(ComponentType.FIELD, "docmain", this.name);
    }
}
