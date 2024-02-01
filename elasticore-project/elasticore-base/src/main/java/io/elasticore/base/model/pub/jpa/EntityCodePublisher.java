package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.util.CodeTemplate;

import java.util.ArrayList;
import java.util.List;

public class EntityCodePublisher {

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


    public void publish(Entity entity) {

        CodeTemplate.Parameters p = CodeTemplate.newParameters();

        String classNm = entity.getIdentity().getName();

        p
                .set("packageName", "com.elasticore.sample.entity")

                .set("extendInfo", "")
                .set("implementInfo", "implements Serializable")

                .set("className", classNm);


        CodeTemplate.Value v = getFieldInfo(entity);
        p.set("fieldList", v);

        String code = javaClassTmpl.toString(p);

        System.err.println(code);
    }


    private CodeTemplate.Value getFieldInfo(Entity entity) {
        ModelComponentItems<Field> fields = entity.getItems();
        CodeTemplate.Value v = CodeTemplate.newValue();
        while (fields.hasNext()) {
            Field f = fields.next();

            setFieldDesc(f,v);
            setFieldPkInfo(f,v);
            setFieldColumnAnnotation(f, v);

            String code = String.format("%s %s %s;", "private", f.getType(), f.getName());
            v.add(code);
            v.add("");
        }
        return v;
    }

    private void setFieldDesc(Field field, CodeTemplate.Value value) {
        if(field.getDescription()!=null) {
            value.add("// "+field.getDescription());
        }

    }

    private void setFieldPkInfo(Field field, CodeTemplate.Value value) {
        if(field.isPrimaryKey()) {
            value.add("@Id");
        }

        if(field.getGenerationType()!=null) {
            value.add("@GeneratedValue(strategy = GenerationType."+field.getGenerationType());
        }
    }

    private void setFieldColumnAnnotation(Field field, CodeTemplate.Value value) {
        List<String> list = new ArrayList<>();
        boolean isNullable = field.isNullable();
        if (!isNullable) {
            list.add("nullable = false");
        }

        int length = field.getLength();
        if (length > 0) {
            list.add("length = " + length);
        }

        if(field.getColumnDefinition()!=null) {
            list.add("columnDefinition=\""+field.getColumnDefinition()+"\"");
        }

        if(field.isUnique()) {
            list.add("unique = true");
        }

        if (list.size() > 0) {
            String txt = "@Column(" + String.join(", ", list) + ")";
            value.add(txt);
        }
    }
}
