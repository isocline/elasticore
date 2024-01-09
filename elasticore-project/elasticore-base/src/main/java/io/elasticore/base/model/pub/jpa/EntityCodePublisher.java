package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.util.TextTemplate;

public class EntityCodePublisher {

    private TextTemplate javaClassTmpl = (new TextTemplate())

            .line("package ${packageName};")
            .line()
            .line("import ${import};")
            .line()
            .line("/**")
            .line(" * <pre>${description}</pre>")
            .line(" * hash:${hashCode}")
            .line(" */")
            .line("public class ${className} ${parentClassInfo} implements Serializable {")
            .line()
            .line("${fieldList}")
            .line()
            .line("}");



    public void publish(Entity entity) {

        String codeTxt = javaClassTmpl
                .set("packageName", "test")
                .set("className", entity.getIdentity().getName())
                .toString();


        System.err.println(codeTxt);

    }

}
