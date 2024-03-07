package io.elasticore.base.model.repo;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.core.BaseComponentIdentity;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Annotation;
import io.elasticore.base.model.entity.Field;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Method implements ModelComponent {

    private ComponentIdentity identity;

    private String name;

    private String type;

    private String description;

    private boolean isNative;

    private String query;

    public Items<Field> params;

    public String returnType;


    @Override
    public ComponentIdentity getIdentity() {
        return BaseComponentIdentity.create(ComponentType.METHOD, this.name);
    }


}
