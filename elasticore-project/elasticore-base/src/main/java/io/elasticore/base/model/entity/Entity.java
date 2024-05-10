package io.elasticore.base.model.entity;


import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.*;
import io.elasticore.base.model.core.*;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
import io.elasticore.base.util.StringUtils;
import lombok.Getter;

@Getter
public class Entity extends AbstractReplaceableModel implements MetaInfoModel,DataModelComponent {

    private final MetaInfo metaInfo;
    private final Items<Field> items;
    //private final ModelComponentItems<Field> items;
    private final PkField pkField;

    private Entity(ComponentIdentity id, Items<Field> items, MetaInfo metaInfo) {
        super(id);

        this.items = items;
        //this.items = new BaseModelComponentItem(items);
        if(metaInfo ==null)
            this.metaInfo = MetaInfo.createEmpty();
        else
            this.metaInfo = metaInfo;


        setRelationshipEntity();

        Items<Field> pkFields = Items.create(Field.class);
        if(items!=null) {
            for(Field f:items.getItemList()) {
                if(f.isPrimaryKey())
                    pkFields.addItem(f);

                setRelationship(f);
            }
        }

        if(pkFields.size()>0)
            this.pkField = PkField.create(pkFields, null , this);
        else
            this.pkField = null;
    }

    private void setRelationshipEntity() {
        RelationshipManager rm = RelationshipManager.getInstance();
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
    }

    private void setRelationship(Field f) {
        RelationshipManager rm = RelationshipManager.getInstance();
        TypeInfo typeInfo = f.getTypeInfo();
        String fromName = getIdentity().getName();
        String toName = typeInfo.getBaseTypeName();

        if(typeInfo.isGenericType()) {
            toName = typeInfo.getTypeParameterName();
        }

        if(f.hasAnnotation(RelationType.ONE_TO_ONE.getName())) {
            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.ONE_TO_ONE));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ONE_TO_ONE));
        }
        else if(f.hasAnnotation(RelationType.ONE_TO_MANY.getName())) {
            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.ONE_TO_MANY));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.MANY_TO_ONE));
        }
        else if(f.hasAnnotation(RelationType.MANY_TO_ONE.getName())) {
            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.MANY_TO_ONE));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ONE_TO_MANY));
        }
        else if(f.hasAnnotation(RelationType.MANY_TO_MANY.getName())) {
            rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.MANY_TO_MANY));
            rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.MANY_TO_MANY));
        }
        else if(!typeInfo.isBaseType()) {
            if(typeInfo.isGenericType()) {
                rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.MANY_TO_ONE));
                rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ONE_TO_MANY));

            }
            else {
                rm.addRelationship(ModelRelationship.create(fromName, toName, RelationType.ONE_TO_ONE));
                rm.addRelationship(ModelRelationship.create(toName, fromName, RelationType.ONE_TO_ONE));
            }

        }
    }

    public static Entity create(String name, MetaInfo metaInfo, Items<Field> items) {
        BaseComponentIdentity identity = BaseComponentIdentity.create(ComponentType.ENTITY, name);
        return new Entity(identity, items, metaInfo);
    }


    public ModelComponentItems<Field> getItems() {
        return new BaseModelComponentItem(items);
    }

    public String getTableName() {
        Annotation annotation = getMetaInfo().getMetaAnnotation("table");
        if(annotation!=null) {
            return annotation.getValue();
        }
        return StringUtils.camelToSnake(this.getIdentity().getName());
    }


    public Entity findParentEntity(ModelDomain domain) {
        Annotation extendAnnotation = getMetaInfo().getMetaAnnotation("extend");
        if(extendAnnotation!=null){
            String parentNm = extendAnnotation.getValue();
            return domain.getModel().getEntityModels().findByName(parentNm);
        }

        return null;
    }

    public PkField findPkField(ModelDomain domain) {
        if(this.pkField!=null)
            return pkField;

        Entity parentEntity = findParentEntity(domain);
        if(parentEntity!=null)
            return parentEntity.findPkField(domain);

        return null;
    }


    public String findFieldName(String fieldName) {
        String findName = null;
        Field f = this.items.findByName(fieldName);

        if(f==null) return null;

        if(this.pkField.isMultiple()) {
            return "Id"+StringUtils.capitalize(f.getName());
        }

        return f.getName();
    }


}
