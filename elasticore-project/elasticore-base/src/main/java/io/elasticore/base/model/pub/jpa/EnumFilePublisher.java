package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.pub.JPACodePublisher;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.StringList;

import java.io.File;

/**
 *
 */
public class EnumFilePublisher extends FilePublisher {

    private final static CodeTemplate javaClassTmpl = CodeTemplate.newInstance()
            .line("package ${packageName};")
            .line()
            .line("import lombok.Getter;")
            .line("import lombok.AllArgsConstructor;")
            .line("import javax.persistence.*;")
            .line("import ${import};", true)

            .line()
            .line("/**")
            .line(" * <pre>${description}</pre>")
            .line(" * hash:${hashCode}")
            .line(" */")

            .line("${classAnotations}", true)
            .line("@Getter")
            .line("@AllArgsConstructor")

            .line("public enum ${className} {")
            .line()
            .line("    ${enumConstant}")
            .line()
            .line("    ${fieldLine}")
            .line()
            .line("    ${className}(${argLine}) {")
            .line(" ${paramLine}")
            .line(" }")
            .line("}");


    private JPACodePublisher publisher;

    private String fileBaseDir;

    public EnumFilePublisher(JPACodePublisher publisher) {
        this.publisher = publisher;

        fileBaseDir= publisher.getDestBaseDirPath() + "/enum";
        File f= new File(fileBaseDir);
        if(!f.exists()) {
            f.mkdirs();
        }
    }


    public void publish(ModelDomain domain,EnumModel enumModel) {
        Items<EnumConstant> enumConstantItems = enumModel.getEnumConstantItems();

        StringList sbLine = StringList.create("\n    ,");
        for (EnumConstant enumConstant : enumConstantItems.getItemList()) {
            String name = enumConstant.getIdentity().getName();

            StringList sb = StringList.create(",");

            sb.append(name).append("(");

            for (EnumConstant.ConstructParam param : enumConstant.getConstructParamList()) {
                sb.add(param);
            }

            sb.append(")");
            sbLine.add(sb);
        }
        sbLine.append(";");

        StringList fieldLine = StringList.create(";\n    ");

        StringList argLine = StringList.create(",");
        StringList paramLine = StringList.create(";\n    ");

        Items<Field> fieldItem = enumModel.getFieldItems();
        for(Field f: fieldItem.getItemList() ) {
            fieldLine.add("private final "+f.getType() +" "+ f.getName());

            argLine.add(f.getType()+" "+f.getName());

            paramLine.add("this."+f.getName()+" = "+f.getName());

        }

        CodeTemplate.Parameters p = CodeTemplate.newParameters();
        String classNm = enumModel.getIdentity().getName();

        p
                .set("packageName", "com.elasticore.sample.enum")
                .set("className", classNm)
                .set("enumConstant", sbLine.toString())
                .set("fieldLine", fieldLine.toString())
                .set("argLine", argLine.toString())
                .set("paramLine", paramLine.toString());




        String code = javaClassTmpl.toString(p);
        System.err.println(code);

        String filePaht = this.fileBaseDir +"/"+ enumModel.getIdentity().getName()+".java";
        writeFile(filePaht, code);
    }
}
