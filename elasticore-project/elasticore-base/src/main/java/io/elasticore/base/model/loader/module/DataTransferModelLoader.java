package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferAnnotation;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityAnnotation;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.entity.TypeInfo;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.util.ConsoleLog;
import io.elasticore.base.util.StringUtils;

import java.util.*;

public class DataTransferModelLoader extends AbstractModelLoader implements ConstanParam, ModelLoader<DataTransfer> {


    public boolean loadModel(ModelLoaderContext ctx, FileSource source) {
        return loadModel(ctx, source.getInfoMap());
    }

    public boolean loadModel(ModelLoaderContext ctx, Map<String, Map> map) {


        if (map.containsKey(ConstanParam.KEYNAME_DTO)) {
            Map entityMap = map.get(ConstanParam.KEYNAME_DTO);
            loadModel(ctx, ctx.getDataTransferItems(), entityMap);
        }

        if (map.containsKey(ConstanParam.KEYNAME_ENTITY)) {
            Map entityMap = map.get(ConstanParam.KEYNAME_ENTITY);
            loadModel(ctx, ctx.getDataTransferItems(), entityMap);
        }

        if(ctx.getDataTransferItems().size()>0) {
            List<DataTransfer> itemList = ctx.getDataTransferItems().getItemList();
            for(DataTransfer dto : itemList) {

                ModelComponentItems<Field> items = dto.getItems();
                while(items.hasNext()) {
                    Field f = items.next();
                    //if(f==null) break;

                    String baseFieldNm = f.getName();

                    if(f.hasAnnotation(DataTransferAnnotation.META_DEFERRED)) {

                        String typeName = f.getTypeInfo().getDefaultTypeName();
                        Entity entity = ctx.getEntityItems().findByName(typeName);

                        if(entity !=null) {

                            Annotation att = Annotation.create(  DataTransferAnnotation.META_SEARCHABLE_BYPASS );
                            Map<String,Annotation> antMp = new HashMap<>();
                            antMp.put(att.getName(), att);

                            if(!dto.getMetaInfo().hasMetaAnnotation(DataTransferAnnotation.META_SEARCHABLE)) {
                                Field dtoField = Field.builder()
                                        .name(f.getName())
                                        .type(typeName+"DTO")
                                        .annotationMap(antMp)
                                        .build();

                                dto.addField(dtoField);
                            }


                            ModelComponentItems<Field> entityFields = entity.getItems();
                            while (entityFields.hasNext()) {
                                Field entityField = entityFields.next();
                                if(entityField.hasAnnotation(EntityAnnotation.META_ID)) {

                                    String refFieldNm = baseFieldNm+"."+ entityField.getName();
                                    String newFieldNm = baseFieldNm+StringUtils.capitalize(entityField.getName());

                                    Annotation annotation = Annotation.create(  "ref", refFieldNm);
                                    Map<String,Annotation> antMap = new HashMap<>();
                                    antMap.put(annotation.getName(), annotation);

                                    Annotation.create(  "s", "eq");
                                    antMap.put(annotation.getName(), annotation);


                                    Field newRefField = Field.builder()
                                            .name(newFieldNm)
                                            .type(entityField.getTypeInfo().getDefaultTypeName())
                                            .annotationMap(antMap)
                                            .build();

                                    dto.addField(newRefField);



                                }
                            }
                        }

                    }


                }

            }

            return true;
        }

        return false;
    }

    public void loadModel(ModelLoaderContext ctx, Items<DataTransfer> items, Map<String, LinkedHashMap> dtoInfoMap) {

        this.setModelLoaderContext(ctx);

        dtoInfoMap.forEach((entityNm, value) -> {
            List<DataTransfer> dtos = loadDataTransfer(ctx, entityNm, value);
            for(DataTransfer dto: dtos) {
                if( items.find(dto.getIdentity()) == null) {
                    items.addItem(dto);
                }
                else {
                    ConsoleLog.printWarn("Duplicate identity found for " + dto.getIdentity().getName());
                }
            }
        });
    }


    protected List<DataTransfer> loadDataTransfer(ModelLoaderContext ctx, String entityNm, Map<String, Object> entityMap) {

        List<DataTransfer> result = new ArrayList<>();

        MetaInfo metaInfo = parseMetaInfoObject(entityMap);

        Map fields = (Map) entityMap.get(PROPERTY_FIELDS);
        Items<Field> fieldItems = null;
        if (fields != null)
            fieldItems = parseField(fields, entityNm ,metaInfo);


        // Handles the "dto" annotation declared within the entity model
        if( "entity".equals(metaInfo.getMetaAnnotationValue(EntityAnnotation.META_TYPE))) {

            if( metaInfo.hasMetaAnnotation(EntityAnnotation.META_DTO)) {

                Items<Field> items = Items.create(Field.class);

                // for fields
                if(fieldItems!=null) {
                    List<Field> itemList = fieldItems.getItemList();
                    for(Field field : itemList) {

                        TypeInfo fieldTypeInfo = field.getTypeInfo();

                        if(!fieldTypeInfo.isBaseType()) {

                            boolean isProcess = true;

                            if(!fieldTypeInfo.isList()) {
                                if(ctx.getEnumModelItems().findByName(fieldTypeInfo.getInitTypeInfo())!=null) {
                                    isProcess = false;
                                }
                            }else {
                                isProcess = false;
                            }

                            if(isProcess) {
                                Annotation annotation = Annotation.create(EntityAnnotation.META_DEFERRED);
                                Map<String, Annotation> annotationMap = new HashMap<>();
                                annotationMap.put(annotation.getName(), annotation);

                                items.addItem(
                                        Field.builder().name(field.getName()).type(field.getTypeInfo().getDefaultTypeName()).annotationMap(annotationMap).build()
                                );

                            /*
                            items.addItem(
                                    Field.builder().name(field.getName()).type(field.getTypeInfo().getDefaultTypeName()+"DTO").build()
                            );

                             */
                            }


                        }

                    }
                }

                // for @searchResult
                if(metaInfo.hasMetaAnnotation(DataTransferAnnotation.META_SEARCH_RESULT)) {
                    Object metaInfoObj = entityMap.get(PROPERTY_META);

                    if(metaInfoObj instanceof String) {
                        //String lineData = (String) entityMap.get(PROPERTY_META) +" @dto";
                        String lineData = "entity @dto";

                        String newLineData = lineData +String.format("@searchResult(%s)",entityNm);


                        entityMap.put(PROPERTY_META, newLineData);
                        MetaInfo metaInfo2 = parseMetaInfoObject(entityMap);
                        result.add(DataTransfer.create(ctx.getDomainId(), entityNm+"ResultDTO", items, metaInfo2));

                    }
                }


                boolean isPagable = metaInfo.hasMetaAnnotation(EntityAnnotation.META_PAGEABLE);

                String pageSizeVal = metaInfo.getMetaAnnotationValue(EntityAnnotation.META_PAGEABLE);
                if(pageSizeVal==null)
                    pageSizeVal = "20";


                Object metaInfoObj = entityMap.get(PROPERTY_META);

                if(metaInfoObj instanceof String) {
                    //String lineData = (String) entityMap.get(PROPERTY_META) +" @dto";
                    String lineData = "entity @dto";

                    String newLineData = lineData +String.format("@template(%s)",entityNm);


                    entityMap.put(PROPERTY_META, newLineData);
                    metaInfo = parseMetaInfoObject(entityMap);
                    result.add(DataTransfer.create(ctx.getDomainId(), entityNm+"DTO", items, metaInfo));


                    if(isPagable) {
                        newLineData = lineData +
                                String.format("@searchable(entity=%s, pageSize=%s)", entityNm,pageSizeVal);
                    }else {
                        newLineData = lineData +String.format("@searchable(%s)", entityNm);
                    }



                    entityMap.put(PROPERTY_META, newLineData);
                    metaInfo = parseMetaInfoObject(entityMap);
                    result.add(DataTransfer.create(ctx.getDomainId(), entityNm+"SrchDTO", items, metaInfo));

                    return result;
                }


            }
            else {
                return result;
            }
        }

        result.add(DataTransfer.create(ctx.getDomainId(), entityNm, fieldItems, metaInfo));
        return result;
    }



}
