package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.ModelDomain;
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
            .line("import lombok.Getter;")
            .line("import lombok.Setter;")
            .line("import lombok.NoArgsConstructor;")
            .line("import lombok.AllArgsConstructor;")
            .line("import javax.persistence.*;")
            .line("import ${import};", true)

            .line()
            .line("/**")
            .line(" * <pre>${description}</pre>")
            .line(" * hash:${hashCode}")
            .line(" */")

            .line("${classAnotations}", true)
            .line("@Entity")
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


    public EntityFilePublisher(JPACodePublisher publisher) {
        this.publisher = publisher;

        entityBaseDir= publisher.getDestBaseDirPath() + "/entity";
        File f= new File(entityBaseDir);
        if(!f.exists()) {
            f.mkdirs();
        }
    }

    public void publish(ModelDomain domain, Entity entity) {

        CodeTemplate.Parameters p = CodeTemplate.newParameters();

        String classNm = entity.getIdentity().getName();

        p
                .set("packageName", "com.elasticore.sample.entity")

                .set("extendInfo", "")
                .set("implementInfo", "implements Serializable")

                .set("className", classNm);


        CodeTemplate.Paragraph v = getFieldInfo(entity);
        p.set("fieldList", v);

        String code = javaClassTmpl.toString(p);


        System.err.println(code);

        String filePaht = this.entityBaseDir +"/"+ entity.getIdentity().getName()+".java";
        writeFile(filePaht, code);



    }



    private CodeTemplate.Paragraph getFieldInfo(Entity entity) {
        ModelComponentItems<Field> fields = entity.getItems();
        CodeTemplate.Paragraph v = CodeTemplate.newParagraph();
        while (fields.hasNext()) {
            Field f = fields.next();

            setFieldDesc(f, v);
            setFieldPkInfo(f, v);
            setFieldColumnAnnotation(f, v);

            String code = String.format("%s %s %s;", "private", f.getType(), f.getName());
            v.add(code);
            v.add("");
        }
        return v;
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
