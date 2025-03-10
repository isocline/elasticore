package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.EntityAnnotation;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.util.ConsoleLog;
import io.elasticore.base.util.StringUtils;

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


    protected ModelLoaderContext modelLoaderContext;

    protected Pattern fieldNamePattern;

    protected void setModelLoaderContext(ModelLoaderContext modelLoaderContext) {
        this.modelLoaderContext = modelLoaderContext;
        String fieldRegx = modelLoaderContext.getConfig("fieldRegx", "^[a-zA-Z_$][a-zA-Z\\d_$]*$");
        fieldNamePattern = Pattern.compile(fieldRegx);
    }


    protected boolean isValidFieldName(String fieldName) {
        if (fieldName == null || fieldName.isEmpty()) {
            return false;
        }
        return fieldNamePattern.matcher(fieldName).matches();
    }

    /**
     * Parses field information from a map where the key is the field name and the value is the field's properties.
     *
     * @param fieldInfo Map containing field names as keys and field properties as values.
     * @return Items containing Field objects parsed from the input map.
     */
    protected Items<Field> parseField(Map<String, String> fieldInfo, String entityName) {
        return parseField(fieldInfo, entityName, null);
    }
    /**
     * Parses field information from a map where the key is the field name and the value is the field's properties.
     *
     * @param fieldInfo Map containing field names as keys and field properties as values.
     * @param entityName String
     * @param metaInfo MetaInfo
     * @return Items containing Field objects parsed from the input map.
     */
    protected Items<Field> parseField(Map<String, String> fieldInfo, String entityName, MetaInfo metaInfo) {
        Items<Field> items = Items.create(Field.class);

        fieldInfo.forEach((fieldNm, fieldPropery) -> {
            Field f = parseFieldLine(fieldNm, fieldPropery ,metaInfo);

            if(entityName!= null && !isValidFieldName(f.getName())) {
                ConsoleLog.storeLog("FIELD_NM_ERR" , entityName+"."+fieldNm);
            }
            items.addItem(f);
        });

        return items;
    }

    protected Items<Field> parseField(Map<String, String> fieldInfo) {
        return parseField(fieldInfo, null);
    }


    /**
     * Parses annotation parameters from a string representation.
     *
     * @param annotationName The name of the annotation.
     * @param parameters The string containing all parameters for the annotation.
     * @return An Annotation object populated with the parsed data.
     */
    protected Annotation parseParameters(String annotationName, String parameters) {

        String singleValue = null;
        Properties properties = new Properties();

        if(parameters.indexOf("=")<0) {
            singleValue = parameters;
        }else {
            Pattern kvPattern = Pattern.compile("(\\w+)\\s*=\\s*([^,]+)|([^,\\s]+)");
            Matcher kvMatcher = kvPattern.matcher(parameters);

            while (kvMatcher.find()) {
                if (kvMatcher.group(1) != null) {
                    String key = kvMatcher.group(1);
                    String value = kvMatcher.group(2);
                    value = StringUtils.removeQuotes(value);
                    properties.setProperty(key, value);
                } else if (kvMatcher.group(3) != null) {
                    singleValue = kvMatcher.group(3);
                }
            }
        }


        return Annotation.create(annotationName, singleValue, properties);
    }




    /**
     * Parses meta-information about an entity from a map representation.
     *
     * @param entityMap
     * @param type
     * @param mainClassName
     * @return
     */
    protected MetaInfo parseMetaInfoObject(Map<String, Object> entityMap, String type, String mainClassName) {
        if(entityMap ==null)
            entityMap = new HashMap<>();

        Object infoObj = entityMap.get(PROPERTY_INFO);
        Map<String, Annotation> infoAnnotation = getAnnotationObj(infoObj);


        Object metaInfoObj = entityMap.get(PROPERTY_META);
        Map<String, Annotation> metaAnnotation = null;
        if(metaInfoObj==null) {
            metaAnnotation = new HashMap<>();
            if(type!=null)
                metaAnnotation.put("type", Annotation.create("type",type) );
        }else{
            metaAnnotation = getAnnotationObj(metaInfoObj);
        }

        if(mainClassName!=null) {
            metaAnnotation.put("name", Annotation.create("name",mainClassName) );
        }


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

    private String replaceEnvVariables(String input) {

        if(modelLoaderContext==null)
            return input;


        StringBuilder result = new StringBuilder();
        String[] tokens = input.split(" ");

        for (String token : tokens) {
            if (token.startsWith("@env:")) {
                String key = token.substring(5);
                String val = modelLoaderContext.getConfig("annotations."+key);
                if (val!=null) {
                    result.append(" @").append(val);
                } else {
                    result.append(" ").append(token);
                }
            } else {
                result.append(" ").append(token);
            }
        }

        return result.toString().trim();
    }

    /**
     * Loads a map of annotations from a string that represents multiple annotations.
     *
     * @param fieldLine The string containing annotations.
     * @return A map of annotation names to Annotation objects.
     */
    protected Map<String, Annotation> loadAnnotationMap(String fieldLine) {

        fieldLine = replaceEnvVariables(fieldLine);

        boolean chk = false;
        int p0 = fieldLine.indexOf(" --");
        if(p0<0) {
            p0 = fieldLine.indexOf("--");
            if(p0!=0) {
                p0 = -1;
            }else{
                chk = true;
            }

        }
        if(p0>=0) {
            String comment =null;
            if(chk)
                comment = fieldLine.substring(p0+2).trim();
            else
                comment = fieldLine.substring(p0+3).trim();
            if(!comment.isEmpty() && comment.indexOf("@")<0) {
                fieldLine = fieldLine.substring(0,p0);

                String label = comment;
                String desc = null;
                if(comment.indexOf(" | ")>0) {
                    String[] items = comment.split(" \\| ");
                    if(items.length==2) {
                        label = items[0];
                        desc = items[1];
                    }
                }
                fieldLine = fieldLine +" @label("+label+")";
                if(desc!=null)
                    fieldLine = fieldLine +" @desc("+desc+")";
            }
        }

        //Pattern pattern = Pattern.compile("@(\\w+)(?:\\((.*?)\\))?");
        //Pattern pattern = Pattern.compile("@(\\w+(?::\\w+)?)(?:\\((.*?)\\))?");
        //Pattern pattern = Pattern.compile("@(\\w+(?::\\w+)?)(?:\\('([^']*?)'\\))?");
        //Pattern pattern = Pattern.compile("@(\\w+(?::\\w+)?)(?:\\(('([^']*?)'|[^()]*?)\\))?");
        //Pattern pattern = Pattern.compile("@(\\w+(?::\\w+)?)(?:\\(([^()]*(?:\\([^)]*\\))?[^()]*)\\))?");

        //Pattern pattern = Pattern.compile("@([\\w:.]+(?::\\w+)?)(?:\\(([^()]*(?:\\([^)]*\\))?[^()]*)\\))?");
        //Pattern pattern = Pattern.compile("@([\\w:.]+)\\s*\\((.*)\\)", Pattern.DOTALL);

        //Pattern pattern = Pattern.compile("@([\\w:.]+(?:[:]\\w+)?)(?:\\((([^()]|\"[^\"]*\")*?)\\))?",Pattern.DOTALL);
        Pattern pattern = Pattern.compile("@([\\w:.]+(?:[:]\\w+)?)(?:\\((([^()]*|\\([^()]*\\)|\"[^\"]*\")*?)\\))?", Pattern.DOTALL);









        Matcher matcher = pattern.matcher(fieldLine);

        Map<String, Annotation> annotationMap = new HashMap<>();
        while (matcher.find()) {
            String annotationName = matcher.group(1);
            String attributeParameters = matcher.group(2);
            int p = annotationName.indexOf(":");
            if(p<0) {
                annotationName = annotationName.toLowerCase(Locale.ROOT);
            }

            Annotation annotation = null;
            if(p>0) {
                String orginAnnotationName = annotationName.substring(p+1);
                String annotationFullTxt = "@"+orginAnnotationName;
                if(attributeParameters!=null && attributeParameters.length()>0) {
                    annotationFullTxt= "@"+orginAnnotationName+"("+attributeParameters+")";

                }
                annotation = Annotation.create(annotationName, annotationFullTxt, null);
            }
            else if (attributeParameters != null && !attributeParameters.isEmpty()) {
                annotation = parseParameters(annotationName, attributeParameters);
            } else {
                annotation = Annotation.create(annotationName);

            }
            Annotation preAnnot = annotationMap.get(annotation.getName());
            if(preAnnot!=null) {
                preAnnot.getSiblings().add(annotation);
            }else {
                annotationMap.put(annotation.getName(), annotation);
            }

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
        return parseFieldLine(fieldNm, fieldLine, null);
    }

    /**
     * Parses a single field line to extract field information and annotations.
     *
     * @param fieldNm The name of the field.
     * @param fieldLine The string representation of the field and its annotations.
     * @param metaInfo MetaInfo
     * @return A Field object populated with the parsed information.
     */
    protected Field parseFieldLine(String fieldNm, String fieldLine ,MetaInfo metaInfo) {
        if("--".equals(fieldLine)) {
            fieldLine = "undefined @disable";
        }

        // for comment
        /*
        int p0 = fieldLine.indexOf(" --");
        if(p0>0) {
            String comment = fieldLine.substring(p0+3).trim();
            if(!comment.isEmpty()) {
                fieldLine = fieldLine.substring(0,p0);
                fieldLine = fieldLine +" @label("+comment+")";
            }
        }

         */


        String[] parts = fieldLine.split(" ", 2);
        String type = parts[0];
        fieldLine = parts.length > 1 ? parts[1] : "";



        // for require
        int p =type.indexOf("!");
        if(p>0) {
            type = type.replace("!", "");
            fieldLine = fieldLine +" @notnull";
        }
        // for length
        int p2 =type.indexOf("(");
        if(p2>0) {
            String lenInfo = type.substring(p2);
            type = type.substring(0,p2);
            fieldLine = "@length"+lenInfo +" "+fieldLine;
        }

        if("longtext".equals(type)) {
            type="string";
            fieldLine = "@longtext "+fieldLine;
        }
        else if("text".equals(type)) {
            type="string";
            fieldLine = "@text "+fieldLine;
        }
        else if("HashMap".equals(type)) {
            type="java.util.HashMap";
        }
        else if("Object".equals(type)) {
            type="java.lang.Object";
        }




        Map<String, Annotation> annotationMap = loadAnnotationMap(fieldLine);


        // Automatically adds the "search" annotation if the "dto" annotation is defined in the entity
        if(metaInfo!=null && metaInfo.hasMetaAnnotation(EntityAnnotation.META_DTO)) {
            if( !annotationMap.containsKey("s") && !annotationMap.containsKey("searchable")) {
                annotationMap.put(EntityAnnotation.AUTOSEARCH, Annotation.create(EntityAnnotation.AUTOSEARCH));
                if("string".equals(type.toLowerCase())) {
                    annotationMap.put("searchable", Annotation.create("searchable"));
                }else {
                    annotationMap.put("searchable", Annotation.create("searchable", "eq"));
                }
            }
        }

        return Field.builder().name(fieldNm)
                .type(type)
                .parentMetaInfo(metaInfo)
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
