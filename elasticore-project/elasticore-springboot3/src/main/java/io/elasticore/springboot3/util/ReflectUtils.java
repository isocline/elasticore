package io.elasticore.springboot3.util;

import java.lang.reflect.Field;
import java.util.*;


/**
 *
 */
public class ReflectUtils {


    private static Map<Class, Map<String, Field>> classFieldMap = new HashMap<>();

    public static Field getField(Class c, String fieldName) {
        String lowcaseFieldName = fieldName.toLowerCase();
        Map<String, Field> fieldMap = classFieldMap.get(c);
        if (fieldMap == null) {
            fieldMap = new HashMap<>();
            classFieldMap.put(c, fieldMap);
            Field[] fds = c.getDeclaredFields();
            for (Field f : fds) {
                String lowcaseFieldNm = f.getName().toLowerCase();
                f.setAccessible(true);
                fieldMap.put(lowcaseFieldNm, f);
            }
        }

        return fieldMap.get(lowcaseFieldName);
    }

}
