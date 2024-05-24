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
        Annotation rollupAnt = this.getMetaInfo().getMetaAnnotation(RelationType.ROLLUP.getName());
        if(rollupAnt!=null) {
            String toName = extendAnt.getValue();
            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.ROLLUP));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ROLLDOWN));
        }else {
            Annotation rolldownAnt = this.getMetaInfo().getMetaAnnotation(RelationType.ROLLDOWN.getName());
            if(rolldownAnt!=null) {
                String toName = extendAnt.getValue();
                rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.ROLLDOWN));
                rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ROLLUP));
            }
        }

        Annotation templateAnt = this.getMetaInfo().getMetaAnnotation(RelationType.TEMPLATE.getName());
        if(templateAnt!=null) {
            String toName = templateAnt.getValue();
            if(toName !=null) {
                rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.TEMPLATE));
                rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.TEMPLATE_TO));
            }
        }

        Annotation searchAnt = this.getMetaInfo().getMetaAnnotation(RelationType.SEARCHABLE.getName());
        if(searchAnt!=null) {
            String toName = searchAnt.getValue();
            if(toName !=null) {
                rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.SEARCHABLE));
                rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.SEARCHED));
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



    private void loadFieldInfo(AbstractDataModel model,ListMap<String, Field> list) {

        if(model == this) {
            ModelComponentItems<Field> fields = model.getItems();
            while (fields.hasNext()) {
                Field f = fields.next();
                if(!list.containsKey(f.getName()))
                    list.put(f.getName(), f);
            }
        }else {
            ListMap<String, Field> fieldListMap = model.getAllFieldListMap();

            for(Field f : fieldListMap.getList()) {
                if(!list.containsKey(f.getName()))
                    list.put(f.getName(), f);
            }
        }

    }

    private String getReferenceModelNames(MetaInfo metaInfo) {
        Annotation templateAnt = this.getMetaInfo().getMetaAnnotation("template");
        Annotation extendAnt = this.getMetaInfo().getMetaAnnotation("extend");
        Annotation searchAnt = this.getMetaInfo().getMetaAnnotation("searchable");

        StringBuilder sb = new StringBuilder();
        if(templateAnt!=null) {
            String names = templateAnt.getValue();
            if(names!=null) {
                sb.append(names.trim());
            }
        }
        if(extendAnt!=null) {
            String names = extendAnt.getValue();
            if(names!=null) {
                if(sb.length()>0) sb.append(",");
                sb.append(names.trim());
            }
        }
        if(searchAnt!=null) {
            String names = searchAnt.getValue();
            if(names!=null) {
                if(sb.length()>0) sb.append(",");
                sb.append(names.trim());
            }
        }


        return sb.toString();
    }

    public ListMap<String, Field> getAllFieldListMap() {

        ECoreModel eCoreModel = BaseModelDomain.getModelDomain(getIdentity().getDomainId())
                .getModel();


        ListMap<String, Field> fieldListMap = new ListMap<>();
        loadFieldInfo(this, fieldListMap);

        {
            String templates = getReferenceModelNames(this.getMetaInfo());

            if (templates != null && templates.length() > 0) {
                String[] templateNmArray = templates.split(",");


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



        return fieldListMap;
    }

}
