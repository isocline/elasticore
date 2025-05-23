package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.*;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.ListMap;
import io.elasticore.base.model.core.RelationshipManager;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferAnnotation;
import io.elasticore.base.model.entity.EntityAnnotation;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
import io.elasticore.base.util.CodeStringBuilder;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ServiceSrcPublisher extends SrcFilePublisher {

    private CodeTemplate baseCodeTmpl;

    private CodeTemplate extendSvcCodeTmpl;


    private CodePublisher publisher;
    private ECoreModel model;
    private RelationshipManager relationshipManager;


    private String packageName;
    private String entityPackageName;
    private String dtoPackageName;
    private String repoPackageName;

    private boolean isDefaultGen = true;


    public ServiceSrcPublisher(CodePublisher publisher) {
        super(publisher);

        String config = publisher.getECoreModelContext().getDomain().getModel().getConfig("defaultGeneration.service", "true");
        if("false".equals(config))
            isDefaultGen = false;

        this.model = publisher.getECoreModelContext().getDomain().getModel();

        if (model.getEntityModels().getItems().size() == 0 || model.getDataTransferModels().getItems().size() == 0)
            return;

        this.relationshipManager = RelationshipManager.getInstance(publisher.getECoreModelContext().getDomain().getName());

        this.packageName = model.getNamespace(ConstantParam.KEYNAME_SERVICE);

        if (this.packageName == null) return;

        this.entityPackageName = model.getNamespace(ConstantParam.KEYNAME_ENTITY);
        this.dtoPackageName = model.getNamespace(ConstantParam.KEYNAME_DTO);
        this.repoPackageName = model.getNamespace(ConstantParam.KEYNAME_REPOSITORY);

        this.publisher = publisher;

        this.baseCodeTmpl = createCodeTemplate(publisher, "template.service", "service.tmpl");

        this.extendSvcCodeTmpl = createCodeTemplate(publisher, "template.serviceExt", "service_extend.tmpl");

        //System.err.println(this.baseCodeTmpl);

    }



    private boolean isPageable(DataTransfer searchDTO) {
        Annotation annotation = searchDTO.getMetaInfo().getMetaAnnotation(DataTransferAnnotation.META_SEARCHABLE);
        if (annotation == null) return false;

        Properties props = annotation.getProperties();
        if (props == null) return false;
        if (props.getProperty("pageSize") == null) return false;

        return true;

    }


    private DataTransfer findSearchDTO(Entity entity) {
        String entityNm = entity.getIdentity().getName();
        List<ModelRelationship> relationshipList = relationshipManager
                .findByToNameAndType(entityNm, RelationType.SEARCHABLE);

        for (ModelRelationship r : relationshipList) {
            String dtoName = r.getFromName();

            return this.model.getDataTransferModels().findByName(dtoName);
        }

        return null;
    }

    private String findMapperClassName(DataModelComponent model) {
        List<ModelRelationship> relationshipList = relationshipManager
                .findByFromNameAndType(model.getIdentity().getName(), RelationType.MAPPER);
        for (ModelRelationship r : relationshipList) {
            return r.getToName();
        }
        return null;
    }

    /**
     * @param domain
     */
    public void publish(ModelDomain domain) {

        if (this.baseCodeTmpl == null) return;

        ModelComponentItems<Entity> entities = this.model.getEntityModels().getItems();

        while (entities.hasNext()) {
            Entity entity = entities.next();
            DataTransfer dto = findDTO(entity);
            if (dto == null) continue;

            DataTransfer searchDTO = findSearchDTO(entity);
            if (searchDTO == null) continue;


            publish(domain, entity, dto, searchDTO);
        }
    }

    private String makeGetFieldInfo(String searchResultDtoNm ) {
        StringBuilder sb = new StringBuilder();

        DataTransfer dto = this.model.getDataTransferModels().findByName(searchResultDtoNm);
        if(dto ==null) return sb.toString();

        ListMap<String, Field>  listMap = dto.getAllFieldListMap();

        //root.get("comSeq")
        for(Field f:listMap.getList()) {
            if(isDisableField(f))
                continue;
            String coreItemType = f.getTypeInfo().getCoreItemType();
            if(this.findEntityByName(coreItemType)!=null) continue;

            if(sb.length()>0) sb.append("\n ,");
            sb.append("root.get(");
            sb.append(StringUtils.quoteString(f.getName()));
            sb.append(")");

        }

        return sb.toString();
    }



    public void publish(ModelDomain domain, Entity entity, DataTransfer dto, DataTransfer searchDto) {

        //this.isDefaultGen
        boolean hasApiAnt = entity.getMetaInfo().hasMetaAnnotation(EntityAnnotation.META_SERVICE);
        String isGen = entity.getMetaInfo().getInfoAnnotationValue(EntityAnnotation.META_SERVICE);
        if("false".equals(isGen) || (!hasApiAnt && !this.isDefaultGen))
            return;


        if(entity.getPkField() ==null)
            return;

        String domainName = domain.getName();

        String orgEntityClassName = entity.getIdentity().getName();
        String entityClassName = targetEntityName(entity); //for rollup
        String mappingCode ="";
        String orgEntityConvert= "";
        if(!orgEntityClassName.equals(entityClassName)) {

            orgEntityConvert = "("+orgEntityClassName+")";
            String varName = StringUtils.uncapitalize(entityClassName);
            mappingCode = String.format(".filter(%s -> %s instanceof %s).map(%s -> (%s) %s)",
                    varName,varName,orgEntityClassName,varName,orgEntityClassName,varName);
        }

        String keyDtoClassName = orgEntityClassName+"Key"+"DTO";

        String className = orgEntityClassName + ConstantParam.POSTFIX_SERVICE;

        String dtoClassName = dto.getIdentity().getName();
        String mapperName = findMapperClassName(dto);
        String searchDTOClassName = searchDto.getIdentity().getName();
        String pkType = entity.getPkField().getType();
        String pkName = entity.getPkField().getName();

        Set<String> namespaceSet = new HashSet<>();


        CodeStringBuilder cb = new CodeStringBuilder("{", "}");
        makeChildRefCode(dto, entity, cb,  namespaceSet);

        boolean isListOutput = false;
        boolean isPageOutput = false;
        boolean isExposeOutput = false;

        if (isPageable(searchDto)) {
            isPageOutput = true;
        }else {
            isListOutput = true;
        }

        boolean isCustomPageOutput = false;
        boolean isCustomListOutput = false;
        String selectColumnNmList = "";

        String toSearchListMappingName = "toDTO";

        String searchResultDTOClassName = findSearchResultDTOName(this.relationshipManager, entity);
        if(searchResultDTOClassName!=null) {
            toSearchListMappingName = "to"+searchResultDTOClassName;
            /*
            if(isPageOutput) {
                isCustomPageOutput = true;
                isPageOutput = false;
            }else {
                isCustomListOutput = true;
                isListOutput = false;
            }

            selectColumnNmList = makeGetFieldInfo(searchResultDTOClassName);
             */
        }else {
            searchResultDTOClassName=dtoClassName;
        }







        boolean isSkipNull = true;
        if( "false".equals(entity.getMetaInfo().getMetaAnnotationValue("dynamicupdate"))) {
            isSkipNull = false;
        }

        StringBuilder sb = new StringBuilder(); // for DTO
        StringBuilder sb2 = new StringBuilder(); // for field
        StringBuilder sb3 = new StringBuilder(); // for declare

        List<Field> fieldList = entity.getAllFieldListMap().getList();

        for(Field field: fieldList) {
            if(!field.isPrimaryKey()) continue;
            if(sb.length()>0) {
                sb.append(" ,");
                sb2.append(" ,");
                sb3.append(" ,");
            }

            // dto.get${pkName}()
            sb.append("dto.get").append(StringUtils.capitalize(field.getName())).append("()");
            sb2.append(field.getName());
            sb3.append(field.getTypeInfo().getDefaultTypeName()).append(" ").append(field.getName());
        }

        String pkDtoInfo = null;
        String pkDtoParamInfo = sb.toString();
        String pkDtoInfo2 = null;
        String pkDtoDefine = sb3.toString();
        if(entity.getPkField().isMultiple()) {

            pkDtoInfo = "new "+entity.getPkField().getType()+"("+pkDtoParamInfo+")";
            pkDtoInfo2 = "new "+entity.getPkField().getType()+"("+sb2.toString()+")";

        }else {
            pkDtoInfo = sb.toString();
            pkDtoInfo2 = sb2.toString();
        }

        if(entity.getMetaInfo().hasMetaAnnotation(EntityAnnotation.META_EXPOSE)) {
            isExposeOutput = true;
            isPageOutput = false;
            isListOutput = false;
        }

        CodeTemplate.Paragraph importList = CodeTemplate.newParagraph();
        for(String ns: namespaceSet) {
            importList.add(ns);
        }

        CodeTemplate.Parameters params = CodeTemplate.newParameters();
        params
                .set("className", className)
                .set("domainName", StringUtils.capitalize(domainName))
                .set("entityClassName", entityClassName)
                .set("orgEntityClassName", orgEntityClassName)
                .set("orgEntityConvert", orgEntityConvert)
                .set("importList",importList)


                .set("keyDtoClassName", keyDtoClassName)
                .set("dtoClassName", dtoClassName)
                .set("mappingCode", mappingCode)

                .set("mapperName", mapperName)
                .set("searchDTOClassName", searchDTOClassName)
                .set("pkType", pkType)
                .set("pkName", StringUtils.capitalize(pkName))

                // dto.get${pkName}()

                .set("pkDtoParamInfo", pkDtoParamInfo)
                .set("pkDtoInfo", pkDtoInfo)
                .set("pkDtoInfo2", pkDtoInfo2)
                .set("pkDtoDefine", pkDtoDefine)

                .set("childRefInfo", cb.toString())
                .set("isListOutput",isListOutput)
                .set("isPageOutput",isPageOutput)
                .set("isExposeOutput",isExposeOutput)


                .set("isCustomPageOutput",isCustomPageOutput)
                .set("isCustomListOutput",isCustomListOutput)
                .set("isSkipNull",isSkipNull)


                .set("searchResultDTOClassName",searchResultDTOClassName)
                .set("selectColumnNmList" ,selectColumnNmList)
                .set("toSearchListMappingName", toSearchListMappingName)



                .set("j2eePkgName",getPersistentPackageName(domain))
                .set("entityPackageName", entityPackageName)
                .set("dtoPackageName", dtoPackageName)
                .set("repositoryPackageName", repoPackageName)
                .set("packageName", packageName);

        String code = baseCodeTmpl.toString(params);

        String qualifiedClassName = packageName + "." + className;

        this.writeSrcCode(this.publisher, null, qualifiedClassName, code);

        // for extended Service Code
        if(entity.getMetaInfo().hasMetaAnnotation(EntityAnnotation.META_EXPOSE)) {
            String extCode = this.extendSvcCodeTmpl.toString(params);
            String extQualifiedClassName = packageName + "." + orgEntityClassName + ConstantParam.POSTFIX_EXT_SERVICE;

            this.writeSrcCode(this.publisher, null, extQualifiedClassName, extCode);

        }
    }

    private void makeChildRefCode(DataTransfer dto, Entity entity, CodeStringBuilder cb, Set<String> namespaceSet) {

        cb.block("");
        cb.block("");

        ModelComponentItems<Field> items = dto.getItems();


        while (items.hasNext()) {
            Field f = items.next();
            // ex) Company.comSeq  || Company
            String refFieldNm = f.getAnnotationValue(EntityAnnotation.REFERENCE);
            if (refFieldNm == null) continue;

            String[] refItems = refFieldNm.split("\\.");
            String refClassPkNm = null;
            if (refItems.length == 2) {
                refClassPkNm = refItems[1];
                refFieldNm = refItems[0];
            }

            Field targetField = entity.getItems().findByName(refFieldNm);
            if (targetField == null) continue;

            String getTypeNm = targetField.getTypeInfo().getDefaultTypeName();

            Entity targetEntity = findEntityByName(getTypeNm);
            if (targetEntity == null) continue;
            String ns = getNamespace(getTypeNm);
            if(ns!=null) {
                namespaceSet.add(ns+"."+getTypeNm);
            }



            String refClassPkNm2 = (targetEntity.getPkField()!=null)?targetEntity.getPkField().getName():null;

            if (refClassPkNm == null || !refClassPkNm.equals(refClassPkNm2)) {
                continue;
            }

            String extraDtoFieldName = "";
            if (!f.getTypeInfo().isBaseType()) {
                extraDtoFieldName = ".get" + StringUtils.capitalize(refClassPkNm2) + "()";
            }

            String dtoGetName = StringUtils.capitalize(f.getName());

            String getDtoFieldName = StringUtils.capitalize(f.getName());

            String fieldInfo = "";
            String entitySetName = findFieldNameByTypeName(entity, refFieldNm);


            cb.line("if(dto.get%s()!=null)", getDtoFieldName);
            cb.block();
            cb.line("%s item = helper.get%s().findById(dto.get%s()%s).orElse(null);"
                    , getTypeNm, getTypeNm, dtoGetName, extraDtoFieldName);
            cb.line("if(item!=null) entity.set%s(item);", StringUtils.capitalize(refFieldNm));

            cb.end();

            //System.err.println(cb.toString());

        }

        cb.end("");
        cb.end("");
    }


}
