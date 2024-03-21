package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Annotation;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.loader.ModelLoader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractModelLoader implements ConstanParam {


    protected Items<Field> parseField(Map<String, String> fieldInfo) {
        Items<Field> items = Items.create(Field.class);

        fieldInfo.forEach((fieldNm, fieldPropery) -> {

            System.out.println("* FIELD :" + fieldNm + " >>  " + fieldPropery);

            Field f = parseFieldLine(fieldNm, fieldPropery);
            items.addItem(f);
        });

        return items;
    }


    protected Annotation parseParameters(String annotationName, String parameters) {
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


    protected Field parseFieldLine(String fieldNm, String fieldLine) {
        String[] parts = fieldLine.split(" ", 2);
        String type = parts[0];
        fieldLine = parts.length > 1 ? parts[1] : "";

        boolean isPrimaryKey = false;


        Pattern pattern = Pattern.compile("@(\\w+)(?:\\((.*?)\\))?");
        Matcher matcher = pattern.matcher(fieldLine);

        Map<String, Annotation> annotationMap = new HashMap<>();
        while (matcher.find()) {
            String annotationName = matcher.group(1);
            String attributeParameters = matcher.group(2);

            if("id".equals(annotationName)){
                isPrimaryKey = true;
            }

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

        return Field.builder().name(fieldNm)
                .type(type)
                .isPrimaryKey(isPrimaryKey)
                .annotationMap(annotationMap).build();

    }
}
