package io.elasticore.base.model.loader.module;

import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityAnnotation;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.ModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.util.ConsoleLog;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        if(ctx.getDataTransferItems().size()>0)
            return true;

        return false;
    }

    public void loadModel(ModelLoaderContext ctx, Items<DataTransfer> items, Map<String, LinkedHashMap> entityMap) {

        this.setModelLoaderContext(ctx);

        entityMap.forEach((entityNm, value) -> {
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
            fieldItems = parseField(fields, entityNm);


        if( "entity".equals(metaInfo.getMetaAnnotationValue(EntityAnnotation.META_TYPE))) {

            if( metaInfo.hasMetaAnnotation(EntityAnnotation.META_DTO)) {

                boolean isPagable = metaInfo.hasMetaAnnotation(EntityAnnotation.META_PAGEABLE);

                String pageSizeVal = metaInfo.getMetaAnnotationValue(EntityAnnotation.META_PAGEABLE);
                if(pageSizeVal==null)
                    pageSizeVal = "20";


                Object metaInfoObj = entityMap.get(PROPERTY_META);

                if(metaInfoObj instanceof String) {
                    String lineData = (String) entityMap.get(PROPERTY_META) +" @dto";

                    String newLineData = lineData +String.format("@template(%s)",entityNm);


                    entityMap.put(PROPERTY_META, newLineData);
                    metaInfo = parseMetaInfoObject(entityMap);
                    result.add(DataTransfer.create(ctx.getDomainId(), entityNm+"DTO", null, metaInfo));


                    if(isPagable) {
                        newLineData = lineData +
                                String.format("@searchable(entity=%s, pageSize=%s)", entityNm,pageSizeVal);
                    }else {
                        newLineData = lineData +String.format("@searchable(%s)", entityNm);
                    }



                    entityMap.put(PROPERTY_META, newLineData);
                    metaInfo = parseMetaInfoObject(entityMap);
                    result.add(DataTransfer.create(ctx.getDomainId(), entityNm+"SrchDTO", null, metaInfo));

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
