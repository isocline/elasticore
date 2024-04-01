package io.elasticore.base.model;

import io.elasticore.base.model.core.Annotation;

import java.util.Map;

public class MetaInfo {

    private Map<String, Annotation> infoAnnotationMap;


    private Map<String, Annotation> metaAnnotationMap;

    private MetaInfo(Map<String, Annotation> infoAnnotationMap, Map<String, Annotation> metaAnnotationMap) {
        this.infoAnnotationMap = infoAnnotationMap;
        this.metaAnnotationMap = metaAnnotationMap;
    }

    public static MetaInfo creat(Map<String, Annotation> infoAnnotationMap, Map<String, Annotation> metaAnnotationMap) {
        return new MetaInfo(infoAnnotationMap, metaAnnotationMap);
    }

    public Annotation getInfoAnnotation(String name) {
        if (infoAnnotationMap == null) {
            return null;
        }

        return infoAnnotationMap.get(name);
    }

    public boolean hasInfoAnnotation(String name) {
        if (infoAnnotationMap == null) {
            return false;
        }
        return infoAnnotationMap.containsKey(name);
    }

    public Annotation getMetaAnnotation(String name) {
        if (metaAnnotationMap == null) {
            return null;
        }

        return metaAnnotationMap.get(name);
    }

    public boolean hasMetaAnnotation(String name) {
        if (metaAnnotationMap == null) {
            return false;
        }
        return metaAnnotationMap.containsKey(name);
    }
}
