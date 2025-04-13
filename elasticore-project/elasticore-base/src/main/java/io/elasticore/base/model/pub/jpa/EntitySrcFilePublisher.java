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
import io.elasticore.base.model.core.ListMap;
import io.elasticore.base.model.core.RelationshipManager;
import io.elasticore.base.model.entity.*;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
import io.elasticore.base.model.shadow.SourceShadowModel;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Handles the publishing of source files for entities.
 * This class is responsible for generating Java source code
 * based on entity definitions and their annotations.
 */
public class EntitySrcFilePublisher extends SrcFilePublisher {

    private CodeTemplate javaClassTmpl;

    private CodePublisher publisher;

    private ECoreModel model;

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
        super(publisher);


        this.publisher = publisher;
        this.model = this.publisher.getECoreModelContext().getDomain().getModel();

        this.publishMode = model.getConfig("mode");

        String templatePath = model.getConfig("template.entity");
        if (templatePath == null)
            templatePath = "elasticore-template/entity.tmpl";
        else
            templatePath = "resource://" + templatePath;

        this.javaClassTmpl = CodeTemplate.newInstance(templatePath);

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);

        if (model.getEnumModels().getItems().size() > 0)
            this.enumPackageName = model.getNamespace(ConstanParam.KEYNAME_ENUMERATION);
        else
            this.enumPackageName = null;

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

        Annotation annotation = entity.getMetaInfo().getMetaAnnotation(EntityAnnotation.META_EXTEND);
        if (annotation != null)
            return "extends " + getClassName(entity, annotation.getValue());
        else {
            String rollupTarget = entity.getMetaInfo().getMetaAnnotationValue(EntityAnnotation.META_ROLL_UP_TARGET);
            if (rollupTarget != null) {
                return "extends " + getClassName(entity, rollupTarget);
            }
        }
        return "";
    }


    private String getAbstractInfo(Entity entity, CodeTemplate.Paragraph p) {
        if (entity.getMetaInfo().hasMetaAnnotation(EntityAnnotation.META_ABSTRACT)) {
            return "abstract";
        }
        /*
        if(p.contains("@DiscriminatorColumn(")) {
            return "abstract";
        }
         */
        return "";
    }

    private CodeTemplate.Paragraph getEntityAnnotation(Entity entity, String j2eePkgNm) {
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();


        AtomicBoolean isInheritance = new AtomicBoolean(false);

        entity.getItems().forEachRemaining(field -> {
            if (field.hasAnnotation(EntityAnnotation.DISCRIMINATOR)) {
                isInheritance.set(true);
                p.add("@Inheritance(strategy = InheritanceType.SINGLE_TABLE)");
                String dbColumnNm = field.getDbColumnName();
                String type = field.getTypeInfo().getDefaultTypeName().toUpperCase(Locale.ROOT);
                p.add("@DiscriminatorColumn(name = \"" + dbColumnNm + "\", discriminatorType = DiscriminatorType." + type + ")");

                String defaultDesciminatorValue = field.getAnnotationValue(EntityAnnotation.DISCRIMINATOR);
                if (defaultDesciminatorValue != null) {
                    p.add("@DiscriminatorValue(\"" + defaultDesciminatorValue + "\")");
                }
            }
        });

        boolean isEntityClass = true;

        if (!isInheritance.get() && entity.getMetaInfo().hasMetaAnnotation(EntityAnnotation.META_ABSTRACT)) {
            isEntityClass = false;
            p.add("@MappedSuperclass");
        } else if (!entity.getMetaInfo().hasMetaAnnotation(EntityAnnotation.META_EMBEDDABLE)) {
            p.add("@Entity");
        }


        MetaInfo metaInfo = entity.getMetaInfo();


        /*
        if(metaInfo.hasMetaAnnotation("DynamicUpdate"))
            p.add("@org.hibernate.annotations.DynamicUpdate");

         */

        if (isEntityClass) {

            CodeTemplate.Paragraph idxPara = CodeTemplate.newParagraph();
            Annotation idxAnnot = metaInfo.getMetaAnnotation(EntityAnnotation.META_INDEX);
            if (idxAnnot != null) {
                idxPara.add("  indexes = {");

                boolean isFirst = true;
                for (Annotation ant : idxAnnot.getSiblings()) {
                    Properties properties = ant.getProperties();
                    String name = properties.getProperty("name");
                    String comlumnList = properties.getProperty("columnList");
                    String unique = properties.getProperty("unique");
                    StringBuilder sb = new StringBuilder();
                    if (name != null && !name.isEmpty()) {
                        sb.append("name=" + StringUtils.quoteString(name));
                    }
                    if (comlumnList != null && !comlumnList.isEmpty()) {
                        if (sb.length() > 0)
                            sb.append(", ");
                        sb.append("columnList=" + StringUtils.quoteString(comlumnList));
                    }
                    if (unique != null && !unique.isEmpty()) {
                        if (sb.length() > 0)
                            sb.append(", ");
                        sb.append("unique=" + unique);
                    }
                    String commaTxt = " ";
                    if (!isFirst)
                        commaTxt = ",";
                    idxPara.add("   %s@%s.persistence.Index(%s)", commaTxt, j2eePkgNm, sb.toString());
                    isFirst = false;

                }
                idxPara.add("  }");
            }

            String tblNameInfo = null;
            Annotation tblAnnotation = metaInfo.getMetaAnnotation(EntityAnnotation.META_TABLE);
            if (tblAnnotation != null) {
                String dbTblNm = tblAnnotation.getValue();
                tblNameInfo = "name=\"" + dbTblNm + "\"";


            }

            if (idxPara.size() > 0 || tblNameInfo != null) {
                if (idxPara.size() > 0) {
                    if (tblNameInfo == null)
                        tblNameInfo = "";
                    p.add("@" + j2eePkgNm + ".persistence.Table(%s", tblNameInfo);
                    p.add(idxPara);
                    p.add("  )");

                } else {
                    p.add("@" + j2eePkgNm + ".persistence.Table(%s)", tblNameInfo);
                }

            }


            if (metaInfo.hasMetaAnnotation(EntityAnnotation.META_IMMUTABLE)) {
                p.add("@org.hibernate.annotations.Immutable");

                if (tblAnnotation == null) {
                    String viewName = metaInfo.getMetaAnnotationValue(EntityAnnotation.META_IMMUTABLE);
                    if (viewName != null) {
                        p.add("@" + j2eePkgNm + ".persistence.Table(name=\"" + viewName + "\")");
                    }
                }
            }

            if (!"false".equals(metaInfo.getMetaAnnotationValue(EntityAnnotation.META_DYNAMIC_UPDATE))) {
                if (!metaInfo.hasMetaAnnotation(EntityAnnotation.META_IMMUTABLE))
                    p.add("@org.hibernate.annotations.DynamicUpdate");
            }
            if (metaInfo.hasMetaAnnotation(EntityAnnotation.META_DYNAMIC_INSERT))
                p.add("@org.hibernate.annotations.DynamicInsert");


        }


        //Entity = BaseModelDomain.getModelDomain(this.getIdentity().getDomainId()).getModel().getEntityModels().findByName(toName);
        if (metaInfo.hasMetaAnnotation(EntityAnnotation.META_EMBEDDABLE)) {
            p.add("@Embeddable");
        } else {
            ComponentIdentity identity = entity.getIdentity();

            List<ModelRelationship> relationshipList = RelationshipManager.getInstance(identity.getDomainId()).findByToName(identity.getName());
            for (ModelRelationship r : relationshipList) {
                if (r.getRelationType() == RelationType.EMBEDDED) {
                    p.add("@Embeddable");
                    break;
                }
            }
        }


        if (metaInfo.hasMetaAnnotation(EntityAnnotation.META_ROLL_UP)) {

            String discriminatorVal = metaInfo.getMetaAnnotationValue(EntityAnnotation.META_ROLL_UP_DISCRIMINATOR);
            if (discriminatorVal == null) {
                discriminatorVal = metaInfo.getMetaAnnotationValue(EntityAnnotation.META_ROLL_UP_TARGET);
            }

            p.add("@DiscriminatorValue(\"" + discriminatorVal + "\")");
        }


        if (entity.getPkField() != null && entity.getPkField().isMultiple()) {
            //@IdClass(PersonFamilyIdentity.class)
            p.add("@IdClass(%s.class)", entity.getPkField().getType());
        }


        return p;
    }

    private SourceShadowModel shadowModel;


    public void publish(ModelDomain domain, EntityModels entityModels) {

        String id = domain.getName() + "_EntityMeta";

        SourceShadowModel shadowModel = new SourceShadowModel(id);

        CodeTemplate.Parameters p = CodeTemplate.newParameters();
        p
                .set("packageName", packageName)
                .set("enumPackageName", enumPackageName);


        CodeTemplate.Paragraph pr = CodeTemplate.newParagraph();
        ModelComponentItems<Entity> items = entityModels.getItems();
        while (items.hasNext()) {
            Entity entity = items.next();
            CodeTemplate.Paragraph entityMeta = getEntityMeta(entity, shadowModel);
            if (entityMeta != null)
                pr.add(entityMeta);
        }
        p.set("fieldList", pr);

        CodeTemplate.Paragraph importList = CodeTemplate.newParagraph();
        Set<String> namespaceSet = shadowModel.getNamespaceSet();
        for (String ns : namespaceSet) {
            importList.add(ns);

            if(ns.indexOf(".entity.")>0) {
                String nsQ = ns.replaceAll("\\*$", "") + "Q.*";
                importList.add(nsQ);
            }
        }
        p.set("importList", importList);

        CodeTemplate codeTemplate = CodeTemplate.newInstance("elasticore-template/Q.tmpl");
        String qualifiedClassName = packageName + ".Q";
        String code = codeTemplate.toString(p);

        this.writeSrcCode(this.publisher, null, qualifiedClassName, code);
    }

    private CodeTemplate.Paragraph getEntityMeta(Entity entity, SourceShadowModel shadowModel) {

        if (entity.getMetaInfo().hasMetaAnnotation(EntityAnnotation.META_ABSTRACT))
            return null;

        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();

        String entityClassNm = entity.getIdentity().getName();
        p.add("public static $%s<%s> %s=new $%s<>();", entityClassNm, entityClassNm, entityClassNm, entityClassNm);
        p.add("public static class $%s<T> {", entityClassNm);
        p.add("");
        p.add("    private FieldInfo p=null;");
        p.add("    public $%s() {}", entityClassNm);
        p.add("    public $%s(FieldInfo p) {this.p=p;}", entityClassNm);

        ListMap allFieldListMap = entity.getAllFieldListMap();
        List<Field> fieldList = allFieldListMap.getList();

        for (Field field : fieldList) {
            String fieldName = field.getIdentity().getName();

            String baseType = "null";
            if (field.getTypeInfo().isGenericType()) {
                baseType = field.getTypeInfo().getBaseFieldType().getName();

                if (baseType != null && !baseType.isEmpty())
                    baseType = baseType + ".class";
            }
            if (!field.getTypeInfo().isBaseType()) {
                shadowModel.setNamespaceInfo(field.getTypeInfo().getCoreItemType());
            }

            p.add("");
            p.add("    public final FieldInfo<T> get%s() {return new FieldInfo(%s.class,%s,%s.class, %s,p);}"
                    , StringUtils.capitalize(fieldName)
                    //, entityClassNm
                    , entityClassNm
                    , StringUtils.quoteString(fieldName)
                    , field.getTypeInfo().getCoreItemType()
                    , baseType

            );
            p.add("    public final String %s=%s;", fieldName, StringUtils.quoteString(fieldName));
            //p.add("    public final FieldInfo get%s() {return F_%s;}" ,StringUtils.capitalize(fieldName),fieldName);

            String typeNm = field.getTypeInfo().getInitTypeInfo();
            Entity refEntity = findEntityByName(typeNm);
            EnumModel enumModel = findEnumModel(typeNm);

            if (field.getTypeInfo().isBaseType() || enumModel != null) {
                p.add("    public Specification<T> %s(Op op,Object value) {return get%s().where(op,value);}"
                        //, entityClassNm
                        , fieldName
                        , StringUtils.capitalize(fieldName)
                );
                p.add("    public Specification<T> %s(Op op,Object value,boolean chkEmpty) {return get%s().where(op,value,chkEmpty);}"
                        //, entityClassNm
                        , fieldName
                        , StringUtils.capitalize(fieldName)
                );
            } else if (refEntity != null) {
                // if entity

                {
                    String refEntityNm = refEntity.getIdentity().getName();
                    p.add("    public final $%s<T> %s() {return new $%s(get%s());}"
                            , refEntityNm
                            , fieldName
                            , refEntityNm
                            , StringUtils.capitalize(fieldName));

                }
            }

        }
        p.add("}");
        p.add("");

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

        Annotation typeAnnotation = entity.getMetaInfo().getMetaAnnotation(EntityAnnotation.META_TYPE);
        if (typeAnnotation != null) {
            if ("template".equals(typeAnnotation.getValue()))
                return;
        }
        String j2eePkgNm = getPersistentPackageName(domain);
        publishEntityIdentityClass(domain, entity);

        CodeTemplate.Parameters p = CodeTemplate.newParameters();
        String classNm = entity.getIdentity().getName();

        this.paragraphForEntity = getEntityAnnotation(entity, j2eePkgNm);

        setNativeAnnotation(entity, this.paragraphForEntity);

        shadowModel = new SourceShadowModel(entity.getIdentity().getName());
        this.model.setShadowModel(shadowModel);

        // must call 'getFieldInfo' first
        CodeTemplate.Paragraph pr = getFieldInfo(entity);

        CodeTemplate.Paragraph importList = CodeTemplate.newParagraph();

        Set<String> namespaceSet = shadowModel.getNamespaceSet();

        for (String ns : namespaceSet) {
            importList.add(ns);
        }


        if (this.paragraphForEntity.contains("@Entity")) {
            importList.add(j2eePkgNm + ".persistence.Entity");
        }

        if (pr.contains("@Convert(")) {
            // TODO
            //importList.add(j2eePkgNm+".persistence.Convert");
        }

        p
                .set("packageName", packageName)
                .set("enumPackageName", enumPackageName)
                .set("abstract", getAbstractInfo(entity, this.paragraphForEntity))
                .set("classAnnotationList", this.paragraphForEntity)
                .set("extendInfo", getExtendInfo(entity))
                .set("j2eePkgName", j2eePkgNm)
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

            String j2eePkgNm = getPersistentPackageName(domain);

            CodeTemplate.Paragraph p2 = CodeTemplate.newParagraph();
            p2.add("@Embeddable");
            p2.add("@lombok.EqualsAndHashCode");
            p2.add("@AllArgsConstructor");

            CodeTemplate.Parameters param = CodeTemplate.newParameters();
            param
                    .set("packageName", packageName)
                    .set("abstract", "")
                    .set("extendInfo", "")
                    .set("j2eePkgName", j2eePkgNm)
                    .set("implementInfo", "implements java.io.Serializable")
                    .set("classAnnotationList", p2)
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
            setFieldColumnAnnotation(entity, f, p);

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
        /*
        if (entity.getPkField() != null && entity.getPkField().isMultiple()) {
            isExtendIdentity = true;

            p.add("@EmbeddedId");
            p.add("private %s %s;", entity.getPkField().getType() , entity.getPkField().getName());
            p.add("");
        }
        */


        String parentName = entity.getMetaInfo().getMetaAnnotationValue(EntityAnnotation.META_EXTEND);

        String entityName = entity.getIdentity().getName();

        // for prePersist
        boolean isMultiplePK = entity.getPkField() != null && entity.getPkField().isMultiple();
        CodeTemplate.Paragraph prePersist = CodeTemplate.newParagraph();


        while (fields.hasNext()) {
            Field f = fields.next();

            if (shadowModel.getField(f.getName()) != null)
                continue;

            if (isExtendIdentity) {
                if (f.isPrimaryKey())
                    continue;
            }

            setFieldDesc(f, p);

            boolean isIdField =
                    setFieldPkInfo(entity, f, p);

            setFieldLabel(entity, f, p);
            boolean hasColumnInfo = setRelationInfo(f, p, entity);
            if (!hasColumnInfo)
                setFieldColumnAnnotation(entity, f, p);

            setEnumListAnnotation(entity, f, p);


            setJoinColumnAnnotation(f, p);
            setNativeAnnotation(f, p);
            setConvertAnnotation(entity.getIdentity().getDomainId(), f, p);


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

            shadowModel.addField(f);

            p.add("%s %s %s%s;", "private", f.getTypeInfo().getDefaultTypeName(), f.getName(), defaultValDefined);
            p.add("");
            p.add("");

            if (isIdField) {
                String idGenerator = f.getAnnotationValue("genid");
                if (idGenerator == null && f.hasAnnotation("genid")) {
                    if (f.getTypeInfo().getBaseFieldType() == BaseFieldType.STRING) {
                        idGenerator = "java.util.UUID.randomUUID().toString";
                    }
                }

                if (idGenerator != null) {

                    if (!isMultiplePK) {
                        p.add("@PrePersist");
                        p.add("public void prePersist() {");
                        p.add("  if (%s == null)", f.getName());
                        p.add("        %s = %s();", f.getName(), idGenerator);
                        p.add("}");
                        p.add("");
                        p.add("");
                    } else {

                        prePersist.add("  if (%s == null)", f.getName());
                        prePersist.add("        %s = %s();", f.getName(), idGenerator);
                    }

                }
            }
        }

        if (isMultiplePK && prePersist.size() > 0) {
            p.add("@PrePersist");
            p.add("public void prePersist() {");
            p.add(prePersist);
            p.add("}");
            p.add("");
            p.add("");
        }

        Annotation templateAnt = entity.getMetaInfo().getMetaAnnotation("template");
        if (templateAnt != null) {
            String templates = templateAnt.getValue();

            if (templates != null && templates.length() > 0) {
                String[] templateNmArray = templates.split(",");

                for (String templateNm : templateNmArray) {
                    Entity templateEntity = this.publisher.getECoreModelContext().findModelComponent(templateNm, Entity.class);
                    if (templateEntity != null)
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


    private boolean setFieldPkInfo(Entity entity, Field field, CodeTemplate.Paragraph paragraph) {

        boolean isIdField = false;

        boolean isSequence = field.hasAnnotation("sequence");

        if (field.hasAnnotation("id") || field.hasAnnotation("pk") || field.isPrimaryKey()) {
            paragraph.add("@Id");
            isIdField = true;

            if (isSequence) {
                paragraph.add("@GeneratedValue(strategy = GenerationType.IDENTITY)");
            }

        } else {
            if (isSequence) {
                String seqName = entity.getIdentity().getName() + "_" + field.getName() + "_seq";

                paragraph.add("@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = \"seq_gen\")");
                paragraph.add("@GenericGenerator(");
                paragraph.add("  name = \"seq_gen\",");
                paragraph.add("  strategy = \"org.hibernate.id.enhanced.SequenceStyleGenerator\",");
                paragraph.add("  parameters = {");
                paragraph.add("    @Parameter(name = \"sequence_name\", value = \"%s\"),", seqName);
                paragraph.add("    @Parameter(name = \"initial_value\", value = \"1\"),");
                paragraph.add("    @Parameter(name = \"increment_size\", value = \"1\")");
                paragraph.add("}");
                paragraph.add(")");
            }
        }


        if (field.getGenerationType() != null) {
            paragraph.add("@GeneratedValue(strategy = GenerationType." + field.getGenerationType());
        }

        return isIdField;
    }

    private String getMappedByName(Field field, Entity entity) {
        if (field.getTypeInfo().isList()) {
            String fieldCoreType = field.getTypeInfo().getCoreItemType();

            Entity byName = this.publisher.getECoreModelContext().findModelComponent(fieldCoreType, Entity.class);
            ModelComponentItems<Field> items = byName.getItems();
            String entityClassName = entity.getIdentity().getName();
            while (items.hasNext()) {
                Field f = items.next();
                if (entityClassName.equals(f.getTypeInfo().getInitTypeInfo())) {
                    return f.getName();
                }
            }
        }
        return null;
    }

    /**
     * Sets annotation information for configuring relational information in JPA.
     *
     * @param field     Field
     * @param paragraph paragraph
     */
    private boolean setRelationInfo(Field field, CodeTemplate.Paragraph paragraph, Entity ety) {
        TypeInfo typeInfo = field.getTypeInfo();

        boolean hasColumnInfo = false;

        boolean isEntityType = false;

        if (typeInfo.isBaseType())
            return false;

        Entity typeEntity = this.publisher.getECoreModelContext().findModelComponent(typeInfo.getCoreItemType(), Entity.class);


        if (typeEntity != null)
            isEntityType = true;

        String fetchType = field.getAnnotationValue(EntityAnnotation.FETCH);

        if (isEntityType &&
                (field.hasAnnotation(EntityAnnotation.EMBEDDED)
                        || typeEntity.getMetaInfo().hasMetaAnnotation(EntityAnnotation.META_EMBEDDABLE))) {
            paragraph.add("@Embedded");

            String embPrefix = field.getAnnotationValue(EntityAnnotation.EMBEDDED_PREFIX);
            if (embPrefix != null) {
                paragraph.add("@AttributeOverrides({");

                boolean isFirst = true;
                ModelComponentItems<Field> items = typeEntity.getItems();

                while (items.hasNext()) {

                    Field ef = items.next();

                    String commaTxt = "";
                    if (!isFirst)
                        commaTxt = ",";

                    String newName = embPrefix + "_" + StringUtils.camelToSnake(ef.getName());
                    paragraph.add(" %s @AttributeOverride(name = \"%s\", column = @Column(name = \"%s\"))", commaTxt, ef.getName(), newName);

                    isFirst = false;
                }
                paragraph.add("})");
            }

        } else if (typeInfo.isList()) {
            if (isEntityType) {


                if (fetchType == null)
                    fetchType = "LAZY";
                else
                    fetchType = fetchType.toUpperCase();

                String extPropsTxt = "";
                if (field.hasAnnotation(EntityAnnotation.CASCADE)) {
                    String cascadeType = field.getAnnotationValue(EntityAnnotation.CASCADE);
                    if (cascadeType == null)
                        cascadeType = "ALL, orphanRemoval = true";
                    else
                        cascadeType = cascadeType.toUpperCase(Locale.ROOT);

                    extPropsTxt = ",cascade = CascadeType." + cascadeType;
                }

                String mappedByName = getMappedByName(field, ety);
                if (mappedByName != null) {
                    extPropsTxt = extPropsTxt + ",mappedBy=\"" + mappedByName + "\"";
                }

                String relationType = "OneToMany";
                if (field.hasAnnotation(EntityAnnotation.MANY_TO_MANY))
                    relationType = "ManyToMany";

                if (!field.hasAnnotation("jpa:onetomany")) {
                    paragraph.add("@%s(fetch = FetchType.%s %s)",relationType, fetchType, extPropsTxt);
                }
            }

        } else if (isEntityType) {


            String targetEntityName = typeInfo.getDefaultTypeName();
            Entity entity = this.publisher.getECoreModelContext().findModelComponent(targetEntityName, Entity.class);

            if (entity != null) {

                String relationType = "ManyToOne";
                if (field.hasAnnotation(EntityAnnotation.ONE_TO_ONE))
                    relationType = "OneToOne";
                else if (field.hasAnnotation(EntityAnnotation.MANY_TO_MANY))
                    relationType = "ManyToMany";

                if (fetchType != null) {
                    fetchType = fetchType.toUpperCase();
                    paragraph.add("@%s(fetch = FetchType.%s)", relationType, fetchType);


                } else {
                    paragraph.add("@%s", relationType);
                }


                String joinFieldName = field.getName() + "_id";
                if (!field.hasAnnotation(EntityAnnotation.JOIN) && !field.hasAnnotation("jpa:joincolumn")) {
                    String nullable = "true";
                    if (field.hasAnnotation(EntityAnnotation.NOT_NULL)) {
                        nullable = "false";
                        paragraph.add(("@JoinColumn(columnDefinition = \"" + joinFieldName + "\", nullable = " + nullable + ")"));
                    } else {
                        paragraph.add(("@JoinColumn(columnDefinition = \"" + joinFieldName + "\")"));
                    }

                    hasColumnInfo = true;
                }
            }

        }

        return hasColumnInfo;
    }

    private String makeLengthInfo(Field field) {
        if (!field.getTypeInfo().isStringType())
            return null;

        String fieldNm = field.getName();

        if (fieldNm.contains("Addr")) {
            return "256";
        } else if (fieldNm.contains("Type")) {
            return "10";
        } else if (fieldNm.contains("Name") || fieldNm.contains("Nm")) {
            return "64";
        } else if (fieldNm.contains("Code")) {
            return "32";
        } else if (fieldNm.contains("Status")) {
            return "10";
        } else if (fieldNm.contains("Date")) {
            // yyyy-MM-dd
            return "10";
        } else if (fieldNm.contains("Time")) {
            // HH:mm:ss
            return "8";
        } else if (fieldNm.contains("Zip")) {
            return "7";
        } else if (fieldNm.contains("Tel")) {
            return "15";
        }

        return null;
    }

    protected void setFieldLabel(Entity entity, Field field, CodeTemplate.Paragraph paragraph) {
        String labelTxt = field.getAnnotationValue(EntityAnnotation.COMMENT);
        if (labelTxt != null && !labelTxt.isEmpty())
            paragraph.add("@Comment(\"%s\")", labelTxt);
    }

    /**
     * Sets JPA annotation information according to the configuration information of each field.
     *
     * @param field
     * @param paragraph
     */
    private void setFieldColumnAnnotation(Entity entity, Field field, CodeTemplate.Paragraph paragraph) {


        List<String> list = new ArrayList<>();
        List<String> list4Enum = new ArrayList<>(); // for Enumeration


        String dbColumnName = field.getDbColumnName();

        if (dbColumnName != null) {
            list.add("name = \"" + dbColumnName + "\"");
            list4Enum.add("name = \"" + dbColumnName + "\"");
        }


        String columnDefinition = field.getAnnotationValue("columndefinition", "def");

        if (field.getColumnDefinition() != null) {
            list.add("columnDefinition=\"" + field.getColumnDefinition() + "\"");
        } else if (columnDefinition != null) {
            list.add("columnDefinition = \"" + columnDefinition + "\"");
        } else if (field.hasAnnotation("text")) {
            list.add("columnDefinition = \"TEXT\"");
        } else if (field.hasAnnotation("longtext")) {
            list.add("columnDefinition = \"LONGTEXT\"");
        }

        if (field.hasAnnotation("notnull")) {
            list.add("nullable = false");
            list4Enum.add("nullable = false");
        }

        if ("false".equals(field.getAnnotationValue(EntityAnnotation.UPDATABLE))) {
            list.add("updatable = false");
            list4Enum.add("updatable = false");
        }

        EnumModel enumModel = this.getEnumModelFromType(field);


        boolean isLengthDefine = false;
        String lengthVal = field.getAnnotationValue(EntityAnnotation.LENGTH);
        if (lengthVal != null) {
            list.add("length = " + lengthVal);
            list4Enum.add("length = " + lengthVal);
            isLengthDefine = true;
        } else {
            if (enumModel != null) {
                Annotation dbAnt = enumModel.getMetaInfo().getMetaAnnotation("db");
                if (dbAnt != null) {
                    String dbFieldNm = dbAnt.getValue();
                    if (dbFieldNm != null) {
                        Field enumField = enumModel.getFieldItems().findByName(dbFieldNm);
                        if (enumField != null) {
                            lengthVal = enumField.getAnnotationValue(EntityAnnotation.LENGTH);
                            if (lengthVal != null) {
                                list4Enum.add("length = " + lengthVal);
                                isLengthDefine = true;
                            }
                        }
                    }
                }

            }
        }

        if (!isLengthDefine) {
            lengthVal = makeLengthInfo(field);
            if (lengthVal != null) {
                list.add("length = " + lengthVal);
            }
        }


        Annotation uniqAnnot = field.getAnnotation("unique");
        if (uniqAnnot != null && !"false".equals(uniqAnnot.getValue()))
            list.add("unique = true");

        //insertable=false, updatable=false
        if (field.hasAnnotation("discriminator")) {
            list.add("insertable = false");
            list.add("updatable = false");
        }


        //if (list.size() > 0 && !isEnumerationType(field)) {
        if (list.size() > 0) {
            String txt = "@Column(" + String.join(", ", list) + ")";
            paragraph.add(txt);
        } else if (list4Enum.size() > 0) {
            String txt = "@Column(" + String.join(", ", list4Enum) + ")";
            paragraph.add(txt);
        }
    }

    private void setEnumListAnnotation(Entity entity, Field field, CodeTemplate.Paragraph p) {
        TypeInfo typeInfo = field.getTypeInfo();
        if (!typeInfo.isList()) return;

        String enumName = typeInfo.getCoreItemType();
        EnumModel enumModel = this.model.getEnumModels().findByName(enumName);
        if (enumModel == null) return;

        String tblName = StringUtils.camelToSnake(entity.getIdentity().getName())
                + "_" + StringUtils.camelToSnake(field.getName());


        p.add("@ElementCollection( fetch = FetchType.EAGER)");
        p.add("@CollectionTable(name = \"%s\", joinColumns = @JoinColumn(name = \"map_seq\"))", tblName);
        p.add("@Column(name = \"code\")");
    }


    private void setJoinColumnAnnotation(Field field, CodeTemplate.Paragraph paragraph) {
        List<String> list = new ArrayList<>();

        if (field.hasAnnotation("join")) {
            String fieldNm = field.getAnnotation("join").getValue();
            list.add("columnDefinition=\"" + fieldNm + "\"");
        }

        if (list.size() > 0 && !field.hasAnnotation("jpa:joincolumn")) {
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


    private EnumModel getEnumModelFromType(Field field) {
        if (field.getTypeInfo().isBaseType()) return null;

        String typeName = field.getTypeInfo().getCoreItemType();

        return this.findEnumModel(typeName);
    }

    private void setConvertAnnotation(String domainId, Field field, CodeTemplate.Paragraph paragraph) {
        String type = field.getTypeInfo().getCoreItemType();

        EnumModel enumModel = getEnumModelFromType(field);
        if (enumModel != null) {
            if (enumModel.getMetaInfo().hasMetaAnnotation("db")) {
                paragraph.add("@Convert(converter = %s.EntityConverter.class)", type);
            }
        }
    }

    private void setNativeAnnotation(Map<String, Annotation> annotationMap, CodeTemplate.Paragraph paragraph) {

        if (this.publishMode == null) return;
        if (annotationMap == null) return;

        //String prefix = this.publishMode+":";
        String prefix = "jpa:";
        annotationMap.entrySet().stream()
                .filter(entry -> entry.getKey().contains(prefix))
                .map(Map.Entry::getValue)
                .forEach(paragraph::add);


        String prefix2 = "entity:";
        annotationMap.entrySet().stream()
                .filter(entry -> entry.getKey().contains(prefix2))
                .map(Map.Entry::getValue)
                .forEach(paragraph::add);
    }


    private void setSqlDeleteAnnotation(Field field, CodeTemplate.Paragraph paragraph) {
        if (field.hasAnnotation("chkdelete")) {

        }
    }
}
