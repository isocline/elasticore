package io.elasticore.base.model.loader;

import io.elasticore.base.ModelLoader;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Annotation;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.enums.EnumModels;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileBasedModelLoader implements ModelLoader, ConstanParam {

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

    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());


    public void test() {

        try {
            String path = "C:\\workspace\\Isocline\\elasticore\\elasticore-project\\elasicore-template\\src\\main\\resources\\blueprint\\api-auth-b2c\\entity\\AppToken.yml";


            Map member = mapper.readValue(new File(path), LinkedHashMap.class);

            System.err.println(member);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private FileSource getLoadData(File f) {
        FileSource fileSource = null;

        try {
            Map map = mapper.readValue(f, LinkedHashMap.class);
            return FileSource.builder().filepath(f.getAbsolutePath()).infoMap(map).build();
        } catch (Throwable e) {
            System.err.println(f.getAbsolutePath());
            e.printStackTrace();
            return FileSource.builder().filepath(f.getAbsolutePath()).error(e).build();
        }

    }

    private List<FileSource> fileSources = new ArrayList<>();

    private void loadData(File f, List<FileSource> fileSources) {

        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File chidFile : files) {
                loadData(chidFile, fileSources);
            }
        } else {

            FileSource fs = getLoadData(f);
            fileSources.add(fs);
        }
    }


    @Override
    public ECoreModel load(String domainName) {


        test();

        List<FileSource> fileSources = new ArrayList<>();

        File f = new File("C:\\workspace\\Isocline\\elasticore\\elasticore-project\\elasicore-template\\src\\main\\resources\\blueprint\\api-auth-b2c");
        loadData(f, fileSources);


        Items<Entity> entityItems = Items.create(Entity.class);
        Items<EnumModel> enumModelItems = Items.create(EnumModel.class);

        for (FileSource fileSource : fileSources) {

            Map map = fileSource.getInfoMap();

            if (map == null) {
                System.out.println("NULL " + fileSource.getFilepath());
                continue;
            }

            if (map.containsKey(ConstanParam.KEYNAME_ENTITY)) {
                Map entityMap = (Map) map.get(ConstanParam.KEYNAME_ENTITY);
                System.out.println("entity " + fileSource.getFilepath());

                loadEntity(entityItems, entityMap);

            }

            if (map.containsKey(ConstanParam.KEYNAME_ENUMERATION)) {
                Map entityMap = (Map) map.get(ConstanParam.KEYNAME_ENUMERATION);
                System.out.println("ENUM " + fileSource.getFilepath());

                loadEnum(enumModelItems, entityMap);
            }
        }

        EntityModels entityModels = EntityModels.create("entityGrp", null, entityItems);
        EnumModels enumModels = EnumModels.create("enumGroup",null, enumModelItems);

        //EnumModels enumModels = getEnumModels();
        //EntityModels entityModels = getEntityModels();

        return ECoreModel.builder()
                .entityModels(entityModels)
                .enumModels(enumModels)
                .build();
    }

    protected void loadEntity(Items<Entity> items, Map<String, LinkedHashMap> entityMap) {

        entityMap.forEach((entityNm, value) -> {

            System.err.println(entityNm + " parsed");


            Entity entity = loadEntity(entityNm, value);
            items.addItem(entity);
        });

    }

    protected void loadEnum(Items<EnumModel> items, Map<String, LinkedHashMap> enumMap) {
        enumMap.forEach((enumNm, value) -> {

            System.err.println(enumNm + " parsed");


            EnumModel enumModel = loadEnumModel(enumNm, value);
            items.addItem(enumModel);

        });
    }


    protected void parsePropertues(String propertiesText) {
        //properties.split("")
    }

    private static Field parseFieldLine(String fieldNm, String fieldLine) {

        String[] parts = fieldLine.split(" ", 2);
        String type = parts[0];
        fieldLine = parts.length > 1 ? parts[1] : "";

        System.out.println("Type: " + type);


        Pattern pattern = Pattern.compile("@(\\w+)(?:\\((.*?)\\))?");
        Matcher matcher = pattern.matcher(fieldLine);

        Map<String, Annotation> annotationMap = new HashMap<>();
        while (matcher.find()) {
            String annotationName = matcher.group(1);
            String attributeParameters = matcher.group(2);

            System.out.println("Attribute Name: " + annotationName);


            Annotation annotation = null;
            if (attributeParameters != null && !attributeParameters.isEmpty()) {
                annotation = parseParameters(annotationName, attributeParameters);
            } else {
                annotation = Annotation.create(annotationName);
                System.out.println("  No parameters");
            }
            annotationMap.put(annotation.getName(), annotation);
        }

        return Field.builder().name(fieldNm).type(type).annotationMap(annotationMap).build();

    }


    private static Annotation parseParameters(String annotationName, String parameters) {

        Pattern kvPattern = Pattern.compile("(\\w+)\\s*=\\s*([^,]+)|([^,\\s]+)");
        Matcher kvMatcher = kvPattern.matcher(parameters);

        String singleValue = null;
        Properties properties = new Properties();
        while (kvMatcher.find()) {
            if (kvMatcher.group(1) != null) {

                String key = kvMatcher.group(1);
                String value = kvMatcher.group(2);
                properties.setProperty(key, value);
                System.out.println("  Parameter: " + key + " = " + value);
            } else if (kvMatcher.group(3) != null) {

                singleValue = kvMatcher.group(3);
                System.out.println("  Parameter: " + singleValue);
            }
        }

        return Annotation.create(annotationName, singleValue, properties);
    }


    private static void parseAttribute3(String input) {

        String[] parts = input.split(" ", 2);
        String type = parts[0];
        String attributes = parts.length > 1 ? parts[1] : "";

        System.out.println("Type: " + type);


        Pattern pattern = Pattern.compile("@(\\w+)(\\(([^)]*)\\))?");
        Matcher matcher = pattern.matcher(attributes);

        while (matcher.find()) {
            String attributeName = matcher.group(1);
            String attributeValues = matcher.group(3);

            System.out.println("Attribute Name: " + attributeName);


            if (attributeValues != null && !attributeValues.isEmpty()) {

                Pattern kvPattern = Pattern.compile("(\\w+)\\s*=\\s*([^,]+)|([^,]+)");
                Matcher kvMatcher = kvPattern.matcher(attributeValues);

                while (kvMatcher.find()) {
                    if (kvMatcher.group(1) != null) {

                        String key = kvMatcher.group(1);
                        String value = kvMatcher.group(2);
                        System.out.println("  Key: " + key + ", Value: " + value);
                    } else {

                        String value = kvMatcher.group(3);
                        System.out.println("  Value: " + value);
                    }
                }
            } else {
                System.out.println("  No values defined");
            }
        }
    }


    protected EnumModel loadEnumModel(String enumName, Map<String, LinkedHashMap> entityMap) {

        Map info = (Map) entityMap.get(PROPERTY_INFO);

        Map meta = (Map) entityMap.get(PROPERTY_META);

        Map fields = (Map) entityMap.get(PROPERTY_FIELDS);
        Items<Field> fieldItems = null;
        if (fields != null)
            fieldItems = parseField(fields);

        Map enumMap = (Map) entityMap.get(PROPERTY_ENUM);
        Items<EnumConstant> enumItems = null;
        if (enumMap != null)
            enumItems = parseEnum(enumMap);

        //(String name, MetaInfo meta, Items<Field> items, Items<EnumConstant> enumConstantItems)
        return EnumModel.create(enumName, null, fieldItems, enumItems);


    }


    protected Entity loadEntity(String entityNm, Map<String, LinkedHashMap> entityMap) {

        Map info = (Map) entityMap.get(PROPERTY_INFO);

        Map meta = (Map) entityMap.get(PROPERTY_META);

        Map fields = (Map) entityMap.get(PROPERTY_FIELDS);
        Items<Field> fieldItems = null;
        if (fields != null)
            fieldItems = parseField(fields);

        return Entity.create(entityNm, fieldItems, null);


    }

    protected Items<EnumConstant> parseEnum(Map<String, String> fieldInfo) {
        Items<EnumConstant> enumConstantItems = Items.create(EnumConstant.class);

        fieldInfo.forEach((fieldNm, fieldPropery) -> {
            System.out.println("* ENUM :" + fieldNm + " >>  " + fieldPropery);


            EnumConstant f = parseEnumLine(fieldNm, fieldPropery);
            enumConstantItems.addItem(f);
        });

        return enumConstantItems;
    }

    private static EnumConstant parseEnumLine(String enumName, String valueLine) {

        System.err.println(enumName + " > " + valueLine);

        String[] items = valueLine.trim().split("\\s*,\\s*");

        System.err.println(enumName + " > " + items.length);
        List<EnumConstant.ConstructParam> paramList = new ArrayList<>();
        for(String item: items) {
            paramList.add(EnumConstant.ConstructParam.create(item));
        }

        EnumConstant enumConstant = EnumConstant.builder().name(enumName).constructParamList(paramList).build();


        return enumConstant;
    }

    protected Items<Field> parseField(Map<String, String> fieldInfo) {

        Items<Field> items = Items.create(Field.class);

        fieldInfo.forEach((fieldNm, fieldPropery) -> {

            System.out.println("* FIELD :" + fieldNm + " >>  " + fieldPropery);


            Field f = parseFieldLine(fieldNm, fieldPropery);
            items.addItem(f);
        });

        return items;
    }


    /**
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
