package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.*;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.ListMap;
import io.elasticore.base.model.core.RelationshipManager;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
import io.elasticore.base.util.CodeStringBuilder;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.StringUtils;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Properties;

public class ServiceSrcPublisher extends SrcFilePublisher {

    private CodeTemplate baseCodeTmpl;


    private CodePublisher publisher;
    private ECoreModel model;
    private RelationshipManager relationshipManager;


    private String packageName;
    private String entityPackageName;
    private String dtoPackageName;
    private String repoPackageName;

    ;


    public ServiceSrcPublisher(CodePublisher publisher) {
        this.model = publisher.getECoreModelContext().getDomain().getModel();

        if (model.getEntityModels().getItems().size() == 0 || model.getDataTransferModels().getItems().size() == 0)
            return;

        this.relationshipManager = RelationshipManager.getInstance(publisher.getECoreModelContext().getDomain().getName());

        this.packageName = model.getNamespace(ConstanParam.KEYNAME_SERVICE);

        if (this.packageName == null) return;

        this.entityPackageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);
        this.dtoPackageName = model.getNamespace(ConstanParam.KEYNAME_DTO);
        this.repoPackageName = model.getNamespace(ConstanParam.KEYNAME_REPOSITORY);

        this.publisher = publisher;

        this.baseCodeTmpl = createCodeTemplate(publisher, "template.service", "service.tmpl");

        System.err.println(this.baseCodeTmpl);

    }

    private DataTransfer findDTO(Entity entity) {
        String entityNm = entity.getIdentity().getName();
        List<ModelRelationship> relationshipList = relationshipManager
                .findByToNameAndType(entityNm, RelationType.TEMPLATE);

        for (ModelRelationship r : relationshipList) {
            String dtoName = r.getFromName();

            return this.model.getDataTransferModels().findByName(dtoName);
        }

        return null;
    }


    private boolean isPageable(DataTransfer searchDTO) {
        Annotation annotation = searchDTO.getMetaInfo().getMetaAnnotation("searchable");
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
            if(sb.length()>0) sb.append(" ,");
            sb.append("root.get(");
            sb.append(StringUtils.quoteString(f.getName()));
            sb.append(")");

        }

        return sb.toString();
    }



    public void publish(ModelDomain domain, Entity entity, DataTransfer dto, DataTransfer searchDto) {

        String domainName = domain.getName();
        String entityClassName = entity.getIdentity().getName();
        String className = entityClassName + ConstanParam.POSTFIX_SERVICE;

        String dtoClassName = dto.getIdentity().getName();
        String mapperName = findMapperClassName(dto);
        String searchDTOClassName = searchDto.getIdentity().getName();
        String pkType = entity.getPkField().getType();
        String pkName = entity.getPkField().getName();

        CodeStringBuilder cb = new CodeStringBuilder("{", "}");
        makeChildRefCode(dto, entity, cb);

        boolean isListOutput = false;
        boolean isPageOutput = false;

        if (isPageable(searchDto)) {
            isPageOutput = true;
        }else {
            isListOutput = true;
        }

        boolean isCustomPageOutput = false;
        boolean isCustomListOutput = false;
        String selectColumnNmList = "";

        String searchResultDtoNm = findSearchResultDTOName(this.relationshipManager, entity);
        if(searchResultDtoNm!=null) {
            if(isPageOutput) {
                isCustomPageOutput = true;
                isPageOutput = false;
            }else {
                isCustomListOutput = true;
                isListOutput = false;
            }

            selectColumnNmList = makeGetFieldInfo(searchResultDtoNm);
        }else {
            searchResultDtoNm="";
        }

        CodeTemplate.Parameters params = CodeTemplate.newParameters();
        params
                .set("className", className)
                .set("domainName", StringUtils.capitalize(domainName))
                .set("entityClassName", entityClassName)
                .set("dtoClassName", dtoClassName)
                .set("mapperName", mapperName)
                .set("searchDTOClassName", searchDTOClassName)
                .set("pkType", pkType)
                .set("pkName", StringUtils.capitalize(pkName))
                .set("childRefInfo", cb.toString())
                .set("isListOutput",isListOutput)
                .set("isPageOutput",isPageOutput)
                .set("isCustomPageOutput",isCustomPageOutput)
                .set("isCustomListOutput",isCustomListOutput)
                .set("customListDTOClassName",searchResultDtoNm)
                .set("selectColumnNmList" ,selectColumnNmList)



                .set("j2eePkgName",getPersistentPackageName(domain))
                .set("entityPackageName", entityPackageName)
                .set("dtoPackageName", dtoPackageName)
                .set("repositoryPackageName", repoPackageName)
                .set("packageName", packageName);

        String code = baseCodeTmpl.toString(params);

        String qualifiedClassName = packageName + "." + className;

        this.writeSrcCode(this.publisher, null, qualifiedClassName, code);
    }

    private void makeChildRefCode(DataTransfer dto, Entity entity, CodeStringBuilder cb) {


        cb.block("");
        cb.block("");


        ModelComponentItems<Field> items = dto.getItems();


        while (items.hasNext()) {
            Field f = items.next();
            // ex) Company.comSeq  || Company
            String refFieldNm = f.getAnnotationValue("reference", "ref");
            if (refFieldNm == null) continue;

            String[] refItems = refFieldNm.split("\\.");
            String refClassPkNm = null;
            if (refItems.length == 2) {
                refClassPkNm = refItems[1];
                refFieldNm = refItems[0];
            }

            Field targetField = entity.getItems().findByName(refFieldNm);
            if (targetField == null) continue;
            ;

            String getTypeNm = targetField.getTypeInfo().getDefaultTypeName();

            Entity targetEntity = findEntity(getTypeNm);
            if (targetEntity == null) continue;
            ;

            String refClassPkNm2 = targetEntity.getPkField().getName();


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

    private Entity findEntity(String entityName) {
        return this.model.getEntityModels().findByName(entityName);
    }


}
