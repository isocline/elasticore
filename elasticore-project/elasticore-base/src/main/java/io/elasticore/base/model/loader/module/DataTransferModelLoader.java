package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstantParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferAnnotation;
import io.elasticore.base.model.entity.EntityAnnotation;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.entity.TypeInfo;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.util.ConsoleLog;
import io.elasticore.base.util.StringUtils;

import java.util.*;

public class DataTransferModelLoader extends AbstractModelLoader implements ConstantParam, ModelLoader<DataTransfer> {


    public boolean loadModel(ModelLoaderContext ctx, FileSource source) {
        return loadModel(ctx, source.getInfoMap());
    }

    public boolean loadModel(ModelLoaderContext ctx, Map<String, Map> map) {


        if (map.containsKey(ConstantParam.KEYNAME_DTO)) {
            Map entityMap = map.get(ConstantParam.KEYNAME_DTO);
            loadModel(ctx, ctx.getDataTransferItems(), entityMap);
        }

        if (map.containsKey(ConstantParam.KEYNAME_ENTITY)) {
            Map entityMap = map.get(ConstantParam.KEYNAME_ENTITY);
            loadModel(ctx, ctx.getDataTransferItems(), entityMap);
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


    /**
     * transform meta data to extend function
     *
     * @param entityMap
     */
    protected void transfer(Map<String, Object> entityMap) {
        String meta = (String) entityMap.get("meta");
        if(meta!=null) {
            if(meta.indexOf("@dto")>=0  || meta.indexOf("@pageable")>=0  || meta.indexOf("@searchable")>=0 )
                return;

            meta = meta.replaceAll("@expose\\b", "@expose @dto @searchable @pageable");
            entityMap.put("meta", meta);
        }
    }


    protected List<DataTransfer> loadDataTransfer(ModelLoaderContext ctx, String entityNm, Map<String, Object> entityMap) {

        transfer(entityMap);
        List<DataTransfer> result = new ArrayList<>();

        MetaInfo metaInfo = parseMetaInfoObject(entityMap, KEYNAME_DTO, entityNm);

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

                            String newFieldType = field.getTypeInfo().getDefaultTypeName();

                            if(!fieldTypeInfo.isList()) {
                                // 20241213 Enum 을 선별할기 전까지
                                //if(ctx.getEnumModelItems().findByName(fieldTypeInfo.getInitTypeInfo())!=null)
                                {
                                    isProcess = false;
                                }

                                isProcess = true;
                            }else {
                                isProcess = false;

                                String dtoType = field.getAnnotationValue(EntityAnnotation.DTO_TYPE);
                                if(StringUtils.hasValue(dtoType)) {
                                    //TypeInfo dtoTypeInfo = new TypeInfo(dtoType);

                                    newFieldType = dtoType;
                                    isProcess = true;
                                }


                            }

                            if(isProcess) {

                                Annotation annotation = Annotation.create(EntityAnnotation.META_DEFERRED);
                                Map<String, Annotation> annotationMap = field.getAnnotationMap();
                                annotationMap.put(annotation.getName(), annotation);

                                items.addItem(
                                        Field.builder()
                                                .name(field.getName())
                                                .parentMetaInfo(metaInfo)
                                                .type(newFieldType)
                                                .annotationMap(annotationMap).build()
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
                        String mainClassNm = entityNm+"ResultDTO";
                        MetaInfo metaInfo2 = parseMetaInfoObject(entityMap ,"dto", mainClassNm);
                        result.add(DataTransfer.create(ctx.getDomainId(), mainClassNm, items, metaInfo2));

                    }
                }


                boolean isPagable = metaInfo.hasMetaAnnotation(EntityAnnotation.META_PAGEABLE);

                String pageSizeVal = metaInfo.getMetaAnnotationValue(EntityAnnotation.META_PAGEABLE);
                if(pageSizeVal==null)
                    pageSizeVal = "20";


                Object metaInfoObj = entityMap.get(PROPERTY_META);

                if(metaInfoObj instanceof String) {
                    //String lineData = (String) entityMap.get(PROPERTY_META) +" @dto";
                    String lineData = "entity @dto @"+EntityAnnotation.AUTOGENERATED+" ";

                    String expoeAnnotation = "@"+EntityAnnotation.META_EXPOSE;
                    if(metaInfoObj.toString().indexOf(expoeAnnotation)>=0) {
                        lineData = lineData+" "+expoeAnnotation;
                    }

                    String newLineData = lineData +String.format("@template(%s)",entityNm);


                    entityMap.put(PROPERTY_META, newLineData);
                    String mainClassNm = entityNm+"DTO";
                    metaInfo = parseMetaInfoObject(entityMap ,"dto", mainClassNm);
                    result.add(DataTransfer.create(ctx.getDomainId(), mainClassNm, items, metaInfo));


                    if(isPagable) {
                        newLineData = lineData +
                                String.format("@searchable(entity=%s, pageSize=%s)", entityNm,pageSizeVal);
                    }else {
                        newLineData = lineData +String.format("@searchable(%s)", entityNm);
                    }



                    entityMap.put(PROPERTY_META, newLineData);
                    String mainClassName = entityNm+"SrchDTO";
                    metaInfo = parseMetaInfoObject(entityMap ,"dto", mainClassName);
                    result.add(DataTransfer.create(ctx.getDomainId(), mainClassName, items, metaInfo));

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
