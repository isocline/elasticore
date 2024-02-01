package io.elasticore.base.model.enums;


import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.core.BaseComponentIdentity;
import lombok.Builder;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
public class EnumConstant implements ModelComponent {


    private ComponentIdentity identity;


    private String name;


    private List<ConstructParam> constructParamList;


    public EnumConstant param(String name, String type) {
        if(constructParamList==null) constructParamList = new ArrayList<>();

        constructParamList.add(new ConstructParam(name, type) );
        return this;
    }

    @Override
    public ComponentIdentity getIdentity() {
        return BaseComponentIdentity.create(ComponentType.FIELD, "param", this.name);
    }


    public static class ConstructParam {

        ConstructParam(String name, String type) {
            this.name = name;
            this.type = type;
        }

        private String name;
        private String type;

        public String toString() {
            if("String".equals(type)) {
                return "\""+name+"\"";
            }
            return name;
        }
    }
}
