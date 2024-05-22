package io.elasticore.base.model.core;

import io.elasticore.base.model.*;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferModels;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.entity.TypeInfo;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
import lombok.Getter;

import java.util.*;

@Getter
public abstract class AbstractDataModel<T extends AbstractReplaceableModel<T>> extends AbstractReplaceableModel<T> {



    protected AbstractDataModel(ComponentIdentity componentIdentity) {
        super(componentIdentity);
    }


    abstract public MetaInfo getMetaInfo();

    abstract public ModelComponentItems<Field> getItems();


    protected void setRelationModel() {
        RelationshipManager rm = RelationshipManager.getInstance(this.getIdentity().getDomainId());
        String fromName = getIdentity().getName();

        Annotation extendAnt = this.getMetaInfo().getMetaAnnotation("extend");
        if(extendAnt!=null) {
            String toName = extendAnt.getValue();

            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.SUPER));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.CHILD));
        }
        Annotation rollupAnt = this.getMetaInfo().getMetaAnnotation("rollup");
        if(rollupAnt!=null) {
            String toName = extendAnt.getValue();
            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.ROLLUP));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ROLLDOWN));
        }else {
            Annotation rolldownAnt = this.getMetaInfo().getMetaAnnotation("rolldown");
            if(rolldownAnt!=null) {
                String toName = extendAnt.getValue();
                rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.ROLLDOWN));
                rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ROLLUP));
            }
        }

        Annotation templateAnt = this.getMetaInfo().getMetaAnnotation("template");
        if(templateAnt!=null) {
            String toName = templateAnt.getValue();
            if(toName !=null) {
                rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.TEMPLATE));
                rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.TEMPLATE_TO));
            }

        }
    }


    protected void setRelationship(Field f) {
        RelationshipManager rm = RelationshipManager.getInstance(this.getIdentity().getDomainId());
        TypeInfo typeInfo = f.getTypeInfo();
        String fromName = getIdentity().getName();
        String toName = typeInfo.getBaseTypeName();

        if (typeInfo.isGenericType()) {
            toName = typeInfo.getTypeParameterName();
        }

        if (f.hasAnnotation(RelationType.ONE_TO_ONE.getName())) {
            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.ONE_TO_ONE, f.getName()));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ONE_TO_ONE, f.getName()));
        } else if (f.hasAnnotation(RelationType.ONE_TO_MANY.getName())) {
            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.ONE_TO_MANY, f.getName()));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.MANY_TO_ONE, f.getName()));
        } else if (f.hasAnnotation(RelationType.MANY_TO_ONE.getName())) {
            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.MANY_TO_ONE, f.getName()));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ONE_TO_MANY, f.getName()));
        } else if (f.hasAnnotation(RelationType.MANY_TO_MANY.getName())) {
            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.MANY_TO_MANY, f.getName()));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.MANY_TO_MANY, f.getName()));
        } else if (f.hasAnnotation(RelationType.EMBEDDED.getName())) {
            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.EMBEDDED, f.getName()));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.EMBEDDABLE, f.getName()));


        } else if (!typeInfo.isBaseType()) {
            if (typeInfo.isGenericType()) {
                rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.MANY_TO_ONE, f.getName()));
                rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ONE_TO_MANY, f.getName()));

            } else {
                rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.ONE_TO_ONE, f.getName()));
                rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ONE_TO_ONE, f.getName()));
            }

        }
    }

    private void loadFieldInfo(AbstractDataModel model,ListMap<String, Field> List) {
        ModelComponentItems<Field> fields = model.getItems();

        while (fields.hasNext()) {
            Field f = fields.next();

            List.put(f.getName(), f);
        }
    }


    public ListMap<String, Field> getAllFieldListMap() {

        ListMap<String, Field> fieldListMap = new ListMap<>();

        Annotation templateAnt = this.getMetaInfo().getMetaAnnotation("template");
        if (templateAnt != null) {
            String templates = templateAnt.getValue();

            if (templates != null && templates.length() > 0) {
                String[] templateNmArray = templates.split(",");
                ECoreModel eCoreModel = BaseModelDomain.getModelDomain(getIdentity().getDomainId())
                        .getModel();

                DataTransferModels models = eCoreModel.getDataTransferModels();
                for (String templateNm : templateNmArray) {
                    DataTransfer templateEntity = models.findByName(templateNm);
                    if (templateEntity != null)
                        loadFieldInfo(templateEntity, fieldListMap);
                }


                EntityModels entityModels = eCoreModel.getEntityModels();
                for (String templateNm : templateNmArray) {
                    Entity templateEntity = entityModels.findByName(templateNm);
                    if (templateEntity != null)
                        loadFieldInfo(templateEntity, fieldListMap);
                }
            }

        }

        loadFieldInfo(this, fieldListMap);

        return fieldListMap;
    }

}
