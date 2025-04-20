package io.elasticore.springboot3.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

class MappingUtils {


    public static <T> List<T> toList(List<?> originList, Class<T> targetClass) {
        return toList(originList, targetClass, null);
    }
    /**
     * Converts a list of source objects to a list of target objects of the specified class,
     * copying only the specified fields.
     *
     * @param <T>         The target object type
     * @param originList  The original list of source objects
     * @param targetClass The target class to convert each object to
     * @param fieldNames  The list of field names to copy
     * @return A new list containing objects of type T with copied field values
     * @throws IllegalArgumentException if any argument is null or instantiation fails
     */
    public static <T> List<T> toList(List<?> originList, Class<T> targetClass, List<String> fieldNames){
        return toList(originList, targetClass, fieldNames, null);
    }

    public static <T> List<T> toList(List<?> originList, Class<T> targetClass, List<String> fieldNames , List<String> toFieldNames) {
        if (originList == null || targetClass == null ) {
            throw new IllegalArgumentException("originList, targetClass  must not be null");
        }

        List<T> result = new ArrayList<>(originList.size());

        for (Object source : originList) {
            try {
                T target = targetClass.getDeclaredConstructor().newInstance();
                copy(source, target, fieldNames , toFieldNames);
                result.add(target);
            } catch (ReflectiveOperationException e) {
                throw new IllegalArgumentException("Failed to create instance of " + targetClass.getName(), e);
            }
        }

        return result;
    }

    /**
     * Copies values from one object to another object of a different class,
     * only for specified fields with matching names and compatible types.
     *
     * @param from The source object
     * @param to The target object
     * @param fieldNames List of field names to copy
     * @throws IllegalArgumentException if from, to, or fieldNames is null
     */
    public static void copy(Object from, Object to, List<String> fieldNames) {
        copy(from, to, fieldNames, null);

    }


    private static List<String> extractFieldNames(Object obj) {
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).keySet().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        } else {
            List<String> result = new ArrayList<>();
            for (Field field : obj.getClass().getDeclaredFields()) {
                result.add(field.getName());
            }
            return result;
        }
    }

    /**
     * Copies values from one object to another object of a different class,
     * only for specified fields with matching names and compatible types.
     *
     * @param from The source object
     * @param to The target object
     * @param fieldNames List of field names in the source object to copy from
     * @param toFieldNames List of corresponding field names in the target object to copy to
     * @throws IllegalArgumentException if from, to, fieldNames, or toFieldNames is null
     *                                  or if their sizes do not match
     */
    public static void copy(Object from, Object to, List<String> fieldNames, List<String> toFieldNames) {
        boolean isIncludeUndefined = false;
        if(fieldNames==null)
            isIncludeUndefined = true;
        copy(from ,to,fieldNames, toFieldNames,isIncludeUndefined);
    }

    public static void copy(Object from, Object to, List<String> fieldNames, List<String> toFieldNames, boolean isIncludeUndefined) {
        copy(from ,to,fieldNames, toFieldNames,isIncludeUndefined , null);
    }

    public static void copy(Object from, Object to, List<String> fieldNames, List<String> toFieldNames, boolean isIncludeUndefined, Map<String,MappingGuard> guardMap) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Source and target must not be null");
        }

        Map<String, String> definedMappings = new LinkedHashMap<>();
        if (fieldNames != null ) {

            if(toFieldNames == null)
                toFieldNames=fieldNames;

            for (int i = 0; i < Math.min(fieldNames.size(), toFieldNames.size()); i++) {
                definedMappings.put(fieldNames.get(i), toFieldNames.get(i));
            }
        }

        Set<String> allTargetFieldNames = new LinkedHashSet<>(definedMappings.values());

        if (isIncludeUndefined) {
            List<String> fromFieldList = extractFieldNames(from);
            List<String> toFieldList = extractFieldNames(to);

            if(to instanceof Map) {

                for(String keyNm : fromFieldList) {

                    if(!definedMappings.containsKey(keyNm)) {
                        definedMappings.put(keyNm, keyNm);
                        allTargetFieldNames.add(keyNm);
                    }

                }
            }else {
                for (String name : toFieldList) {
                    if (!allTargetFieldNames.contains(name) && fromFieldList.contains(name)) {
                        definedMappings.put(name, name);
                        allTargetFieldNames.add(name);
                    }
                }
            }

        }

        for (Map.Entry<String, String> entry : definedMappings.entrySet()) {
            String fromName = entry.getKey();
            String toName = entry.getValue();

            MappingGuard mappingGuard = null;
            MappingContext ctx = null;
            if(guardMap!=null) {
                mappingGuard = guardMap.get(fromName);
                ctx = new MappingContext(1, from,to, fromName, mappingGuard);

                if(!ctx.checkEnable())
                    continue;
            }

            try {
                Object value = null;

                if (from instanceof Map) {
                    if (!((Map<?, ?>) from).containsKey(fromName)) continue;
                    value = ((Map<?, ?>) from).get(fromName);
                } else {
                    try {
                        Field sourceField = from.getClass().getDeclaredField(fromName);
                        sourceField.setAccessible(true);
                        value = sourceField.get(from);
                    } catch (NoSuchFieldException ignore) {
                        continue;
                    }
                }

                if (to instanceof Map) {
                    ((Map<String, Object>) to).put(toName, value);
                } else {
                    Field targetField;
                    try {
                        targetField = to.getClass().getDeclaredField(toName);
                    } catch (NoSuchFieldException e) {
                        continue;
                    }

                    targetField.setAccessible(true);

                    if (value == null) {
                        targetField.set(to, null);
                        continue;
                    }

                    if (List.class.isAssignableFrom(targetField.getType()) && value instanceof List) {
                        ParameterizedType genericType = (ParameterizedType) targetField.getGenericType();
                        Class<?> itemType = (Class<?>) genericType.getActualTypeArguments()[0];

                        List<?> sourceList = (List<?>) value;
                        List<Object> targetList = new ArrayList<>();

                        for (Object item : sourceList) {



                            if (item instanceof Map) {
                                Object targetItem = itemType.getDeclaredConstructor().newInstance();
                                copy(item, targetItem, null, null, isIncludeUndefined ,guardMap);

                                if(guardMap!=null) {
                                    ctx = new MappingContext(MappingEventType.AFTER_ADD_LIST, from,item , to, targetItem, fromName, mappingGuard);
                                    if(!ctx.checkEnable())
                                        continue;
                                }


                                targetList.add(targetItem);
                            } else {
                                if(guardMap!=null) {
                                    ctx = new MappingContext(MappingEventType.AFTER_ADD_LIST, from,item , to, item, fromName, mappingGuard);
                                    if(!ctx.checkEnable())
                                        continue;
                                }
                                targetList.add(item);
                            }
                        }

                        targetField.set(to, targetList);

                    } else if (value instanceof Map && !targetField.getType().isAssignableFrom(Map.class)) {
                        Object nestedObj = targetField.getType().getDeclaredConstructor().newInstance();
                        copy(value, nestedObj, null, null, isIncludeUndefined);
                        targetField.set(to, nestedObj);

                    } else {
                        /*
                        if (!targetField.getType().isAssignableFrom(value.getClass())) {
                            continue;
                        }

                         */

                        Class targetClass =targetField.getType();
                        if( !isPrimitiveOrWrapperOrString(targetClass) && targetClass!= value.getClass()) {
                            Object nestedObj = targetClass.newInstance();
                            copy(value, nestedObj, null, null, isIncludeUndefined);
                            targetField.set(to, nestedObj);
                        }else {
                            targetField.set(to, value);
                        }


                    }
                }

            } catch (Exception e) {
                throw new RuntimeException("copy error: " + fromName + " -> " + toName, e);
            }
        }
    }

    private static boolean isPrimitiveOrWrapperOrString(Class<?> clazz) {
        return clazz.isPrimitive()
                || clazz == Integer.class
                || clazz == Long.class
                || clazz == Double.class
                || clazz == Float.class
                || clazz == Boolean.class
                || clazz == Byte.class
                || clazz == Short.class
                || clazz == Character.class
                || clazz == String.class;
    }

    private static void copy_20250417(Object from, Object to, List<String> fieldNames, List<String> toFieldNames) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Source and target must not be null");
        }

        List<String> targetFieldNames = new ArrayList<>();

        if (to instanceof Map) {

            if (fieldNames != null && !fieldNames.isEmpty()) {
                targetFieldNames.addAll(fieldNames);
            } else if (from instanceof Map) {
                targetFieldNames.addAll(((Map<?, ?>) from).keySet().stream().map(Object::toString).toList());
            } else {
                for (Field field : from.getClass().getDeclaredFields()) {
                    targetFieldNames.add(field.getName());
                }
            }
        } else {

            if (toFieldNames != null && !toFieldNames.isEmpty()) {
                targetFieldNames.addAll(toFieldNames);
            } else {
                for (Field field : to.getClass().getDeclaredFields()) {
                    targetFieldNames.add(field.getName());
                }
            }
        }

        boolean hasCustomFromNames = (fieldNames != null && !fieldNames.isEmpty());

        for (int i = 0; i < targetFieldNames.size(); i++) {
            String toName = targetFieldNames.get(i);
            String fromName = hasCustomFromNames ? fieldNames.get(i) : toName;

            try {
                Object value = null;


                if (from instanceof Map) {
                    if (!((Map<?, ?>) from).containsKey(fromName)) continue;
                    value = ((Map<?, ?>) from).get(fromName);
                } else {
                    try {
                        Field sourceField = from.getClass().getDeclaredField(fromName);
                        sourceField.setAccessible(true);
                        value = sourceField.get(from);
                    } catch (NoSuchFieldException ignore) {
                        continue;
                    }
                }

                if (to instanceof Map) {
                    ((Map<String, Object>) to).put(toName, value);
                } else {
                    Field targetField;
                    try {
                        targetField = to.getClass().getDeclaredField(toName);
                    } catch (NoSuchFieldException e) {
                        continue;
                    }

                    targetField.setAccessible(true);

                    if (value == null) {
                        targetField.set(to, null);
                        continue;
                    }


                    if (List.class.isAssignableFrom(targetField.getType()) && value instanceof List) {
                        ParameterizedType genericType = (ParameterizedType) targetField.getGenericType();
                        Class<?> itemType = (Class<?>) genericType.getActualTypeArguments()[0];

                        List<?> sourceList = (List<?>) value;
                        List<Object> targetList = new ArrayList<>();

                        for (Object item : sourceList) {
                            if (item instanceof Map) {
                                Object targetItem = itemType.getDeclaredConstructor().newInstance();
                                copy(item, targetItem, null, null);
                                targetList.add(targetItem);
                            } else {
                                targetList.add(item);
                            }
                        }

                        targetField.set(to, targetList);
                    }

                    else if (value instanceof Map && !targetField.getType().isAssignableFrom(Map.class)) {
                        Object nestedObj = targetField.getType().getDeclaredConstructor().newInstance();
                        copy(value, nestedObj, null, null);
                        targetField.set(to, nestedObj);
                    }

                    else {
                        if (!targetField.getType().isAssignableFrom(value.getClass())) {
                            continue;
                        }
                        targetField.set(to, value);
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException("copy error: " + fromName + " -> " + toName, e);
            }
        }
    }




    public static void copy_2(Object from, Object to, List<String> fieldNames, List<String> toFieldNames) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Source and target objects must not be null");
        }

        List<String> fromNames = new ArrayList<>();
        if (fieldNames == null || fieldNames.isEmpty()) {
            if (from instanceof Map) {
                fromNames.addAll(((Map<?, ?>) from).keySet().stream()
                        .map(Object::toString)
                        .toList());
            } else {
                for (Field field : from.getClass().getDeclaredFields()) {
                    fromNames.add(field.getName());
                }
            }
        } else {
            fromNames.addAll(fieldNames);
        }

        boolean hasCustomTargetFields = (toFieldNames != null && !toFieldNames.isEmpty());
        for (int i = 0; i < fromNames.size(); i++) {
            String fromName = fromNames.get(i);
            String toName = hasCustomTargetFields ? toFieldNames.get(i) : fromName;

            try {
                Object value;

                if (from instanceof Map) {
                    value = ((Map<?, ?>) from).get(fromName);
                } else {
                    Field sourceField = from.getClass().getDeclaredField(fromName);
                    sourceField.setAccessible(true);
                    value = sourceField.get(from);
                }

                if (to instanceof Map) {
                    ((Map<String, Object>) to).put(toName, value);
                } else {
                    try {
                        Field targetField = to.getClass().getDeclaredField(toName);
                        targetField.setAccessible(true);

                        if (value != null && !targetField.getType().isAssignableFrom(value.getClass())) {
                            throw new IllegalArgumentException("Type mismatch: " + fromName + " -> " + toName);
                        }

                        targetField.set(to, value);
                    } catch (NoSuchFieldException ignore) {

                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Failed to copy: " + fromName + " -> " + toName, e);
            }
        }
    }


    /**
     * Copies values from one object to another object of a different class,
     * only for fields with matching names and compatible types.
     *
     * @param from The source object
     * @param to The target object
     * @throws IllegalArgumentException if from or to is null
     */
    public static void copy(Object from, Object to) {
        copy(from, to, null);
    }


}
