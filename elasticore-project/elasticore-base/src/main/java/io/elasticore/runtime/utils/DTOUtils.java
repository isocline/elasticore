package io.elasticore.runtime.utils;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DTOUtils {

    protected static Object invokeMethod(Class clazz, String methodName, Object obj) {

        if(obj==null)
            return null;

        Method method = null;

        try {
            method = clazz.getMethod(methodName);
            return method.invoke(obj);
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
        return null;
    }

    private static void setDescriptionInfo(Map<String, Object> fieldMetadata, Field field) {
        try {
            Class clazz = Class.forName("io.swagger.v3.oas.annotations.media.Schema");
            Annotation annotation = field.getAnnotation(clazz);

            Object val = invokeMethod(clazz, "description", annotation);
            if (val != null) {
                fieldMetadata.put("description", val);
            }

        } catch (ClassNotFoundException e) {
        }
    }

    private static void setMaxSize(Map<String, Object> fieldMetadata, Field field) {
        try {
            Class clazz = Class.forName("jakarta.validation.constraints.Size");
            Annotation annotation = field.getAnnotation(clazz);

            Object val = invokeMethod(clazz, "max", annotation);
            if (val != null) {
                fieldMetadata.put("maxSize", val);
            }

        } catch (ClassNotFoundException e) {
        }
    }


    /**
     *
     *
     * @param dtoClass
     * @return
     */
    public static Map<String, Object> getMetaInfo(Class dtoClass) {
        Map<String, Object> metadata = new LinkedHashMap<>();
        Field[] fields = dtoClass.getDeclaredFields();

        for (Field field : fields) {
            Map<String, Object> fieldMetadata = new LinkedHashMap<>();
            fieldMetadata.put("type", field.getType().getSimpleName());

            setDescriptionInfo(fieldMetadata, field);
            setMaxSize(fieldMetadata, field);


            // Enum 타입 처리 (BrandType 같은 enum 클래스 처리)
            if (field.getType().isEnum()) {
                fieldMetadata.put("type", "enumeration");
                List<Map<String, Object>> enumValues = new ArrayList<>();

                for (Object enumConstant : field.getType().getEnumConstants()) {
                    Map<String, Object> enumInfo = new LinkedHashMap<>();

                    // enum 상수명
                    enumInfo.put("enum", enumConstant.toString());

                    // enum의 필드 값 추출
                    try {
                        for (Field enumField : enumConstant.getClass().getDeclaredFields()) {
                            if (!enumField.isEnumConstant() && !Modifier.isStatic(enumField.getModifiers())) {
                                enumField.setAccessible(true);
                                String keyNm = enumField.getName();
                                if("code".equals(keyNm)) keyNm="value";
                                else if("label".equals(keyNm)) keyNm="text";

                                Object valObj = enumField.get(enumConstant);
                                enumInfo.put(keyNm, valObj);
                            }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    enumValues.add(enumInfo);
                }

                fieldMetadata.put("enums", enumValues);
            }

            metadata.put(field.getName(), fieldMetadata);
        }

        return metadata;
    }


}
