package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        this.setModelLoaderContext(ctx);

        enumMap.forEach((enumNm, value) -> {

            //System.err.println(enumNm + " parsed");

            if(value!=null) {
                EnumModel enumModel = loadEnumModel(ctx, enumNm, value);

                ctx.getEnumModelItems().addItem(enumModel);

                items.addItem(enumModel);
            }
        });
    }


    protected EnumModel loadEnumModel(ModelLoaderContext ctx,String enumName, Map<String, Object> entityMap) {

        MetaInfo metaInfo = parseMetaInfoObject(entityMap, ConstanParam.KEYNAME_ENUM, enumName);

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



        //String[] items = valueLine.trim().split("\\s*,\\s*");

        String regex = "'(?:[^']|'')*'|[^,]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(valueLine);

        // 결과를 저장할 리스트
        ArrayList<String> results = new ArrayList<>();

        // 정규식으로 매칭된 그룹을 리스트에 추가
        while (matcher.find()) {
            results.add(matcher.group().trim());
        }

        String[] items = results.toArray(new String[0]);

        //System.err.println(enumName + " > " + items.length);
        List<EnumConstant.ConstructParam> paramList = new ArrayList<>();
        for (String item : items) {
            paramList.add(EnumConstant.ConstructParam.create(item));
        }

        EnumConstant enumConstant = EnumConstant.builder().name(enumName).constructParamList(paramList).build();


        return enumConstant;
    }

}
