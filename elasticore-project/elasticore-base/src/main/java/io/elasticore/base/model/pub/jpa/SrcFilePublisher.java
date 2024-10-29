//ecd:
package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SourceFileAccessFactory;
import io.elasticore.base.model.*;
import io.elasticore.base.model.core.AbstractDataModel;
import io.elasticore.base.model.core.RelationshipManager;
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
import java.util.List;

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
        String path =  publisher.getSrcFileAccessFactory().getFilePath(qualifiedClassName);

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
        writeSrcCode(publisher,modelComponent,qualifiedClassName,content,true);
    }

    protected void writeSrcCode(CodePublisher publisher, ModelComponent modelComponent, String qualifiedClassName, String content, boolean checkModified) {
        SourceFileAccessFactory fileAccessFactory = publisher.getSrcFileAccessFactory();


        HashUtils.Response response = null;
        try (Reader reader = fileAccessFactory.getReader(qualifiedClassName)) {
            response = HashUtils.checkContentModified(reader);


            if (response != null && response.getStatus() == HashUtils.MODIFIED) {



                if(checkModified && !response.hasCustomizedScopeContent()) {
                    //ConsoleLog.printInfo("[WARN] PUB_SKIP: " + qualifiedClassName + " ** USER_MODIFIED **");
                    ConsoleLog.storeLog("USER_MODIFIED", getFilePathInfo(qualifiedClassName));
                    return;
                }
                //ConsoleLog.printInfo("[WARN] IGNORE: " + qualifiedClassName + " ** USER_MODIFIED **");
                ConsoleLog.storeLog("USER_MODIFIED", getFilePathInfo(qualifiedClassName) +" [INGORE]");

            }

        } catch (FileNotFoundException fnfe) {
        } catch (Throwable e) {
            e.printStackTrace();
        }

        if (response != null && content.equals(response.getContent()) && !response.hasCustomizedScopeContent()) {
            //ConsoleLog.printInfo("[INFO] PUB_SKIP: " + qualifiedClassName + " NO_MODIFIED");
            ConsoleLog.storeLog("NO_MODIFIED", getFilePathInfo(qualifiedClassName) );

            return;
        }



        try (Writer writer = fileAccessFactory.getWriter(qualifiedClassName)) {


            if(response!=null && response.hasCustomizedScopeContent()) {
                String oldEcdLine = response.getOldEcdLine();
                if(oldEcdLine!=null) {
                    writer.write(oldEcdLine+"\n");
                }else {
                    writer.write(HashUtils.makeEcdCode(content));
                }
                writer.write(content.substring(0, content.length()-2));
                writer.write(response.getCustomizedScopeContent());
                writer.write(content.substring(content.length()-2));
            }else{
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

            if (eCoreModel.getEntityModels().findByName(parameterType) != null) {
                // skip
                return true;
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
        if("true".equals(required))
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

        if(example==null) {
            String format = f.getAnnotationValue(EntityAnnotation.FORMAT);
            if(format==null) {
                if(f.getTypeInfo().getBaseFieldType() == BaseFieldType.DATE
                        || f.getTypeInfo().getBaseFieldType() == BaseFieldType.LocalDate ) {
                    if(format==null)
                        format = "yyyy-MM-dd";

                }
                else if(f.getTypeInfo().getBaseFieldType() == BaseFieldType.DATETIME
                        || f.getTypeInfo().getBaseFieldType() == BaseFieldType.LocalDateTime ) {
                    if(format==null)
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

        if (f.hasAnnotation("notnull"))
            p.add("@NotNull");

        if(f.hasAnnotation("notblank"))
            p.add("@NotBlank");

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


    protected void setJsonInfo(Field f, CodeTemplate.Paragraph p) {
        String jsonName = f.getAnnotationValue(EntityAnnotation.JSON_NAME);
        if (jsonName != null) {
            p.add("@JsonProperty(\"%s\")", jsonName);
        }

        if (f.hasAnnotation("jsonignore") || f.hasAnnotation("ignore")) {
            p.add("@JsonIgnore");
        }

        String jsonSerialize = f.getAnnotationValue(EntityAnnotation.JSON_SERIALIZE_USE);
        String jsonDeserialize = f.getAnnotationValue(EntityAnnotation.JSON_DESERIALIZE_USE);

        if (jsonSerialize != null) {
            p.add("@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = %s)", jsonSerialize);
        }

        if (jsonDeserialize != null) {
            p.add("@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = %s)", jsonDeserialize);
        }
    }

    protected boolean isEnumerationType(Field f) {
        String typeName = f.getTypeInfo().getCoreItemType();
        if (this.publisher.getECoreModelContext().getDomain().getModel().getEnumModels().findByName(typeName) != null)
            return true;

        return false;
    }

    protected Entity findEntityByField(Field f) {
        String typeName = f.getTypeInfo().getCoreItemType();
        return findEntityByField(typeName);
    }

    protected Entity findEntityByField(String typeName) {
        return this.publisher.getECoreModelContext().getDomain().getModel().getEntityModels().findByName(typeName);
    }

    protected boolean isEntityType(Field f) {
        if(findEntityByField(f) !=null)
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
            if(getFunc.indexOf(";")<0)
                getFunc = getFunc+";";
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
        if(field.getTypeInfo().getBaseFieldType() == BaseFieldType.DATE
                || field.getTypeInfo().getBaseFieldType() == BaseFieldType.LocalDate ) {
            if(format==null)
                format = "yyyy-MM-dd";
            paragraph.add("@org.springframework.format.annotation.DateTimeFormat(pattern = \"%s\")",format);
            paragraph.add("@com.fasterxml.jackson.annotation.JsonFormat(pattern = \"%s\")",format);
        }
        else if(field.getTypeInfo().getBaseFieldType() == BaseFieldType.DATETIME
                || field.getTypeInfo().getBaseFieldType() == BaseFieldType.LocalDateTime ) {
            if(format==null)
                format = "yyyy-MM-dd HH:mm:ss";
            paragraph.add("@org.springframework.format.annotation.DateTimeFormat(pattern = \"%s\")",format);
            paragraph.add("@com.fasterxml.jackson.annotation.JsonFormat(pattern = \"%s\")",format);
        }

    }


    protected String targetEntityName(Entity targetEntity) {

        String rollupTargetNm = targetEntity.getMetaInfo().getMetaAnnotationValue(EntityAnnotation.META_ROLL_UP_TARGET);
        if(rollupTargetNm==null) {
            return targetEntity.getIdentity().getName();
        }

        Entity rollupEntity = this.publisher.getECoreModelContext().getDomain().getModel().getEntityModels().findByName(rollupTargetNm);
        if(rollupEntity==null)
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
        boolean isEntity = eCoreModel.getEntityModels().findByName(typeName)!=null;
        if(isEntity)
            return true;
        boolean isDTO = eCoreModel.getDataTransferModels().findByName(typeName)!=null;
        if(isDTO)
            return true;


        return false;
    }


    protected boolean isEntityModel(ECoreModel eCoreModel, String typeName) {
        boolean isEntity = eCoreModel.getEntityModels().findByName(typeName)!=null;
        if(isEntity)
            return true;

        return false;
    }

    protected boolean isDTOModel(ECoreModel eCoreModel, String typeName) {

        boolean isDTO = eCoreModel.getDataTransferModels().findByName(typeName)!=null;
        if(isDTO)
            return true;


        return false;
    }

    protected boolean isEnumModel(ECoreModel eCoreModel, String typeName) {

        boolean isEnum = eCoreModel.getEnumModels().findByName(typeName)!=null;
        if(isEnum)
            return true;

        return false;
    }


    protected boolean isExist(ECoreModel eCoreModel, String typeName) {
        boolean isEntity = eCoreModel.getEntityModels().findByName(typeName)!=null;
        if(isEntity)
            return true;
        boolean isDTO = eCoreModel.getDataTransferModels().findByName(typeName)!=null;
        if(isDTO)
            return true;
        boolean isEnum = eCoreModel.getEnumModels().findByName(typeName)!=null;
        if(isEnum)
            return true;

        return false;
    }

    protected boolean isEnableInDTO(ECoreModel eCoreModel, MetaInfo metaInfo, String typeName) {

        boolean isDTO = eCoreModel.getDataTransferModels().findByName(typeName)!=null;
        if(isDTO) {
            String type =metaInfo.getMetaAnnotationValue(DataTransferAnnotation.META_TYPE);
            boolean isSearchResult = metaInfo.hasMetaAnnotation(DataTransferAnnotation.META_SEARCH_RESULT);
            if("entity".equals(type) && isSearchResult) {
                return false;
            }


            return true;
        }
        boolean isEnum = eCoreModel.getEnumModels().findByName(typeName)!=null;
        if(isEnum)
            return true;

        return false;
    }


}
