package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Field;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Abstract base class for model loaders.
 * Provides common functionalities to parse field information, annotations, and meta-information from entity maps.
 */
public class AbstractModelLoader implements ConstanParam {


    /**
     * Parses field information from a map where the key is the field name and the value is the field's properties.
     *
     * @param fieldInfo Map containing field names as keys and field properties as values.
     * @return Items containing Field objects parsed from the input map.
     */
    protected Items<Field> parseField(Map<String, String> fieldInfo) {
        Items<Field> items = Items.create(Field.class);

        fieldInfo.forEach((fieldNm, fieldPropery) -> {
            Field f = parseFieldLine(fieldNm, fieldPropery);
            items.addItem(f);
        });

        return items;
    }


    /**
     * Parses annotation parameters from a string representation.
     *
     * @param annotationName The name of the annotation.
     * @param parameters The string containing all parameters for the annotation.
     * @return An Annotation object populated with the parsed data.
     */
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
            } else if (kvMatcher.group(3) != null) {
                singleValue = kvMatcher.group(3);
            }
        }

        return Annotation.create(annotationName, singleValue, properties);
    }

    /**
     * Parses meta-information about an entity from a map representation.
     *
     * @param entityMap The map containing entity information including meta-information.
     * @return A MetaInfo object populated with parsed annotations and other meta-information.
     */
    protected MetaInfo parseMetaInfoObject(Map<String, Object> entityMap) {
        Object infoObj = entityMap.get(PROPERTY_INFO);
        Map<String, Annotation> infoAnnotation = getAnnotationObj(infoObj);


        Object metaInfoObj = entityMap.get(PROPERTY_META);
        Map<String, Annotation> metaAnnotation = getAnnotationObj(metaInfoObj);

        return MetaInfo.creat(infoAnnotation, metaAnnotation);
    }

    /**
     * Extracts annotation objects from various types of inputs.
     *
     * @param metaInfoObj The object containing meta-information which could be a String or a Map.
     * @return A map of annotation names to Annotation objects.
     */
    protected Map<String, Annotation> getAnnotationObj(Object metaInfoObj) {
        Map<String, Annotation> annotationMap = null;
        if(metaInfoObj instanceof String) {
            String line = metaInfoObj.toString();

            if(line!=null && line.trim().length()>0) {
                annotationMap = loadAnnotationMap(line);

                if(line.indexOf("@")!=0) {
                    String[] parts = line.split(" ", 2);

                    annotationMap.put("type", Annotation.create("type", parts[0],null) );
                }
            }



        }
        else if(metaInfoObj instanceof Map) {
            Map<String, String>  propertyMap = (Map) metaInfoObj;
            annotationMap = new HashMap<>();
            for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
                String nm = entry.getKey().toLowerCase(Locale.ROOT);
                try {

                    Annotation annotation = Annotation.create(nm, entry.getValue(), null);
                    annotationMap.put(nm, annotation);
                }catch (ClassCastException cce) {
                    cce.printStackTrace();
                }
            }
        }

        if(annotationMap!=null)
            return annotationMap;

        return null;
    }


    /**
     * Loads a map of annotations from a string that represents multiple annotations.
     *
     * @param fieldLine The string containing annotations.
     * @return A map of annotation names to Annotation objects.
     */
    protected Map<String, Annotation> loadAnnotationMap(String fieldLine) {

        Pattern pattern = Pattern.compile("@(\\w+)(?:\\((.*?)\\))?");
        Matcher matcher = pattern.matcher(fieldLine);

        Map<String, Annotation> annotationMap = new HashMap<>();
        while (matcher.find()) {
            String annotationName = matcher.group(1).toLowerCase(Locale.ROOT);
            String attributeParameters = matcher.group(2);

            Annotation annotation = null;
            if (attributeParameters != null && !attributeParameters.isEmpty()) {
                annotation = parseParameters(annotationName, attributeParameters);
            } else {
                annotation = Annotation.create(annotationName);

            }
            annotationMap.put(annotation.getName(), annotation);
        }
        return annotationMap;
    }

    /**
     * Parses a single field line to extract field information and annotations.
     *
     * @param fieldNm The name of the field.
     * @param fieldLine The string representation of the field and its annotations.
     * @return A Field object populated with the parsed information.
     */
    protected Field parseFieldLine(String fieldNm, String fieldLine) {
        String[] parts = fieldLine.split(" ", 2);
        String type = parts[0];
        fieldLine = parts.length > 1 ? parts[1] : "";


        Map<String, Annotation> annotationMap = loadAnnotationMap(fieldLine);

        return Field.builder().name(fieldNm)
                .type(type)
                .isPrimaryKey(annotationMap.containsKey("id"))
                .unique(annotationMap.containsKey("unique"))
                .annotationMap(annotationMap).build();

    }

    /**
     * Placeholder method for completing the load process.
     * Override this method in subclasses to implement specific finalization steps if needed.
     */
    public void completeLoad() {
        // This method can be overridden to perform final steps after loading is complete
    }
}
