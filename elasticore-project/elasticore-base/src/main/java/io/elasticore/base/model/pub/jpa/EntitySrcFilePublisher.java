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
import io.elasticore.base.model.*;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.BaseModelDomain;
import io.elasticore.base.model.core.RelationshipManager;
import io.elasticore.base.model.entity.*;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
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
        else
            templatePath = "resource://"+templatePath;

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
        if(entity.getItems()!=null && entity.getItems().size()>0) {

            if (!entity.getMetaInfo().hasMetaAnnotation("abstract")) {
                p.add("@Entity");
            }
        }

        MetaInfo metaInfo = entity.getMetaInfo();
        Annotation annotation = metaInfo.getMetaAnnotation("table");
        if (annotation != null) {
            String dbTblNm = annotation.getValue();
            p.add("@Table(name=\"" + dbTblNm + "\")");
        }

        /*
        if(metaInfo.hasMetaAnnotation("DynamicUpdate"))
            p.add("@org.hibernate.annotations.DynamicUpdate");

         */

        if(!"false".equals(metaInfo.getMetaAnnotationValue("DynamicUpdate"))) {
            p.add("@org.hibernate.annotations.DynamicUpdate");
        }
        if(metaInfo.hasMetaAnnotation("DynamicInsert"))
            p.add("@org.hibernate.annotations.DynamicInsert");



        //Entity = BaseModelDomain.getModelDomain(this.getIdentity().getDomainId()).getModel().getEntityModels().findByName(toName);
        if (metaInfo.hasMetaAnnotation("Embeddable")) {
            p.add("@Embeddable");
        }else {
            ComponentIdentity identity = entity.getIdentity();

            List<ModelRelationship> relationshipList = RelationshipManager.getInstance(identity.getDomainId()).findByToName(identity.getName());
            for(ModelRelationship r : relationshipList){
                if(r.getRelationType() == RelationType.EMBEDDED) {
                    p.add("@Embeddable");
                    break;
                }
            }
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

        CodeTemplate.Paragraph importList = CodeTemplate.newParagraph();
        if(pr.contains("@Convert(")) {
             // TODO
        }

        p
                .set("packageName", packageName)
                .set("enumPackageName", enumPackageName)
                .set("abstract", getAbstractInfo(entity))
                .set("classAnnotationList", this.paragraphForEntity)
                .set("extendInfo", getExtendInfo(entity))
                .set("j2eePkgName",getPersistentPackageName(domain))
                .set("importList", importList)
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
            setFieldColumnAnnotation( entity, f, p);

            /*
            BaseFieldType ft =f.getTypeInfo().getBaseFieldType();
            if(ft == BaseFieldType.DATETIME)
                p.add("@Temporal(TemporalType.TIMESTAMP)");
            else if(ft == BaseFieldType.DATE)
                p.add("@Temporal(TemporalType.DATE)");
            else if(ft == BaseFieldType.TIME)
                p.add("@Temporal(TemporalType.TIME)");

             */

            p.add("%s %s %s;", "private", f.getTypeInfo().getDefaultTypeName(), f.getName());
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

            p.add("@EmbeddedId");
            p.add("private %s %s;", entity.getPkField().getType() , entity.getPkField().getName());
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
            setFieldColumnAnnotation(entity, f, p);

            setRelationInfo(f, p);
            setJoinColumnAnnotation(f, p);
            setNativeAnnotation(f, p);
            setConvertAnnotation(entity.getIdentity().getDomainId(), f,p);

            String defaultValDefined = getDefaultValueSetup(f);

            /*
            BaseFieldType ft =f.getTypeInfo().getBaseFieldType();
            if(ft == BaseFieldType.DATETIME)
                p.add("@Temporal(TemporalType.TIMESTAMP)");
            else if(ft == BaseFieldType.DATE)
                p.add("@Temporal(TemporalType.DATE)");
            else if(ft == BaseFieldType.TIME)
                p.add("@Temporal(TemporalType.TIME)");

             */


            p.add("%s %s %s%s;", "private", f.getTypeInfo().getDefaultTypeName(), f.getName(), defaultValDefined);
            p.add("");
            p.add("");

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

        if (field.hasAnnotation("Embedded")) {
            paragraph.add("@Embedded");
        }

        else if (typeInfo.isList()) {
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

    private String makeLengthInfo(Field field) {
        if(!field.getTypeInfo().isStringType())
            return null;

        String fieldNm = field.getName();

        if(fieldNm.contains("Addr")) {
            return "256";
        }
        else if(fieldNm.contains("Type")) {
            return "10";
        }
        else if(fieldNm.contains("Name") || fieldNm.contains("Nm")) {
            return "64";
        }else if(fieldNm.contains("Code")) {
            return "32";
        }else if(fieldNm.contains("Status")) {
            return "10";
        }else if(fieldNm.contains("Date")) {
            return "8";
        }else if(fieldNm.contains("Time")) {
            return "6";
        }else if(fieldNm.contains("Zip")) {
            return "7";
        }else if(fieldNm.contains("Tel")) {
            return "15";
        }

        return null;
    }

    /**
     * Sets JPA annotation information according to the configuration information of each field.
     *
     * @param field
     * @param paragraph
     */
    private void setFieldColumnAnnotation(Entity entity, Field field, CodeTemplate.Paragraph paragraph) {

        String labelTxt = field.getAnnotationValue("label", "comment");
        if(labelTxt!=null && !labelTxt.isEmpty())
            paragraph.add("@Comment(\"%s\")", labelTxt);


        List<String> list = new ArrayList<>();


        String dbColumnName = field.getDbColumnName();

        if (dbColumnName !=null)
            list.add("name = \"" + dbColumnName + "\"");



        if (field.hasAnnotation("text")) {
            list.add("columnDefinition = \"TEXT\"");
        }
        else if (field.hasAnnotation("longtext")) {
            list.add("columnDefinition = \"LONGTEXT\"");
        }
        if (field.hasAnnotation("notnull")) {
            list.add("nullable = false");
        }

        if("false".equals(field.getAnnotationValue("updatable"))) {
            list.add("updatable = false");
        }

        EnumModel enumModel = this.getEnumModelFromType(entity.getIdentity().getDomainId(),field);

        boolean isLengthDefine = false;
        String lengthVal = field.getAnnotationValue("length", "len");
        if (lengthVal != null) {
            list.add("length = " + lengthVal);
            isLengthDefine = true;
        }else {
            if(enumModel!=null) {
                Annotation dbAnt = enumModel.getMetaInfo().getMetaAnnotation("db");
                if(dbAnt!=null) {
                    String dbFieldNm = dbAnt.getValue();
                    if(dbFieldNm!=null) {
                        Field enumField = enumModel.getFieldItems().findByName(dbFieldNm);
                        if(enumField!=null) {
                            lengthVal = enumField.getAnnotationValue("length", "len");
                            if (lengthVal != null) {
                                list.add("length = " + lengthVal);
                                isLengthDefine = true;
                            }
                        }
                    }
                }

            }
        }

        if(!isLengthDefine) {
            lengthVal = makeLengthInfo(field);
            if (lengthVal != null) {
                list.add("length = " + lengthVal);
            }
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


    private EnumModel getEnumModelFromType(String domainId, Field field) {
        if(field.getTypeInfo().isBaseType()) return null;

        String typeName = field.getTypeInfo().getDefaultTypeName();
        try {
            ModelDomain domain = BaseModelDomain.getModelDomain(domainId);
            EnumModel enumModel = domain.getModel()
                    .getEnumModels().findByName(typeName);

            return enumModel;
        }catch (NullPointerException npe) {}
        return null;
    }

    private void setConvertAnnotation(String domainId, Field field, CodeTemplate.Paragraph paragraph) {
        String type=  field.getTypeInfo().getDefaultTypeName();

        EnumModel enumModel = getEnumModelFromType(domainId, field);
        if (enumModel != null) {
            if (enumModel.getMetaInfo().hasMetaAnnotation("db")) {
                paragraph.add("@Convert(converter = %s.EntityConverter.class)", type);
            }
        }
    }

    private void setNativeAnnotation(Map<String, Annotation> annotationMap, CodeTemplate.Paragraph paragraph) {

        if(this.publishMode==null) return;
        if(annotationMap ==null) return;

        //String prefix = this.publishMode+":";
        String prefix = "jpa:";
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
