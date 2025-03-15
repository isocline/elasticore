//ecd:
package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SourceFileAccessFactory;
import io.elasticore.base.model.*;
import io.elasticore.base.model.core.AbstractDataModel;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.BaseECoreModelContext;
import io.elasticore.base.model.core.RelationshipManager;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferAnnotation;
import io.elasticore.base.model.entity.EntityAnnotation;
import io.elasticore.base.model.entity.BaseFieldType;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.ConsoleLog;
import io.elasticore.base.util.HashUtils;
import io.elasticore.base.util.StringUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SrcFilePublisher {


    private CodePublisher publisher;

    protected SrcFilePublisher(CodePublisher publisher) {
        this.publisher = publisher;
    }

    private void log(String msg) {
        System.err.println(msg);
    }

    private static String userHome = System.getProperty("user.dir");

    public String getFilePathInfo(String qualifiedClassName) {
        String path = publisher.getSrcFileAccessFactory().getFilePath(qualifiedClassName);

        Path input = Paths.get(path);
        Path home = Paths.get(userHome);
        if (input.startsWith(home)) {
            Path relativePath = home.relativize(input);
            return "." + File.separator + relativePath.toString();
        } else {
            return path;
        }
    }

    protected void writeSrcCode(CodePublisher publisher, ModelComponent modelComponent, String qualifiedClassName, String content) {
        writeSrcCode(publisher, modelComponent, qualifiedClassName, content, true);
    }

    protected void writeSrcCode(CodePublisher publisher, ModelComponent modelComponent, String qualifiedClassName, String content, boolean checkModified) {
        SourceFileAccessFactory fileAccessFactory = publisher.getSrcFileAccessFactory();


        HashUtils.Response response = null;
        try (Reader reader = fileAccessFactory.getReader(qualifiedClassName)) {
            response = HashUtils.checkContentModified(reader);


            if (response != null && response.getStatus() == HashUtils.MODIFIED) {


                if (checkModified && !response.hasCustomizedScopeContent()) {
                    //ConsoleLog.printInfo("[WARN] PUB_SKIP: " + qualifiedClassName + " ** USER_MODIFIED **");
                    //ConsoleLog.storeLog("USER_MODIFIED", getFilePathInfo(qualifiedClassName) +" "+response.getErrMsg());
                    ConsoleLog.storeLog("USER_MODIFIED", getFilePathInfo(qualifiedClassName));
                    return;
                }
                //ConsoleLog.printInfo("[WARN] IGNORE: " + qualifiedClassName + " ** USER_MODIFIED **");
                ConsoleLog.storeLog("USER_MODIFIED", getFilePathInfo(qualifiedClassName) + " [INGORE]");

            }

        } catch (FileNotFoundException fnfe) {
        } catch (Throwable e) {
            e.printStackTrace();
        }

        boolean isEqaulsWithPureChars = false;
        if (response != null)
            isEqaulsWithPureChars = StringUtils.equalsWithPureChars(content, response.getPreSourceContent());

        //if (response != null && isEqaulsWithPureChars && !response.hasCustomizedScopeContent()) {
        if (response != null && isEqaulsWithPureChars) {
            //ConsoleLog.printInfo("[INFO] PUB_SKIP: " + qualifiedClassName + " NO_MODIFIED");
            ConsoleLog.storeLog("NO_MODIFIED", getFilePathInfo(qualifiedClassName));

            return;
        }


        try (Writer writer = fileAccessFactory.getWriter(qualifiedClassName)) {


            if (response != null) {

                String oldEcdLine = response.getOldEcdLine();

                if (oldEcdLine != null && isEqaulsWithPureChars) {
                    writer.write(oldEcdLine + "\n");

                } else {

                    String hCode = HashUtils.makeEcdCode(content);
                    writer.write(hCode);
                }


                if (!response.hasCustomizedScopeContent()) {
                    writer.write(content);
                } else {
                    // content를 라인별로 분리
                    String[] lines = content.split("\n");
                    StringBuilder modifiedContent = new StringBuilder();


                    for (int i = 0; i < lines.length; i++) {

                        if (i == lines.length - 1) {
                            if (response.getCustomizedScopeContent() != null) {
                                writer.write(response.getCustomizedScopeContent());
                                writer.write("\n");
                            }
                        }

                        writer.write(lines[i]);
                        writer.write("\n");

                        if (i == 1) {
                            if (response.getCustomizedScopeContent4Header() != null) {
                                writer.write(response.getCustomizedScopeContent4Header());
                                writer.write("\n");
                            }
                        }
                    }
                }


            } else {
                writer.write(HashUtils.makeEcdCode(content));
                writer.write(content);
            }


            writer.flush();
        } catch (Throwable e) {
            e.printStackTrace();
            if (modelComponent != null)
                publisher.errorOnPublish(modelComponent, e);
            else
                e.printStackTrace();
        }
    }


    protected String getPath(String packageName) {
        return packageName.replace('.', '/');
    }


    public String getPersistentPackageName(ModelDomain domain) {
        if(domain==null) {
            domain = this.publisher.getECoreModelContext().getDomain();
        }

        try {
            if ("jakarta".equals(domain.getModel().getConfig("j2ee")))
                return "jakarta";
        } catch (RuntimeException re) {
        }

        return "javax";
    }


    protected CodeTemplate createCodeTemplate(CodePublisher publisher, String configPath, String defaultFileName) {
        String templatePath = publisher.getECoreModelContext().getDomain().getModel().getConfig(configPath);
        if (templatePath == null)
            templatePath = "elasticore-template/" + defaultFileName;
        else
            templatePath = "resource://" + templatePath;

        return CodeTemplate.newInstance(templatePath);
    }


    protected boolean isEntityReturnType(Field f, ECoreModel eCoreModel) {
        if (!f.getTypeInfo().isBaseType()) {

            String parameterType = f.getTypeInfo().getDefaultTypeName();
            if (f.getTypeInfo().isGenericType())
                parameterType = f.getTypeInfo().getTypeParameterName();

            //if (eCoreModel.getEntityModels().findByName(parameterType) != null) {

            Entity entityModel = this.publisher.getECoreModelContext().findModelComponent(parameterType, Entity.class);
            if (entityModel != null) {
                if (!entityModel.getMetaInfo().hasMetaAnnotation(EntityAnnotation.META_EMBEDDABLE)) {
                    // skip
                    return true;
                }
            }
        }

        return false;
    }

    protected Field findFieldByTypeName(AbstractDataModel entity, String typeName) {
        if (typeName == null) return null;

        ModelComponentItems<Field> items = entity.getItems();
        while (items.hasNext()) {
            Field field = items.next();

            if (typeName.equals(field.getTypeInfo().getDefaultTypeName())) {
                return field;
            }
        }

        return null;
    }

    protected String findFieldNameByTypeName(AbstractDataModel entity, String typeName) {
        Field f = findFieldByTypeName(entity, typeName);
        if (f != null)
            return f.getName();

        return null;
    }


    protected String findSearchResultDTOName(RelationshipManager rm, Entity entity) {
        List<ModelRelationship> relationshipList = rm.findByFromNameAndType(
                entity.getIdentity().getName(), RelationType.SEARCH_RESULT);

        for (ModelRelationship r : relationshipList) {
            return r.getToName();
        }

        return null;
    }


    protected void setFieldDesc(Field field, CodeTemplate.Paragraph paragraph) {
        if (field.getDescription() != null) {
            paragraph.add("/*");
            paragraph.add("  " + field.getDescription());
            paragraph.add("*/");
        } else {
            String description = field.getAnnotationValue(EntityAnnotation.COMMENT);
            if (description != null) {
                paragraph.add("/*");
                paragraph.add("  " + description);
                paragraph.add("*/");
            }
        }


        String required = field.getAnnotationValue(EntityAnnotation.CALCULATION_REQUIRED);
        if ("true".equals(required))
            paragraph.add("// calcRequired:" + required);

    }


    protected void setFieldDocumentation(Field f, CodeTemplate.Paragraph p) {

        String desc = f.getAnnotationValue(EntityAnnotation.DESCRIPTION);
        if (desc == null) {
            desc = f.getName();
        }
        boolean isNotNull = f.hasAnnotation(EntityAnnotation.NOT_NULL);
        String requireTxt = "";
        if (isNotNull) {
            requireTxt = ", requiredMode=Schema.RequiredMode.REQUIRED";
        }

        String example = getExample(f);
        p.add("@Schema(description = \"%s\" %s %s)", desc, requireTxt, example);
    }

    protected void setFieldDocumentation(Field f, CodeTemplate.Paragraph p, String[] conditions, boolean enableSearch) {

        String conditionTxt = "";
        if (enableSearch) {
            if (conditions != null && conditions.length > 0) {
                conditionTxt = StringUtils.getOperatorDescription(conditions[0]) + " field:" + f.getName();
            } else {
                conditionTxt = StringUtils.getOperatorDescription("") + " field:" + f.getName();
            }
        }


        String desc = f.getAnnotationValue(EntityAnnotation.DESCRIPTION);
        if (desc == null) {
            desc = conditionTxt;
        } else {
            desc = desc + " " + conditionTxt;
        }
        boolean isNotNull = f.hasAnnotation(EntityAnnotation.NOT_NULL);
        String requireTxt = "";
        if (isNotNull) {
            requireTxt = ", requiredMode=Schema.RequiredMode.REQUIRED";
        }

        String example = getExample(f);
        p.add("@Schema(description = \"%s\" %s %s)", desc, requireTxt, example);
    }

    protected String getExample(Field f) {
        String example = f.getAnnotationValue(EntityAnnotation.EXAMPLE);

        if (example == null) {

            EnumModel enumModel = this.publisher.getECoreModelContext().getDomain()
                    .getModel()
                    .getEnumModels()
                    .findByName(f.getTypeInfo().getCoreItemType());

            if (enumModel != null) {
                String fieldNameForJson = enumModel.getMetaInfo().getMetaAnnotationValue("json");

                int jsonIdx = -1;
                int chkIdx = 0;
                for (Field ef : enumModel.getFieldItems().getItemList()) {
                    if (ef.getName().equals(fieldNameForJson)) {
                        jsonIdx = chkIdx;
                        break;
                    }
                    chkIdx++;
                }

                StringBuilder sb = new StringBuilder();

                List<EnumConstant> itemList = enumModel.getEnumConstantItems().getItemList();
                for (EnumConstant e : itemList) {
                    List<EnumConstant.ConstructParam> constructParamList = e.getConstructParamList();
                    if (sb.length() > 0)
                        sb.append(" | ");

                    if (jsonIdx < 0) {
                        sb.append(e.getIdentity().getName());
                    } else {
                        sb.append(constructParamList.get(jsonIdx).getName());
                    }
                    sb.append(":");

                    for (int i = 0; i < constructParamList.size(); i++) {

                        if (i != jsonIdx) {
                            sb.append(" ");
                            sb.append(constructParamList.get(i));
                        }
                    }
                }

                example = sb.toString();
            }

        }

        if (example == null) {
            String format = f.getAnnotationValue(EntityAnnotation.FORMAT);
            if (format == null) {
                if (f.getTypeInfo().getBaseFieldType() == BaseFieldType.DATE
                        || f.getTypeInfo().getBaseFieldType() == BaseFieldType.LocalDate) {
                    if (format == null)
                        format = "yyyy-MM-dd";

                } else if (f.getTypeInfo().getBaseFieldType() == BaseFieldType.DATETIME
                        || f.getTypeInfo().getBaseFieldType() == BaseFieldType.LocalDateTime) {
                    if (format == null)
                        format = "yyyy-MM-dd HH:mm:ss";

                }
            }

            example = format;
        }


        if (example != null) {
            example = ", example=\"" + StringUtils.escapeQuotes(example) + "\"";
        } else {
            example = "";
        }
        return example;
    }


    protected void setFieldValidation(Field f, CodeTemplate.Paragraph p) {

        if (f.hasAnnotation("notnull") && !f.hasAnnotation("dto:jakarta.validation.constraints.NotNull"))
            p.add("@NotNull");

        if (f.hasAnnotation("notblank"))
            p.add("@NotBlank");

        if (f.hasAnnotation(DataTransferAnnotation.PATTERN) && !f.hasAnnotation("dto:jakarta.validation.constraints.Pattern")) {
            String regexp = f.getAnnotationValue(DataTransferAnnotation.PATTERN_REGEX);
            if(regexp != null && regexp.length() > 0) {
                String msg = f.getAnnotationValue(DataTransferAnnotation.PATTERN_MSG);
                if(msg !=null && msg.length()>0) {
                    String persistentPackageName = getPersistentPackageName(null);

                    p.add("@"+persistentPackageName+".validation.constraints.Pattern(");
                    p.add(" regexp=\""+regexp+"\",");
                    p.add(" msg=\""+msg+"\"");
                    p.add(")");
                }
            }

        }


        String minSize = f.getAnnotationValue(EntityAnnotation.MIN_SIZE);
        String maxSize = f.getAnnotationValue(EntityAnnotation.LENGTH);
        if (minSize != null || maxSize != null) {
            StringBuilder sb = new StringBuilder();
            if (minSize != null)
                sb.append("min=").append(minSize);
            if (maxSize != null) {
                if (sb.length() > 0)
                    sb.append(" , ");
                sb.append("max=").append(maxSize);
            }

            p.add("@Size(%s)", sb.toString());
        }

    }


    protected boolean setJsonInfo(Field f, CodeTemplate.Paragraph p) {

        boolean isApplied = false;
        String jsonName = f.getAnnotationValue(EntityAnnotation.JSON_NAME);
        if (jsonName != null) {
            p.add("@JsonProperty(\"%s\")", jsonName);
            isApplied = true;
        }

        if (f.hasAnnotation("jsonignore") || f.hasAnnotation("ignore")) {
            p.add("@JsonIgnore");
            isApplied = true;
        }

        String jsonSerialize = f.getAnnotationValue(EntityAnnotation.JSON_SERIALIZE_USE);
        String jsonDeserialize = f.getAnnotationValue(EntityAnnotation.JSON_DESERIALIZE_USE);

        if (jsonSerialize != null) {
            p.add("@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = %s)", jsonSerialize);

        }

        if (jsonDeserialize != null) {
            p.add("@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = %s)", jsonDeserialize);

        }

        return isApplied;
    }

    protected boolean isEnumerationType(Field f) {
        String typeName = f.getTypeInfo().getCoreItemType();
        if (this.publisher.getECoreModelContext().getDomain().getModel().getEnumModels().findByName(typeName) != null)
            return true;

        return false;
    }

    protected Entity findEntityByField(Field f) {
        String typeName = f.getTypeInfo().getCoreItemType();
        return findEntityByName(typeName);
    }

    protected Entity findEntityByName(String typeName) {
        return this.publisher.getECoreModelContext().findModelComponent(typeName, Entity.class);
    }

    protected EnumModel findEnumModel(String enumName) {
        return this.publisher.getECoreModelContext().findModelComponent(enumName, EnumModel.class);
    }


    protected boolean isEntityType(Field f) {
        if (findEntityByField(f) != null)
            return true;
        return false;
    }


    protected void setFunctionInfo(Field f, CodeTemplate.Paragraph p) {
        String getFunc = f.getAnnotationValue(EntityAnnotation.FUNCTION_GET);
        String setFunc = f.getAnnotationValue(EntityAnnotation.FUNCTION_SET);

        String fldNm = f.getName();
        String cFldNm = StringUtils.capitalize(fldNm);
        String type = f.getTypeInfo().getDefaultTypeName();


        if (getFunc != null) {
            getFunc = getFunc.trim();
            if (!"null".equals(getFunc) && getFunc.indexOf("(") < 0) {
                getFunc = getFunc + "(this)";
            }
            if (getFunc.indexOf(";") < 0)
                getFunc = getFunc + ";";
            p.add("public %s get%s() {", type, cFldNm);
            p.add("    return %s", getFunc);
            p.add("}");
            p.add("");
        }

        if (setFunc != null) {
            if (setFunc.indexOf("(") < 0) {
                setFunc = setFunc.trim() + "(this)";
            }
            p.add("public void set%s(%s val) {", cFldNm, type);
            p.add("    this.%s = val;", fldNm);
            p.add("    %s;", setFunc);
            p.add("}");
            p.add("");
        }
    }

    protected void setFormatAnnotation(Field field, CodeTemplate.Paragraph paragraph) {

        String format = field.getAnnotationValue(EntityAnnotation.FORMAT);
        if (field.getTypeInfo().getBaseFieldType() == BaseFieldType.DATE
                || field.getTypeInfo().getBaseFieldType() == BaseFieldType.LocalDate) {
            if (format == null)
                format = "yyyy-MM-dd";
            paragraph.add("@org.springframework.format.annotation.DateTimeFormat(pattern = \"%s\")", format);
            paragraph.add("@com.fasterxml.jackson.annotation.JsonFormat(pattern = \"%s\")", format);
        } else if (field.getTypeInfo().getBaseFieldType() == BaseFieldType.DATETIME
                || field.getTypeInfo().getBaseFieldType() == BaseFieldType.LocalDateTime) {
            if (format == null)
                format = "yyyy-MM-dd HH:mm:ss";
            paragraph.add("@org.springframework.format.annotation.DateTimeFormat(pattern = \"%s\")", format);
            paragraph.add("@com.fasterxml.jackson.annotation.JsonFormat(pattern = \"%s\")", format);
        }

    }


    protected String targetEntityName(Entity targetEntity) {

        String rollupTargetNm = targetEntity.getMetaInfo().getMetaAnnotationValue(EntityAnnotation.META_ROLL_UP_TARGET);
        if (rollupTargetNm == null) {
            return targetEntity.getIdentity().getName();
        }

        Entity rollupEntity = findEntityByName(rollupTargetNm);
        if (rollupEntity == null)
            return null;

        return targetEntityName(rollupEntity);

    }

    /**
     * check entity or dto
     *
     * @param eCoreModel
     * @param typeName
     * @return
     */
    protected boolean isModel(ECoreModel eCoreModel, String typeName) {
        boolean isEntity = findEntityByName(typeName) != null;
        if (isEntity)
            return true;
        //boolean isDTO = eCoreModel.getDataTransferModels().findByName(typeName)!=null;
        boolean isDTO = this.publisher.getECoreModelContext().findModelComponent(typeName, DataTransfer.class) != null;
        if (isDTO)
            return true;


        return false;
    }


    protected boolean isEntityModel(ECoreModel eCoreModel, String typeName) {
        boolean isEntity = this.findEntityByName(typeName) != null;
        if (isEntity)
            return true;

        return false;
    }

    protected boolean isDTOModel(ECoreModel eCoreModel, String typeName) {

        boolean isDTO = eCoreModel.getDataTransferModels().findByName(typeName) != null;
        if (isDTO)
            return true;


        return false;
    }

    protected boolean isEnumModel(String typeName) {
        return this.publisher.getECoreModelContext().findModelComponent(typeName, EnumModel.class) != null;
    }


    protected boolean isExist(ECoreModel eCoreModel, String typeName) {
        boolean isEntity = this.findEntityByName(typeName) != null;
        if (isEntity)
            return true;
        boolean isDTO = eCoreModel.getDataTransferModels().findByName(typeName) != null;
        if (isDTO)
            return true;
        boolean isEnum = eCoreModel.getEnumModels().findByName(typeName) != null;
        if (isEnum)
            return true;

        return false;
    }

    protected boolean isEnableInDTO(ECoreModel eCoreModel, MetaInfo metaInfo, String typeName, ComponentIdentity identity) {

        //////boolean isDTO = eCoreModel.getDataTransferModels().findByName(typeName)!=null;
        boolean isDTO = this.publisher.getECoreModelContext().findModelComponent(typeName, DataTransfer.class) != null;

        if (isDTO) {
            String type = metaInfo.getMetaAnnotationValue(DataTransferAnnotation.META_TYPE);
            boolean isSearchResult = metaInfo.hasMetaAnnotation(DataTransferAnnotation.META_SEARCH_RESULT);
            if ("entity".equals(type) && isSearchResult) {
                return false;
            }
            String targetNm = metaInfo.getMetaAnnotationValue(DataTransferAnnotation.META_TEMPLATE);
            if (targetNm != null) {

                // Cross-referencing can lead to an infinite loop, so it has been excluded.
                RelationshipManager instance = RelationshipManager.getInstance(identity.getDomainId());
                List<ModelRelationship> byToName = instance.findByToNameAndType(targetNm, RelationType.MANY_TO_ONE);
                if (byToName != null && byToName.size() > 0) {
                    ConsoleLog.printWarn("Cross-referencing can lead to an infinite loop. typeName: " + typeName);
                    //return false;
                }
            }

            return true;
        }
        //////boolean isEnum = eCoreModel.getEnumModels().findByName(typeName)!=null;
        boolean isEnum = this.publisher.getECoreModelContext().findModelComponent(typeName, EnumModel.class) != null;
        if (isEnum)
            return true;


        return false;
    }


    protected boolean isSkipSearchField(DataModelComponent dto, Field f) {
        if (f.hasAnnotation(EntityAnnotation.AUTOSEARCH) && !dto.getMetaInfo().hasMetaAnnotation(EntityAnnotation.AUTOGENERATED)) {
            return true;
        }

        return false;
    }


    /**
     * Retrieves the full class name if the given className includes a domain prefix.
     * If the className contains a colon (`:`), it attempts to find the corresponding
     * DataModelComponent and return its full name. Otherwise, it returns the input className.
     *
     * @param className the class name, which may include a domain prefix (e.g., "domain:ClassName")
     * @return the full class name if a corresponding DataModelComponent is found; otherwise, the input className
     */
    protected String getClassName(Entity entity, String className) {
        if (className.indexOf(":") > 0) {
            DataModelComponent modelComponent = BaseECoreModelContext.getContext().findModelComponent(className);
            if (modelComponent != null) {
                return modelComponent.getFullName();
            }
        } else {
            String domainId = entity.getIdentity().getDomainId();

            String[] allDomainNames = BaseECoreModelContext.getContext().getAllDomainNames();
            for (String domainNm : allDomainNames) {
                DataModelComponent modelByName = BaseECoreModelContext.getContext().getDomain(domainNm).getModel().findModelByName(className);
                if (modelByName != null) {
                    if (domainId.equals(domainNm)) {
                        return modelByName.getIdentity().getName();
                    } else {
                        return modelByName.getFullName();
                    }
                }
            }

        }
        return className;
    }


    protected void processDeferredField(DataTransfer dto, Field f) {


        if (f.hasAnnotation(DataTransferAnnotation.META_DEFERRED)) {

            String baseFieldNm = f.getName();

            String typeName = f.getTypeInfo().getDefaultTypeName();
            Entity entity = this.publisher.getECoreModelContext().findModelComponent(typeName, Entity.class);

            if (entity == null) {
                //f.removeAnnotation(DataTransferAnnotation.META_DEFERRED);
                return;
            }

            //if(entity !=null)
            {

                Map<String, Annotation> antMp = new HashMap<>();

                boolean isEmbeddable = false;

                if (entity.getMetaInfo().hasMetaAnnotation(EntityAnnotation.META_EMBEDDABLE)) {
                    isEmbeddable = true;
                }

                Annotation att = Annotation.create(DataTransferAnnotation.META_SEARCHABLE_BYPASS);
                antMp.put(att.getName(), att);


                if (!dto.getMetaInfo().hasMetaAnnotation(DataTransferAnnotation.META_SEARCHABLE)) {
                    Field dtoField = Field.builder()
                            .name(f.getName())
                            .parentMetaInfo(dto.getMetaInfo())
                            .type(typeName + "DTO")
                            .annotationMap(antMp)
                            .build();

                    dto.addField(dtoField);
                }


                ModelComponentItems<Field> entityFields = entity.getItems();
                while (entityFields.hasNext()) {
                    Field entityField = entityFields.next();
                    if (entityField.hasAnnotation(EntityAnnotation.META_ID)) {

                        String refFieldNm = baseFieldNm + "." + entityField.getName();
                        String newFieldNm = baseFieldNm + StringUtils.capitalize(entityField.getName());

                        // for search
                        Annotation srchAnt = Annotation.create(DataTransferAnnotation.META_SEARCHABLE, "eq");
                        Annotation annotation = Annotation.create("ref", refFieldNm);
                        Map<String, Annotation> antMap = new HashMap<>();
                        antMap.put(annotation.getName(), annotation);
                        antMap.put(srchAnt.getName(), srchAnt);


                        Annotation.create("s", "eq");
                        antMap.put(annotation.getName(), annotation);


                        Field newRefField = Field.builder()
                                .name(newFieldNm)
                                .parentMetaInfo(dto.getMetaInfo())
                                .type(entityField.getTypeInfo().getDefaultTypeName())
                                .annotationMap(antMap)
                                .build();

                        dto.addField(newRefField);

                    }
                }
            }

        }
    }

    protected boolean isDisableField(Field f) {
        if (f.hasAnnotation(DataTransferAnnotation.META_DISABLE)) {
            if (this.isEntityType(f)) {
                return true;
            }
        }
        return false;
    }

    protected DataTransfer findDTO(String name) {
        DataTransfer modelComponent = this.publisher.getECoreModelContext().findModelComponent(name, DataTransfer.class);
        return modelComponent;
    }


    protected DataTransfer findDTO(Entity entity) {

        ModelDomain domain = this.publisher.getECoreModelContext().getDomain();
        ECoreModel model = domain.getModel();
        RelationshipManager relationshipManager = RelationshipManager.getInstance(domain.getName());


        String entityNm = entity.getIdentity().getName();
        List<ModelRelationship> relationshipList = relationshipManager
                .findByToNameAndType(entityNm, RelationType.TEMPLATE);

        for (ModelRelationship r : relationshipList) {
            String dtoName = r.getFromName();


            DataTransfer byName = model.getDataTransferModels().findByName(dtoName);
            if (byName != null)
                return byName;
        }

        return null;
    }
}
