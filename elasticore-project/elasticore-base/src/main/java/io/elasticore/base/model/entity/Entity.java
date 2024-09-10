package io.elasticore.base.model.entity;


import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.*;
import io.elasticore.base.model.core.*;
import io.elasticore.base.util.StringUtils;
import lombok.Getter;

@Getter
public class Entity extends AbstractDataModel implements MetaInfoModel, DataModelComponent {

    private final MetaInfo metaInfo;
    private final Items<Field> items;
    //private final ModelComponentItems<Field> items;
    private final PkField pkField;

    private Entity(ComponentIdentity id, Items<Field> items, MetaInfo metaInfo) {
        super(id);

        this.items = items;
        //this.items = new BaseModelComponentItem(items);
        if (metaInfo == null)
            this.metaInfo = MetaInfo.createEmpty();
        else
            this.metaInfo = metaInfo;


        setRelationModel();

        Items<Field> pkFields = Items.create(Field.class);
        if (items != null) {
            for (Field f : items.getItemList()) {
                if (f.isPrimaryKey())
                    pkFields.addItem(f);

                setRelationship(f);
            }
        }

        if (pkFields.size() > 0)
            this.pkField = PkField.create(pkFields, null, this);
        else
            this.pkField = null;
    }



    public static Entity create(String domainId, String name, MetaInfo metaInfo, Items<Field> items) {
        BaseComponentIdentity identity = BaseComponentIdentity.create(ComponentType.ENTITY, domainId, name);
        return new Entity(identity, items, metaInfo);
    }


    public ModelComponentItems<Field> getItems() {
        return new BaseModelComponentItem(items);
    }

    public String getTableName() {
        Annotation annotation = getMetaInfo().getMetaAnnotation(EntityAnnotation.META_TABLE);
        if (annotation != null) {
            return annotation.getValue();
        }else {
            annotation = getMetaInfo().getMetaAnnotation(EntityAnnotation.META_IMMUTABLE);
            if (annotation != null) {
                return annotation.getValue();
            }

        }
        return StringUtils.camelToSnake(this.getIdentity().getName());
    }


    public Entity findParentEntity(ModelDomain domain) {
        Annotation extendAnnotation = getMetaInfo().getMetaAnnotation(EntityAnnotation.META_EXTEND);
        if (extendAnnotation != null) {
            String parentNm = extendAnnotation.getValue();
            try {
                ECoreModel eCoreModel = domain.getModel();
                EntityModels entityModels = eCoreModel.getEntityModels();
                return entityModels.findByName(parentNm);
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return null;
    }

    public PkField findPkField(ModelDomain domain) {
        if (this.pkField != null)
            return pkField;

        Entity parentEntity = findParentEntity(domain);
        if (parentEntity != null)
            return parentEntity.findPkField(domain);

        return null;
    }


    public String findFieldName(String fieldName) {
        String findName = null;
        Field f = this.items.findByName(fieldName);

        if (f == null) return null;

        if (this.pkField.isMultiple()) {
            return "Id" + StringUtils.capitalize(f.getName());
        }

        return f.getName();
    }


}
