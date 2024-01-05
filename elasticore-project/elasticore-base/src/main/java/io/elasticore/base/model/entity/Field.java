package io.elasticore.base.model.entity;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ModelComponent;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Field implements ModelComponent {

    private String name;

    private String type;

    @Override
    public ComponentIdentity getIdentity() {
        return null;
    }
}
