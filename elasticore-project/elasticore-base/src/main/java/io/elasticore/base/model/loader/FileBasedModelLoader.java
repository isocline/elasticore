package io.elasticore.base.model.loader;

import io.elasticore.base.ModelLoader;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.enums.EnumModels;

import java.util.ArrayList;
import java.util.List;

public class FileBasedModelLoader implements ModelLoader {

    private static FileBasedModelLoader instance;

    public static FileBasedModelLoader newInstance() {
        return new FileBasedModelLoader();
    }


    @Override
    public List<String> getDomainNameList() {
        List<String> domainNmList = new ArrayList();
        domainNmList.add("default");
        return domainNmList;
    }


    @Override
    public ECoreModel load(String domainName) {
        EnumModels enumModels = getEnumModels();
        EntityModels entityModels = getEntityModels();

        return ECoreModel.builder()
                .entityModels(entityModels)
                .enumModels(enumModels)
                .build();
    }


    /**
     *
     * @return
     */
    protected EnumModels getEnumModels() {
        Field ef1 = Field.builder()
                .name("name")
                .type("String")
                .build();

        Field ef2 = Field.builder()
                .name("size")
                .type("int")
                .build();

        Items<Field> enumItems = Items.create(Field.class)
                .addItem(ef1).addItem(ef2);


        EnumConstant p1 = EnumConstant.builder()
                .name("SALEING").build()
                .param("1", "String").param("1", "Integer");

        EnumConstant p2 = EnumConstant.builder()
                .name("RELEASE").build()
                .param("2", "String").param("2", "Integer");


        Items<EnumConstant> enumConstant = Items.create(EnumConstant.class)
                .addItem(p1).addItem(p2);

        EnumModel enumModel = EnumModel.create("ForSaleStatus", null, enumItems, enumConstant);

        Items<EnumModel> enumItems2 = Items.create(EnumModel.class)
                .addItem(enumModel);

        EnumModels enumModels = EnumModels.create("egroups", null, enumItems2);

        return enumModels;
    }


    /**
     *
     *
     * @return
     */
    protected EntityModels getEntityModels() {

        Field f = Field.builder()
                .isPrimaryKey(true)
                .generationType("IDENTITY")
                .description("name")
                .name("name").type("String").build();


        Field f2 = Field.builder().name("age").type("int").length(10).build();


        Field f3 = Field.builder().name("address").type("String")
                .nullable(false).columnDefinition("TEXT").build();

        Field f4 = Field.builder().name("address").type("String")
                .nullable(false)
                .unique(true)
                .columnDefinition("TEXT").build();

        Items<Field> items = Items.create(Field.class)
                .addItem(f)
                .addItem(f2)
                .addItem(f3)
                .addItem(f4);

        Entity entity = Entity.create("Person", items, null);


        Items<Entity> entityItems = Items.create(Entity.class)
                .addItem(entity);


        EntityModels entityModels = EntityModels.create("entityGrp", null, entityItems);
        return entityModels;
    }

}
