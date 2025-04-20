package io.elasticore.springboot3.mapper;

import java.util.ArrayList;
import java.util.*;
import java.util.Map;
public class Mapper<T> {

    private final Object from;
    private final Object to;
    private final Class<T> toClass;

    private final Map<String, String> fieldMap = new LinkedHashMap<>();
    private final Map<String, MappingGuard> fieldMappingGuardMap = new HashMap<>();
    private List<String> fromKeyNmList;
    private List<String> toKeyNmList;
    private boolean skipNull = false;
    private boolean autoMap = true;

    private boolean isIncludeUndefined = true;

    private Mapper(Object from, Object to) {
        this.from = from;
        this.to = to;
        this.toClass = null;
    }

    private Mapper(Object from, Class<T> toClass) {
        this.from = from;
        this.to = null;
        this.toClass =toClass;
    }

    private Mapper() {
        this.from = null;
        this.to = null;
        this.toClass = null;
    }


    public static Mapper<Object> of(Object from, Object to) {
        return new Mapper(from, to);
    }

    public static <T> Mapper<T> of(Object from, Class<T> toClass) {
        return new Mapper(from, toClass);
    }

    public static Mapper<Object> ofEmpty() {
        return new Mapper();
    }


    public Mapper<T> includeUndefinedMap(boolean includeUndefined) {
        this.isIncludeUndefined = includeUndefined;
        return this;
    }

    public Mapper<T> on(String fromFieldName, MappingGuard mappingGuard) {
        fieldMappingGuardMap.put(fromFieldName, mappingGuard);
        return this;
    }

    public Mapper<T> map(String...fieldNames) {

        if(fromKeyNmList==null)
            fromKeyNmList = new ArrayList<>();
        if(toKeyNmList==null)
            toKeyNmList = new ArrayList<>();

        for(String fieldName : fieldNames) {
            fromKeyNmList.add(fieldName);
            toKeyNmList.add(fieldName);
        }

        this.isIncludeUndefined = false;
        return this;
    }


    public Mapper<T> map(String fromField, String toField) {
        fieldMap.put(fromField, toField);
        if(fromKeyNmList==null)
            fromKeyNmList = new ArrayList<>();
        if(toKeyNmList==null)
            toKeyNmList = new ArrayList<>();

        fromKeyNmList.add(fromField);
        toKeyNmList.add(toField);
        return this;
    }

    public Mapper<T> skipNull(boolean skip) {
        this.skipNull = skip;
        return this;
    }

    private Mapper<T> autoMap(boolean autoMap) {
        this.autoMap = autoMap;
        return this;
    }

    public static void copy(Object src, Object to) {
        MappingUtils.copy(src, to);
    }

    public <T> T execute() {
        if (from == null || (to == null && toClass == null)) {
            throw new IllegalArgumentException("Both source and target must be non-null");
        }

        if (from == to) {
            throw new IllegalArgumentException("Both source and target is same.");
        }

        Object toObject;
        if (toClass != null) {
            try {
                toObject = toClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Cannot create instance of target class: " + toClass.getName(), e);
            }
        } else {
            toObject = to;
        }
        MappingUtils.copy(from, toObject, fromKeyNmList, toKeyNmList, isIncludeUndefined);
        return (T) toObject;
    }

    public <T> List<T> toList() {

        if(from instanceof List<?>) {
            List originList = (List) from;
            List<T> result = new ArrayList<>(originList.size());

            for (Object source : originList) {
                try {
                    T target = (T) toClass.getDeclaredConstructor().newInstance();
                    MappingUtils.copy(source, target,  fromKeyNmList, toKeyNmList, isIncludeUndefined);
                    result.add(target);
                } catch (ReflectiveOperationException e) {
                    throw new IllegalArgumentException("Failed to create instance of " + toClass.getName(), e);
                }
            }
            return result;

        }else {

        }

        return null;
    }



}

