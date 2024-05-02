/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.entity.*;
import io.elasticore.base.util.CodeTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Handles the publishing of source files for entities.
 * This class is responsible for generating Java source code
 * based on entity definitions and their annotations.
 */
public class EntitySrcFilePublisher extends SrcFilePublisher {

    private CodeTemplate javaClassTmpl;

    private CodePublisher publisher;

    private String packageName;

    private String enumPackageName;

    // jps | hibernate | px
    private String publishMode;

    private CodeTemplate.Paragraph paragraphForEntity;


    /**
     * Initializes a new instance of the EntitySrcFilePublisher class.
     * Sets up the template path and packages based on the provided publisher.
     *
     * @param publisher The JPACodePublisher instance used for publishing.
     */
    public EntitySrcFilePublisher(CodePublisher publisher) {
        this.publisher = publisher;

        this.publishMode = this.publisher.getECoreModelContext().getDomain().getModel().getConfig("mode");

        String templatePath = this.publisher.getECoreModelContext().getDomain().getModel().getConfig("template.entity");
        if(templatePath==null)
            templatePath = "elasticore-template/entity.tmpl";

        this.javaClassTmpl = CodeTemplate.newInstance(templatePath);

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);
        this.enumPackageName = model.getNamespace(ConstanParam.KEYNAME_ENUMERATION);

    }

    /**
     * Retrieves the extension information for an entity.
     * This method checks if the entity has an "extend" meta-annotation and
     * returns the extension clause (e.g., "extends BaseClass") for the entity.
     * If the "extend" meta-annotation is not present, an empty string is returned.
     *
     * @param entity The entity for which to retrieve the extension information.
     * @return A string containing the extension clause, if applicable; otherwise, an empty string.
     */
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


    /**
     * Generates and publishes Java source code for the specified entity.
     * This method orchestrates the creation of entity classes based on
     * entity definition and annotations.
     *
     * @param domain The model domain to which the entity belongs.
     * @param entity The entity for which source code is to be published.
     */
    public void publish(ModelDomain domain, Entity entity) {

        Annotation typeAnnotation = entity.getMetaInfo().getMetaAnnotation("type");
        if(typeAnnotation!=null) {
            if("template".equals(typeAnnotation.getValue()))
                return;
        }



        publishEntityIdentityClass(domain, entity);

        CodeTemplate.Parameters p = CodeTemplate.newParameters();
        String classNm = entity.getIdentity().getName();

        this.paragraphForEntity = getEntityAnnotation(entity);

        setNativeAnnotation(entity, this.paragraphForEntity);

        // must call 'getFieldInfo' first
        CodeTemplate.Paragraph pr = getFieldInfo(entity);

        p
                .set("packageName", packageName)
                .set("enumPackageName", enumPackageName)
                .set("abstract", getAbstractInfo(entity))
                .set("classAnnotationList", this.paragraphForEntity)
                .set("extendInfo", getExtendInfo(entity))
                .set("implementInfo", "implements java.io.Serializable")
                .set("className", classNm);


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

            BaseFieldType ft =f.getTypeInfo().getBaseFieldType();
            if(ft == BaseFieldType.DATETIME)
                p.add("@Temporal(TemporalType.TIMESTAMP)");
            else if(ft == BaseFieldType.DATE)
                p.add("@Temporal(TemporalType.DATE)");
            else if(ft == BaseFieldType.TIME)
                p.add("@Temporal(TemporalType.TIME)");

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
            setNativeAnnotation(f, p);

            String defaultValDefined = getDefaultValueSetup(f);

            BaseFieldType ft =f.getTypeInfo().getBaseFieldType();
            if(ft == BaseFieldType.DATETIME)
                p.add("@Temporal(TemporalType.TIMESTAMP)");
            else if(ft == BaseFieldType.DATE)
                p.add("@Temporal(TemporalType.DATE)");
            else if(ft == BaseFieldType.TIME)
                p.add("@Temporal(TemporalType.TIME)");


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


        String dbColumnName = null;
        Annotation dbAnnot = field.getAnnotation("db");
        if (dbAnnot != null && dbAnnot.hasValue()){
            dbColumnName =dbAnnot.getValue();
        }else if (field.getTypeInfo().isBaseType()){
            dbColumnName = field.getDbColumnName();
        }

        if (dbColumnName !=null)
            list.add("name = \"" + dbColumnName + "\"");


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

    private void setNativeAnnotation(Entity entity, CodeTemplate.Paragraph paragraph) {
        setNativeAnnotation(entity.getMetaInfo().getMetaAnnotationMap(), paragraph);
    }

    private void setNativeAnnotation(Field field, CodeTemplate.Paragraph paragraph) {
        setNativeAnnotation(field.getAnnotationMap(), paragraph);
    }

    private void setNativeAnnotation(Map<String, Annotation> annotationMap, CodeTemplate.Paragraph paragraph) {

        if(this.publishMode==null) return;
        if(annotationMap ==null) return;

        String prefix = this.publishMode+":";
        annotationMap.entrySet().stream()
                .filter(entry -> entry.getKey().contains(prefix))
                .map(Map.Entry::getValue)
                .forEach(paragraph::add);


    }



    private void setSqlDeleteAnnotation(Field field, CodeTemplate.Paragraph paragraph) {
        if (field.hasAnnotation("chkdelete")) {

        }
    }
}
