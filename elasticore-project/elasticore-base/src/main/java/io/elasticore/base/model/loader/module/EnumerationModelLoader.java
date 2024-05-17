package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnumerationModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<EnumModel> {


    public boolean loadModel(ModelLoaderContext ctx, FileSource source) {
        return loadModel(ctx, source.getInfoMap());
    }

    public boolean loadModel(ModelLoaderContext ctx, Map<String, Map> map) {
        if (map.containsKey(ConstanParam.KEYNAME_ENUMERATION)) {
            Map entityMap = map.get(ConstanParam.KEYNAME_ENUMERATION);

            loadModel(ctx, ctx.getEnumModelItems(), entityMap);
            return true;
        }
        return false;
    }


    public void loadModel(ModelLoaderContext ctx,Items<EnumModel> items, Map<String, LinkedHashMap> enumMap) {
        enumMap.forEach((enumNm, value) -> {

            //System.err.println(enumNm + " parsed");

            EnumModel enumModel = loadEnumModel(ctx, enumNm, value);
            items.addItem(enumModel);

        });
    }


    protected EnumModel loadEnumModel(ModelLoaderContext ctx,String enumName, Map<String, Object> entityMap) {

        MetaInfo metaInfo = parseMetaInfoObject(entityMap);

        Map fields = (Map) entityMap.get(PROPERTY_FIELDS);
        Items<Field> fieldItems = null;
        if (fields != null)
            fieldItems = parseField(fields);

        Map enumMap = (Map) entityMap.get(PROPERTY_ENUM);
        Items<EnumConstant> enumItems = null;
        if (enumMap != null)
            enumItems = parseEnum(ctx, enumMap);

        //(String name, MetaInfo meta, Items<Field> items, Items<EnumConstant> enumConstantItems)
        return EnumModel.create(ctx.getDomainId(), enumName, metaInfo, fieldItems, enumItems);


    }

    protected Items<EnumConstant> parseEnum(ModelLoaderContext ctx,Map<String, String> fieldInfo) {
        Items<EnumConstant> enumConstantItems = Items.create(EnumConstant.class);

        fieldInfo.forEach((fieldNm, fieldPropery) -> {
            //System.out.println("* ENUM :" + fieldNm + " >>  " + fieldPropery);

            EnumConstant f = parseEnumLine(fieldNm, fieldPropery);
            enumConstantItems.addItem(f);
        });

        return enumConstantItems;
    }


    private static EnumConstant parseEnumLine(String enumName, String valueLine) {

        //System.err.println(enumName + " > " + valueLine);

        String[] items = valueLine.trim().split("\\s*,\\s*");

        //System.err.println(enumName + " > " + items.length);
        List<EnumConstant.ConstructParam> paramList = new ArrayList<>();
        for (String item : items) {
            paramList.add(EnumConstant.ConstructParam.create(item));
        }

        EnumConstant enumConstant = EnumConstant.builder().name(enumName).constructParamList(paramList).build();


        return enumConstant;
    }

}
