package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.entity.TypeInfo;
import io.elasticore.base.model.pub.JPACodePublisher;
import io.elasticore.base.util.CodeTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EntitySrcFilePublisher extends SrcFilePublisher {

    private CodeTemplate javaClassTmpl;

    private JPACodePublisher publisher;


    private String packageName;

    private String enumPackageName;


    public EntitySrcFilePublisher(JPACodePublisher publisher) {
        this.publisher = publisher;

        String templatePath = this.publisher.getECoreModelContext().getDomain().getModel().getConfig("template.entity");
        if(templatePath==null)
            templatePath = "elasticore-template/entity.tmpl";

        this.javaClassTmpl = CodeTemplate.newInstance(templatePath);

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);
        this.enumPackageName = model.getNamespace(ConstanParam.KEYNAME_ENUMERATION);

    }

    private String getExtendInfo(Entity entity) {

        Annotation annotation = entity.getMetaInfo().getMetaAnnotation("extend");
        if (annotation != null)
            return "extends " + annotation.getValue();

        return "";


    }


    private String getAbstractInfo(Entity entity) {
        if (entity.getMetaInfo().hasMetaAnnotation("abstract")) {
            return "abstract";
        }
        return "";
    }

    private CodeTemplate.Paragraph getEntityAnnotation(Entity entity) {
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();
        if(entity.getItems()!=null && entity.getItems().size()>0)
            p.add("@Entity");

        MetaInfo metaInfo = entity.getMetaInfo();
        Annotation annotation = metaInfo.getMetaAnnotation("table");
        if (annotation != null) {
            String dbTblNm = annotation.getValue();
            p.add("@Table(name=\"" + dbTblNm + "\")");
        }


        entity.getItems().forEachRemaining(field -> {
            if (field.hasAnnotation("discriminator")) {
                p.add("@Inheritance(strategy = InheritanceType.SINGLE_TABLE)");
                String dbColumnNm = field.getDbColumnName();
                String type = field.getTypeInfo().getDefaultTypeName().toUpperCase(Locale.ROOT);
                p.add("@DiscriminatorColumn(name = \"" + dbColumnNm + "\", discriminatorType = DiscriminatorType." + type + ")");
            }
        });

        if (metaInfo.hasMetaAnnotation("rollup")) {
            String disVal = metaInfo.getMetaAnnotation("rollup").getValue();
            p.add("@DiscriminatorValue(\"" + disVal + "\")");
        }


        return p;
    }

    public void publish(ModelDomain domain, Entity entity) {

        Annotation typeAnnotation = entity.getMetaInfo().getMetaAnnotation("type");
        if(typeAnnotation!=null) {
            if("template".equals(typeAnnotation.getValue()))
                return;
        }

        //for
        publishEntityIdentityClass(domain, entity);

        CodeTemplate.Parameters p = CodeTemplate.newParameters();

        String classNm = entity.getIdentity().getName();

        p
                .set("packageName", packageName)
                .set("enumPackageName", enumPackageName)
                .set("abstract", getAbstractInfo(entity))
                .set("classAnnotationList", getEntityAnnotation(entity))
                .set("extendInfo", getExtendInfo(entity))
                .set("implementInfo", "implements java.io.Serializable")
                .set("className", classNm);


        CodeTemplate.Paragraph pr = getFieldInfo(entity);
        p.set("fieldList", pr);

        String qualifiedClassName = packageName + "." + classNm;
        String code = javaClassTmpl.toString(p);

        this.writeSrcCode(this.publisher, entity, qualifiedClassName, code);
    }


    /**
     * @param domain
     * @param entity
     */
    private void publishEntityIdentityClass(ModelDomain domain, Entity entity) {
        if (entity.getPkField() != null && entity.getPkField().isMultiple()) {

            String classNm = entity.getPkField().getType();

            CodeTemplate.Parameters param = CodeTemplate.newParameters();
            param
                    .set("packageName", packageName)
                    .set("abstract", "")
                    .set("extendInfo", "")
                    .set("implementInfo", "implements java.io.Serializable")
                    .set("classAnnotationList", "@Embeddable")
                    .set("className", classNm);

            CodeTemplate.Paragraph p = getMultiplePkField(entity);
            param.set("fieldList", p);

            String qualifiedClassName = packageName + "." + classNm;
            String code = javaClassTmpl.toString(param);

            this.writeSrcCode(this.publisher, entity, qualifiedClassName, code);
        }
    }

    private CodeTemplate.Paragraph getMultiplePkField(Entity entity) {
        ModelComponentItems<Field> fields = entity.getItems();
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();
        while (fields.hasNext()) {
            Field f = fields.next();

            if (!f.isPrimaryKey())
                continue;
            ;

            setFieldDesc(f, p);
            //setFieldPkInfo(f, p);
            setFieldColumnAnnotation(f, p);

            String code = String.format("%s %s %s;", "private", f.getTypeInfo().getDefaultTypeName(), f.getName());
            p.add(code);
            p.add("");
        }
        return p;
    }

    private CodeTemplate.Paragraph getFieldInfo(Entity entity) {
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();

        loadFieldInfo(entity, p);
        return p;
    }


    private void loadFieldInfo(Entity entity, CodeTemplate.Paragraph p) {
        ModelComponentItems<Field> fields = entity.getItems();


        boolean isExtendIdentity = false;
        if (entity.getPkField() != null && entity.getPkField().isMultiple()) {
            isExtendIdentity = true;
            String code = String.format("private %s id;", entity.getPkField().getType());
            p.add("@EmbeddedId");
            p.add(code);
            p.add("");
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

            setRelationInfo(f, p);
            setJoinColumnAnnotation(f, p);

            String defaultValDefined = getDefaultValueSetup(f);


            String code = String.format("%s %s %s%s;", "private", f.getTypeInfo().getDefaultTypeName(), f.getName(), defaultValDefined);
            p.add(code);
            p.add("\n");

        }

        Annotation templateAnt = entity.getMetaInfo().getMetaAnnotation("template");
        if(templateAnt!=null) {
            String templates = templateAnt.getValue();

            if(templates!=null && templates.length()>0) {
                String[] templateNmArray = templates.split(",");
                EntityModels models = this.publisher.getECoreModelContext().getDomain().getModel().getEntityModels();
                for(String templateNm : templateNmArray) {
                    Entity templateEntity = models.findByName(templateNm);
                    if(templateEntity!=null)
                        loadFieldInfo(templateEntity, p);
                }
            }
            System.err.println(templates);
        }
    }

    private String getDefaultValueSetup(Field f) {
        if (!f.hasAnnotation("default")) return "";
        String val = f.getAnnotation("default").getValue();
        if (f.getTypeInfo().isStringType()) {
            return " = \"" + val + "\"";
        } else {
            return " = " + val;
        }
    }

    private void setFieldDesc(Field field, CodeTemplate.Paragraph paragraph) {
        if (field.getDescription() != null) {
            paragraph.add("// " + field.getDescription());
        }

    }

    private void setFieldPkInfo(Field field, CodeTemplate.Paragraph paragraph) {

        if (field.hasAnnotation("id") || field.hasAnnotation("pk") || field.isPrimaryKey()) {
            paragraph.add("@Id");

            if (field.hasAnnotation("sequence")) {
                paragraph.add("@GeneratedValue(strategy = GenerationType.IDENTITY)");
            }
        }


        if (field.getGenerationType() != null) {
            paragraph.add("@GeneratedValue(strategy = GenerationType." + field.getGenerationType());
        }
    }

    /**
     * Sets annotation information for configuring relational information in JPA.
     *
     * @param field     Field
     * @param paragraph paragraph
     */
    private void setRelationInfo(Field field, CodeTemplate.Paragraph paragraph) {
        TypeInfo typeInfo = field.getTypeInfo();
        if (typeInfo.isList()) {
            paragraph.add(("@OneToMany(fetch = FetchType.LAZY)"));
        } else if (!typeInfo.isBaseType()) {


            String targetEntityName = typeInfo.getDefaultTypeName();
            Entity entity = this.publisher.getECoreModelContext().getDomain().getModel().getEntityModels()
                    .findByName(targetEntityName);

            if (entity != null) {
                if (field.hasAnnotation("onetoone")) {
                    paragraph.add(("@OneToOne"));
                } else {
                    paragraph.add(("@ManyToOne"));
                }


                String joinFieldName = field.getName() + "_id";
                if (!field.hasAnnotation("join")) {
                    paragraph.add(("@JoinColumn(columnDefinition = \"" + joinFieldName + "\")"));
                }
            }

        }
    }

    /**
     * Sets JPA annotation information according to the configuration information of each field.
     *
     * @param field
     * @param paragraph
     */
    private void setFieldColumnAnnotation(Field field, CodeTemplate.Paragraph paragraph) {
        List<String> list = new ArrayList<>();


        if (field.getTypeInfo().isBaseType())
            list.add("name = \"" + field.getDbColumnName() + "\"");


        if (field.hasAnnotation("text")) {
            list.add("columnDefinition = \"TEXT\"");
        }

        if (field.hasAnnotation("notnull")) {
            list.add("nullable = false");
        }

        if (field.hasAnnotation("updatable")) {
            if ("false".equals(field.getAnnotation("updatable")))
                list.add("updatable = false");
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

        //insertable=false, updatable=false
        if (field.hasAnnotation("discriminator")) {
            list.add("insertable = false");
            list.add("updatable = false");
        }


        if (list.size() > 0) {
            String txt = "@Column(" + String.join(", ", list) + ")";
            paragraph.add(txt);
        }
    }


    private void setJoinColumnAnnotation(Field field, CodeTemplate.Paragraph paragraph) {
        List<String> list = new ArrayList<>();

        if (field.hasAnnotation("join")) {
            String fieldNm = field.getAnnotation("join").getValue();
            list.add("columnDefinition=\"" + fieldNm + "\"");
        }

        if (list.size() > 0) {
            String txt = "@JoinColumn(" + String.join(", ", list) + ")";
            paragraph.add(txt);
        }
    }
}
