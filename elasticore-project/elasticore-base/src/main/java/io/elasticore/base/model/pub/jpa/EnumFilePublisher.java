package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
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

            .line("import javax.persistence.*;")
            .line("import ${import};", true)

            .line()
            .line("/**")
            .line(" * <pre>${description}</pre>")
            .line(" * hash:${hashCode}")
            .line(" */")

            .line("${classAnotations}", true)
            .line("@Getter")


            .line("public enum ${className} {")
            .line()
            .line("    ${enumConstant}")
            .line()
            .line("    ${fieldLine}")
            .line()
            .line("    ${className}(${argLine}) {")
            .line("        ${paramLine}")
            .line(" }")
            .line("}");


    private JPACodePublisher publisher;

    private String fileBaseDir;
    private String packageName;

    public EnumFilePublisher(JPACodePublisher publisher) {
        this.publisher = publisher;

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_ENUMERATION);

        fileBaseDir= publisher.getDestBaseDirPath() + "/"+getPath(this.packageName);
        File f= new File(fileBaseDir);
        if(!f.exists()) {
            f.mkdirs();
        }
    }


    private String getValue(EnumConstant.ConstructParam param, Field f) {
        String baseVal = param.toString();
        if(f.getTypeInfo().isStringType()) {
            if(baseVal.indexOf("\"")==0) {
                return baseVal;
            }
            return "\""+baseVal+"\"";
        }
        return baseVal;

    }


    public void publish(ModelDomain domain,EnumModel enumModel) {
        Items<EnumConstant> enumConstantItems = enumModel.getEnumConstantItems();
        Items<Field> fieldItem = enumModel.getFieldItems();

        StringList sbLine = StringList.create("\n    ,");
        for (EnumConstant enumConstant : enumConstantItems.getItemList()) {
            String name = enumConstant.getIdentity().getName();

            StringList sb = StringList.create(",");

            sb.append(name).append("(");

            int seq=0;
            for (EnumConstant.ConstructParam param : enumConstant.getConstructParamList()) {
                String val = getValue(param, fieldItem.getItemList().get(seq));


                sb.add(val);
                seq++;
            }

            sb.append(")");
            sbLine.add(sb);
        }
        sbLine.append(";");

        StringList fieldLine = StringList.create(";\n    ",";");

        StringList argLine = StringList.create(",");
        StringList paramLine = StringList.create(";\n    ",";");


        for(Field f: fieldItem.getItemList() ) {
            String fieldTypeNm = f.getTypeInfo().getDefaultTypeName();
            fieldLine.add("private final "+fieldTypeNm +" "+ f.getName());

            argLine.add(fieldTypeNm+" "+f.getName());

            paramLine.add("this."+f.getName()+" = "+f.getName());

        }

        CodeTemplate.Parameters p = CodeTemplate.newParameters();
        String classNm = enumModel.getIdentity().getName();

        p
                .set("packageName", packageName)
                .set("className", classNm)
                .set("enumConstant", sbLine.toString())
                .set("fieldLine", fieldLine.toString())
                .set("argLine", argLine.toString())
                .set("paramLine", paramLine.toString());




        String code = javaClassTmpl.toString(p);

        String filePaht = this.fileBaseDir +"/"+ enumModel.getIdentity().getName()+".java";
        writeFile(filePaht, code);
    }
}
