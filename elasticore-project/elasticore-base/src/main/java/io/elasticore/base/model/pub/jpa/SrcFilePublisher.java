//ecd:
package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SourceFileAccessFactory;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.AbstractDataModel;
import io.elasticore.base.model.core.RelationshipManager;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.HashUtils;
import io.elasticore.base.util.StringUtils;

import java.io.*;
import java.util.List;

public class SrcFilePublisher {

    private CodePublisher publisher;

    protected SrcFilePublisher(CodePublisher publisher) {
        this.publisher = publisher;
    }

    private void log(String msg) {
        System.err.println(msg);
    }


    protected void writeSrcCode(CodePublisher publisher, ModelComponent modelComponent, String qualifiedClassName, String content) {
        SourceFileAccessFactory fileAccessFactory = publisher.getSrcFileAccessFactory();

        HashUtils.Response response = null;
        try (Reader reader = fileAccessFactory.getReader(qualifiedClassName)) {
            response = HashUtils.checkContentModified(reader);


            if(response!= null && response.getStatus() == HashUtils.MODIFIED) {
                log("[WARN] PUB_SKIP: "+qualifiedClassName+" ** USER_MODIFIED **");

                return;
            }

        }catch (FileNotFoundException fnfe) {
        }catch (Throwable e) {
            e.printStackTrace();
        }

        if(response!= null && content.equals(response.getContent())) {
            log("[INFO] PUB_SKIP: "+qualifiedClassName+" NO_MODIFIED");

            return;
        }


        try (Writer writer = fileAccessFactory.getWriter(qualifiedClassName)) {
            writer.write(HashUtils.makeEcdCode(content));
            writer.write(content);
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
            String description = field.getAnnotationValue("label", "desc", "description");
            if (description != null) {
                paragraph.add("/*");
                paragraph.add("  " + description);
                paragraph.add("*/");
            }

        }
    }


    protected void setFieldDocumentation(Field f, CodeTemplate.Paragraph p) {

        String desc = f.getAnnotationValue("description", "desc", "label");
        if (desc == null) {
            desc = f.getName();
        }
        boolean isNotNull = f.hasAnnotation("notnull");
        String requireTxt = "";
        if (isNotNull) {
            requireTxt = ", requiredMode=Schema.RequiredMode.REQUIRED";
        }

        String example = getExample(f);

        p.add("@Schema(description = \"%s\" %s %s)", desc, requireTxt, example);
    }

    protected String getExample(Field f) {
        String example = f.getAnnotationValue("example");

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


        if (example != null) {
            example = ", example=\"" + example + "\"";
        } else {
            example = "";
        }
        return example;
    }


    protected void setFieldValidation(Field f, CodeTemplate.Paragraph p) {

        if (f.hasAnnotation("notnull"))
            p.add("@NotNull");

        String minSize = f.getAnnotationValue("minsize");
        String maxSize = f.getAnnotationValue("length", "len", "size");
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
        String jsonName = f.getAnnotationValue("jsonproperty", "json");
        if (jsonName != null) {
            p.add("@JsonProperty(\"%s\")", jsonName);
        }

        if (f.hasAnnotation("jsonignore") || f.hasAnnotation("ignore")) {
            p.add("@JsonIgnore");
        }
    }

    protected boolean isEnumerationType(Field f) {
        String typeName = f.getTypeInfo().getCoreItemType();
        if (this.publisher.getECoreModelContext().getDomain().getModel().getEnumModels().findByName(typeName) != null)
            return true;

        return false;
    }


    protected boolean isEntityType(Field f) {
        String typeName = f.getTypeInfo().getCoreItemType();
        if (this.publisher.getECoreModelContext().getDomain().getModel().getEnumModels().findByName(typeName) != null)
            return true;

        return false;
    }



    protected void setFunctionInfo(Field f, CodeTemplate.Paragraph p) {
        String getFunc = f.getAnnotationValue("function.get");
        String setFunc = f.getAnnotationValue("function.set");

        String fldNm = f.getName();
        String cFldNm = StringUtils.capitalize(fldNm);
        String type = f.getTypeInfo().getDefaultTypeName();


        if(getFunc!=null) {
            if(getFunc.indexOf("(")<0){
                getFunc = getFunc.trim()+"(this)";
            }
            p.add("public %s get%s() {",type,cFldNm);
            p.add("    return %s;",getFunc);
            p.add("}");
            p.add("");
        }

        if(setFunc!=null) {
            if(setFunc.indexOf("(")<0){
                setFunc = setFunc.trim()+"(this)";
            }
            p.add("public void set%(%s val) {",cFldNm,type);
            p.add("    this.%s = val;",fldNm);
            p.add("    %s;",setFunc);
            p.add("}");
            p.add("");
        }
    }

}
