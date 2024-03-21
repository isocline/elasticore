package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.entity.Annotation;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.pub.JPACodePublisher;
import io.elasticore.base.util.CodeTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EntityFilePublisher extends FilePublisher {

    private final static CodeTemplate javaClassTmpl = CodeTemplate.newInstance()

            .line("package ${packageName};")
            .line()
            .line("import ${enumPackageName}.*;") // for test
            .line("import lombok.Getter;")
            .line("import lombok.Setter;")
            .line("import lombok.NoArgsConstructor;")
            .line("import lombok.AllArgsConstructor;")
            .line("import javax.persistence.*;")
            .line("import java.util.*;")
            .line("import ${import};", true)

            .line()
            .line("/**")
            .line(" * <pre>${description}</pre>")
            .line(" * hash:${hashCode}")
            .line(" */")

            .line("${classAnnotations}", true)
            .line("@Getter")
            .line("@Setter")
            .line("@NoArgsConstructor")
            .line("@AllArgsConstructor")

            .line("public class ${className} ${extendInfo} ${implementInfo}  {")
            .line()
            .line("    ${fieldList}", true)
            .line()
            .line("}");

    private JPACodePublisher publisher;

    private String entityBaseDir;

    private String packageName;

    private String enumPackageName;


    public EntityFilePublisher(JPACodePublisher publisher) {
        this.publisher = publisher;


        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);
        this.enumPackageName = model.getNamespace(ConstanParam.KEYNAME_ENUMERATION);


        entityBaseDir = publisher.getDestBaseDirPath() + "/"+getPath(this.packageName);
        File f = new File(entityBaseDir);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public void publish(ModelDomain domain, Entity entity) {

        //for
        publishEntityIdentityClass(domain, entity);

        CodeTemplate.Parameters p = CodeTemplate.newParameters();

        String classNm = entity.getIdentity().getName();

        p
                .set("packageName", packageName)
                .set("enumPackageName", enumPackageName)
                .set("classAnnotations", "@Entity")

                .set("extendInfo", "")
                .set("implementInfo", "implements java.io.Serializable")

                .set("className", classNm);


        CodeTemplate.Paragraph pr = getFieldInfo(entity);
        p.set("fieldList", pr);

        String code = javaClassTmpl.toString(p);


        String filePaht = this.entityBaseDir + "/" + entity.getIdentity().getName() + ".java";
        writeFile(filePaht, code);

    }


    /**
     * @param domain
     * @param entity
     */
    private void publishEntityIdentityClass(ModelDomain domain, Entity entity) {
        if (entity.getPkField().isMultiple()) {

            String classNm = entity.getPkField().getType();

            CodeTemplate.Parameters param = CodeTemplate.newParameters();
            param
                    .set("packageName", packageName)
                    .set("extendInfo", "")
                    .set("implementInfo", "implements java.io.Serializable")
                    .set("classAnnotations", "@Embeddable")
                    .set("className", classNm);

            CodeTemplate.Paragraph p = getMultiplePkField(entity);
            param.set("fieldList", p);

            String targetSrcPath = this.entityBaseDir + "/" + classNm + ".java";
            String code = javaClassTmpl.toString(param);
            writeFile(targetSrcPath, code);

        }
    }

    private CodeTemplate.Paragraph getMultiplePkField(Entity entity) {
        ModelComponentItems<Field> fields = entity.getItems();
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();
        while (fields.hasNext()) {
            Field f = fields.next();

            if(!f.isPrimaryKey())
                continue;;

            setFieldDesc(f, p);
            //setFieldPkInfo(f, p);
            setFieldColumnAnnotation(f, p);

            String code = String.format("%s %s %s;", "private", f.getType(), f.getName());
            p.add(code);
            p.add("");
        }
        return p;
    }

    private CodeTemplate.Paragraph getFieldInfo(Entity entity) {
        ModelComponentItems<Field> fields = entity.getItems();
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();

        boolean isExtendIdentity = false;
        if (entity.getPkField() != null && entity.getPkField().isMultiple()) {
            isExtendIdentity = true;
            String code = String.format("private %s id;", entity.getPkField().getType());
            p.add("@EmbeddedId");
            p.add(code);
        }
        while (fields.hasNext()) {
            Field f = fields.next();

            if (isExtendIdentity) {
                if (f.isPrimaryKey())
                    continue;
            }

            setFieldDesc(f, p);
            setFieldPkInfo(f, p);
            setFieldColumnAnnotation(f, p);

            String code = String.format("%s %s %s;", "private", f.getType(), f.getName());
            p.add(code);
            p.add("");
        }
        return p;
    }

    private void setFieldDesc(Field field, CodeTemplate.Paragraph paragraph) {
        if (field.getDescription() != null) {
            paragraph.add("// " + field.getDescription());
        }

    }

    private void setFieldPkInfo(Field field, CodeTemplate.Paragraph paragraph) {

        if (field.getAnnotation("id") != null) {
            paragraph.add("@Id");
        }

        if (field.isPrimaryKey()) {
            paragraph.add("@Id");
        }

        if (field.getGenerationType() != null) {
            paragraph.add("@GeneratedValue(strategy = GenerationType." + field.getGenerationType());
        }
    }

    private void setFieldColumnAnnotation(Field field, CodeTemplate.Paragraph paragraph) {
        List<String> list = new ArrayList<>();


        Annotation notnull = field.getAnnotation("notnull");

        if (notnull != null && !"false".equals(notnull.getValue())) {
            list.add("nullable = false");
        }
        Annotation length = field.getAnnotation("length");


        if (length != null && length.getValue() != null) {
            list.add("length = " + length.getValue());
        }

        if (field.getColumnDefinition() != null) {
            list.add("columnDefinition=\"" + field.getColumnDefinition() + "\"");
        }

        Annotation uniqAnnot = field.getAnnotation("unique");
        if (uniqAnnot != null && !"false".equals(uniqAnnot.getValue()))
            list.add("unique = true");

        if (list.size() > 0) {
            String txt = "@Column(" + String.join(", ", list) + ")";
            paragraph.add(txt);
        }
    }
}
