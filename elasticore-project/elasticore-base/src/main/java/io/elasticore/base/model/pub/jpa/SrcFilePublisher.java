//ecd:
package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.AbstractDataModel;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.HashUtils;
import lombok.SneakyThrows;

import java.io.*;

public class SrcFilePublisher {

    private static String getMarkText(String content) {
        String ecd = "ecd:" + HashUtils.getHashCode(content) + "V0.7";
        return "//" + ecd + "\n";
    }

    protected void writeSrcCode(CodePublisher publisher, ModelComponent modelComponent, String qualifiedClassName, String content) {
        try (Writer writer = publisher.getSrcCodeWriterFactory().getWriter(qualifiedClassName)) {
            writer.write(getMarkText(content));
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


    protected void setFieldDesc(Field field, CodeTemplate.Paragraph paragraph) {
        if (field.getDescription() != null) {
            paragraph.add("// " + field.getDescription());
        } else {
            String description = field.getAnnotationValue("label", "desc", "description");
            if (description != null)
                paragraph.add("// " + description);
        }

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
        if(typeName==null) return null;

        ModelComponentItems<Field> items = entity.getItems();
        while(items.hasNext()) {
            Field field = items.next();

            if(typeName.equals(field.getTypeInfo().getDefaultTypeName())) {
                return field;
            }
        }

        return null;
    }

    protected String findFieldNameByTypeName(AbstractDataModel entity, String typeName) {
        Field f = findFieldByTypeName(entity, typeName);
        if(f!=null)
            return f.getName();

        return null;
    }
}
