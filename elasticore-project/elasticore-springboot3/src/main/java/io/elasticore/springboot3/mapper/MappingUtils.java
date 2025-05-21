package io.elasticore.springboot3.mapper;

import java.lang.reflect.*;
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


    private static List<String> extractFieldNames_old(Object obj) {
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



    private static List<String> extractFieldNames(Object obj) {
        return extractFieldNames(obj, null);
    }



    public static List<String> extractFieldNames(Object obj, String methodType) {
        Set<String> result = new LinkedHashSet<>();

        if (obj instanceof Map) {
            result.addAll(((Map<?, ?>) obj).keySet().stream()
                    .map(Object::toString)
                    .collect(Collectors.toSet()));
        } else {
            Class<?> clazz = obj.getClass();

            if ("get".equalsIgnoreCase(methodType) || "set".equalsIgnoreCase(methodType)) {
                for (Method method : clazz.getMethods()) {
                    String name = method.getName();
                    if (method.getParameterCount() == 0 && name.startsWith("get") && "get".equalsIgnoreCase(methodType)) {
                        result.add(decapitalize(name.substring(3)));
                    } else if (method.getParameterCount() == 1 && name.startsWith("set") && "set".equalsIgnoreCase(methodType)) {
                        result.add(decapitalize(name.substring(3)));
                    }
                }
            } else {
                for (Field field : clazz.getDeclaredFields()) {
                    result.add(field.getName());
                }
            }
        }

        return new ArrayList<>(result);
    }


    public static Map<String, Object> extractFieldNameMap(Object obj, String methodType) {
        Map<String, Object> result = new LinkedHashMap<>();

        if (obj instanceof Map) {
            ((Map<?, ?>) obj).keySet().forEach(k -> result.put(k.toString(), k.toString()));
        } else {
            Class<?> clazz = obj.getClass();

            for (Field field : clazz.getDeclaredFields()) {
                result.putIfAbsent(field.getName(), field);
            }

            if ("get".equalsIgnoreCase(methodType) || "set".equalsIgnoreCase(methodType)) {
                for (Method method : clazz.getMethods()) {
                    String name = method.getName();
                    if (method.getParameterCount() == 0 && name.startsWith("get") && "get".equalsIgnoreCase(methodType)) {
                        String fieldName = decapitalize(name.substring(3));
                        result.put(fieldName, method);
                    } else if (method.getParameterCount() == 1 && name.startsWith("set") && "set".equalsIgnoreCase(methodType)) {
                        String fieldName = decapitalize(name.substring(3));
                        result.put(fieldName, method);
                    }
                }
            }
        }

        return result;
    }


    private static String decapitalize(String name) {
        if (name == null || name.isEmpty()) return name;
        if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) && Character.isUpperCase(name.charAt(0))) {
            return name;
        }
        return Character.toLowerCase(name.charAt(0)) + name.substring(1);
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

        Map<String, Object> fromFieldInfoMap = extractFieldNameMap(from, "get");
        Map<String, Object> toFieldInfoMap = extractFieldNameMap(to, "set");

        if (isIncludeUndefined) {
            //List<String> fromFieldList = extractFieldNames(from, "get");
            //List<String> toFieldList = extractFieldNames(to ,"set");

            if(to instanceof Map) {

                for(String keyNm : fromFieldInfoMap.keySet()) {

                    if(!definedMappings.containsKey(keyNm)) {
                        definedMappings.put(keyNm, keyNm);
                        allTargetFieldNames.add(keyNm);
                    }

                }
            }else {
                for (String name : toFieldInfoMap.keySet()) {
                    if (!allTargetFieldNames.contains(name) && fromFieldInfoMap.containsKey(name)) {
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

                    Object refType = fromFieldInfoMap.get(fromName);

                    if(refType==null) {
                        continue;
                    }

                    else if(refType instanceof Field) {
                        Field sourceField = (Field) refType;
                        sourceField.setAccessible(true);
                        value = sourceField.get(from);

                    }
                    else if(refType instanceof Method) {
                        Method getter = (Method) refType;
                        value = getter.invoke(from);
                    }

                }

                if (to instanceof Map) {
                    ((Map<String, Object>) to).put(toName, value);
                } else {

                    Object refToType = toFieldInfoMap.get(toName);
                    Field targetField2 = null;
                    Method setter = null;

                    Class fieldType = null;
                    if(refToType==null) {
                        continue;
                    }
                    else if(refToType instanceof Field) {
                        targetField2 = (Field) refToType;
                        targetField2.setAccessible(true);

                        fieldType = targetField2.getType();


                    }
                    else if(refToType instanceof Method) {
                        setter  = (Method) refToType;

                        Class<?>[] parameterTypes = setter.getParameterTypes();
                        if(parameterTypes==null ||parameterTypes.length>1) {
                            continue;
                        }

                        fieldType = parameterTypes[0];
                    }


                    if (value == null) {
                        setValue(to, null, targetField2, setter);
                        continue;
                    }

                    if (fieldType !=null && List.class.isAssignableFrom(fieldType) && value instanceof List) {

                        ParameterizedType genericType = null;
                        if(targetField2!=null) {
                            genericType = (ParameterizedType) targetField2.getGenericType();
                        }
                        else {
                            Type parameterType = setter.getGenericParameterTypes()[0];
                            if(parameterType instanceof ParameterizedType) {
                                genericType = (ParameterizedType)parameterType;
                            }

                        }


                        Class<?> itemType = (Class<?>) genericType.getActualTypeArguments()[0];

                        List<?> sourceList = (List<?>) value;
                        List<Object> targetList = new ArrayList<>();

                        Class tmpType = null;
                        for (Object item : sourceList) {

                            if(tmpType==null && item!=null) {
                                tmpType = item.getClass();

                            }
                            boolean needClassMapping = false;
                            if (item instanceof Map) {
                                needClassMapping = true;
                            }else if(!isPrimitiveOrWrapperOrString(itemType) && itemType != tmpType) {
                                needClassMapping = true;
                            }


                            if (needClassMapping) {
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

                        setValue(to, targetList, targetField2, setter);
                        //targetField.set(to, targetList);

                    } else if (value instanceof Map && !fieldType.isAssignableFrom(Map.class)) {
                        Object nestedObj = fieldType.getDeclaredConstructor().newInstance();
                        copy(value, nestedObj, null, null, isIncludeUndefined);
                        setValue(to, nestedObj, targetField2, setter);
                        //targetField.set(to, nestedObj);

                    } else {
                        /*
                        if (!targetField.getType().isAssignableFrom(value.getClass())) {
                            continue;
                        }
                         */

                        //Class targetClass =targetField.getType();
                        if( !isPrimitiveOrWrapperOrString(fieldType) && fieldType!= value.getClass()) {
                            if(fieldType.isArray()) {
                                Class<?> componentType = fieldType.getComponentType();

                                if (value instanceof List) {
                                    List<?> list = (List<?>) value;
                                    Object array = java.lang.reflect.Array.newInstance(componentType, list.size());

                                    for (int i = 0; i < list.size(); i++) {
                                        Object elem = list.get(i);
                                        java.lang.reflect.Array.set(array, i, elem);
                                    }
                                    setValue(to, array, targetField2, setter);
                                    //targetField.set(to, array);
                                }

                            }else {
                                Object nestedObj = fieldType.newInstance();
                                copy(value, nestedObj, null, null, isIncludeUndefined);
                                setValue(to, nestedObj, targetField2, setter);
                                //targetField.set(to, nestedObj);
                            }

                        }else {
                            setValue(to, value, targetField2, setter);
                            //targetField.set(to, value);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("copy error: " + fromName + " -> " + toName, e);
            }
        }
    }


    private static void setValue(Object target, Object value, Field field, Method method) {
        try {
            if (method != null) {
                method.invoke(target, value);
            }
            else if (field != null) {
                field.set(target, value);
            }
            else if( target instanceof  Map) {
                Map tartgetMap = (Map) target;
            }
        }catch (RuntimeException | IllegalAccessException | InvocationTargetException re) {

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
