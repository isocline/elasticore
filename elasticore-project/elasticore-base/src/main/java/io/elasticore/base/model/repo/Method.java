package io.elasticore.base.model.repo;

import io.elasticore.base.model.ComponentIdentity;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.core.BaseComponentIdentity;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Field;
import lombok.Builder;
import lombok.Getter;

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

    public SqlQueryInfo queryInfo;


    public boolean isNeedQueryAnnotation() {
        if(queryInfo==null)
            return false;

        /*
        if(this.name !=null)
            return true;

         */

        return queryInfo.isJpaQueryAnnotationNeed();
    }

    public boolean isEnable() {

        if(queryInfo!=null) {
            if(queryInfo.isPredefinedJpaMethodName())
                return false;
        }

        /*
        if(queryInfo!=null)
            return queryInfo.isEnableNativeJpaMethod();


         */
        return true;

    }

    public String getQuery() {
        if(this.queryInfo==null)
            return null;
        return this.queryInfo.getSqlTxt();
    }




    public Items<Field> getParams() {
        if(params!=null)
            return params;

        if(this.queryInfo!=null)
            return this.queryInfo.getSetVarFieldItems();

        return null;
    }


    public String getReturnType() {
        if(returnType!=null)
            return returnType;
        if(this.queryInfo!=null)
            return this.queryInfo.getDefaultReturnType();

        return "void";
    }

    public String getName() {
        if(name !=null)
            return name;


        if(this.queryInfo!=null) {
            return this.queryInfo.getJpaMethodName();
        }



        return "unknownMethod";
    }


    public boolean isNative() {
        if(this.queryInfo!=null) {
            isNative =this.queryInfo.isNativeQuery();
        }

        return isNative;
    }
}
