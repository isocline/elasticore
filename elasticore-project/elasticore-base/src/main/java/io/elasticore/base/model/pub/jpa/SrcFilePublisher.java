//ecd:
package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.HashUtils;
import lombok.SneakyThrows;

import java.io.*;

public class SrcFilePublisher {

    private static String getMarkText(String content) {
        String ecd = "ecd:"+HashUtils.getHashCode(content)+"V0.7";
        return "//"+ecd+"\n";
    }

    protected void writeSrcCode(CodePublisher publisher, ModelComponent modelComponent, String qualifiedClassName, String content) {
        try (Writer writer = publisher.getSrcCodeWriterFactory().getWriter(qualifiedClassName)) {
            writer.write(getMarkText(content));
            writer.write(content);
            writer.flush();
        } catch (Throwable e) {
            e.printStackTrace();
            if(modelComponent!=null)
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
        }catch (RuntimeException re){}

        return "javax";
    }


    protected void setFieldDesc(Field field, CodeTemplate.Paragraph paragraph) {
        if (field.getDescription() != null) {
            paragraph.add("// " + field.getDescription());
        }else {
            String description = field.getAnnotationValue("label","desc","description");
            if(description !=null)
                paragraph.add("// " + description);
        }

    }
}
