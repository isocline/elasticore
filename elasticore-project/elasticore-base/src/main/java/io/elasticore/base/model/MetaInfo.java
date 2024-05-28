package io.elasticore.base.model;

import io.elasticore.base.model.core.Annotation;

import java.util.Locale;
import java.util.Map;

public class MetaInfo {

    private Map<String, Annotation> infoAnnotationMap;


    private Map<String, Annotation> metaAnnotationMap;

    private MetaInfo() {
        this.infoAnnotationMap = null;
        this.metaAnnotationMap = null;

    }

    private MetaInfo(Map<String, Annotation> infoAnnotationMap, Map<String, Annotation> metaAnnotationMap) {
        this.infoAnnotationMap = infoAnnotationMap;
        this.metaAnnotationMap = metaAnnotationMap;
    }

    public static MetaInfo creat(Map<String, Annotation> infoAnnotationMap, Map<String, Annotation> metaAnnotationMap) {
        return new MetaInfo(infoAnnotationMap, metaAnnotationMap);
    }

    public static MetaInfo createEmpty(){
        return new MetaInfo();
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

    public Map<String, Annotation> getMetaAnnotationMap() {
        return metaAnnotationMap;
    }

    public Annotation getMetaAnnotation(String name) {
        if (metaAnnotationMap == null || name ==null) {
            return null;
        }
        String key = name.toLowerCase(Locale.ROOT);
        return metaAnnotationMap.get(key);
    }

    public boolean hasMetaAnnotation(String name) {
        if (metaAnnotationMap == null|| name ==null) {
            return false;
        }
        String key = name.toLowerCase(Locale.ROOT);
        return metaAnnotationMap.containsKey(key);
    }

    public String getMetaAnnotationValue(String... names) {
        for(String name: names) {
            Annotation an = getMetaAnnotation(name);
            if(an!=null) {
                String val = an.getValue();
                if(val !=null) return val;
            }
        }

        return null;
    }
}
