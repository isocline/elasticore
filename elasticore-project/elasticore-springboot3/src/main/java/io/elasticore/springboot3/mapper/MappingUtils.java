package io.elasticore.springboot3.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MappingUtils {


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
    public static void copy(Object from, Object to, List<String> fieldNames, List<String> toFieldNames) {
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
