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
import io.elasticore.base.model.core.ListMap;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.*;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handles the publishing of source files for entities.
 * This class is responsible for generating Java source code
 * based on entity definitions and their annotations.
 */
public class DtoSrcFilePublisher extends SrcFilePublisher {

    private CodeTemplate javaClassTmpl;

    private CodePublisher publisher;

    private String packageName;

    private String enumPackageName;

    // jps | hibernate | px
    private String publishMode;

    private CodeTemplate.Paragraph paragraphForEntity;

    private ECoreModel eCoreModel;


    /**
     * Initializes a new instance of the EntitySrcFilePublisher class.
     * Sets up the template path and packages based on the provided publisher.
     *
     * @param publisher The JPACodePublisher instance used for publishing.
     */
    public DtoSrcFilePublisher(CodePublisher publisher) {
        super(publisher);

        this.publisher = publisher;
        this.eCoreModel = this.publisher.getECoreModelContext().getDomain().getModel();

        this.publishMode = this.eCoreModel.getConfig("mode");

        String templatePath = this.publisher.getECoreModelContext().getDomain().getModel().getConfig("template.dto");
        if (templatePath == null)
            templatePath = "elasticore-template/dto.tmpl";
        else
            templatePath = "resource://"+templatePath;

        this.javaClassTmpl = CodeTemplate.newInstance(templatePath);

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_DTO);

        if(model.getEnumModels().getItems().size()>0)
            this.enumPackageName = model.getNamespace(ConstanParam.KEYNAME_ENUMERATION);
        else
            this.enumPackageName ="";

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
    private String getExtendInfo(MetaInfoModel entity) {


        Annotation annotation = entity.getMetaInfo().getMetaAnnotation("extend");
        if (annotation != null)
            return "extends " + annotation.getValue();

        return "";
    }


    private String getAbstractInfo(MetaInfoModel entity) {
        if (entity.getMetaInfo().hasMetaAnnotation("abstract")) {
            return "abstract";
        }
        return "";
    }


    /**
     * Generates and publishes Java source code for the specified entity.
     * This method orchestrates the creation of entity classes based on
     * entity definition and annotations.
     *
     * @param domain The model domain to which the entity belongs.
     * @param entity The entity for which source code is to be published.
     */
    public void publish(ModelDomain domain, DataTransfer entity) {
        MetaInfo metaInfo = entity.getMetaInfo();
        Annotation typeAnnotation = metaInfo.getMetaAnnotation("type");
        if (typeAnnotation != null) {
            if ("template".equals(typeAnnotation.getValue()))
                return;
        }

        if(metaInfo.hasMetaAnnotation("searchable")) return;


        CodeTemplate.Parameters p = CodeTemplate.newParameters();
        String classNm = entity.getIdentity().getName();


        this.paragraphForEntity = CodeTemplate.newParagraph();

        setNativeAnnotation(entity, this.paragraphForEntity);

        boolean isSearchDTO = false;

        // must call 'getFieldInfo' first
        CodeTemplate.Paragraph pr = getFieldInfo(entity);

        p
                .set("packageName", packageName)
                .set("j2eePkgName",getPersistentPackageName(domain))
                .set("enumPackageName", enumPackageName)
                .set("abstract", getAbstractInfo(entity))
                .set("classAnnotationList", this.paragraphForEntity)
                .set("extendInfo", getExtendInfo(entity))
                .set("isSearchDTO", isSearchDTO)
                .set("implementInfo", "implements java.io.Serializable")
                .set("className", classNm);


        p.set("fieldList", pr);

        String qualifiedClassName = packageName + "." + classNm;
        String code = javaClassTmpl.toString(p);

        this.writeSrcCode(this.publisher, entity, qualifiedClassName, code);
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


            /*
            BaseFieldType ft = f.getTypeInfo().getBaseFieldType();
            if (ft == BaseFieldType.DATETIME)
                p.add("@Temporal(TemporalType.TIMESTAMP)");
            else if (ft == BaseFieldType.DATE)
                p.add("@Temporal(TemporalType.DATE)");
            else if (ft == BaseFieldType.TIME)
                p.add("@Temporal(TemporalType.TIME)");

             */

            String code = String.format("%s %s %s;", "private", f.getTypeInfo().getDefaultTypeName(), f.getName());
            p.add(code);
            p.add("");
        }
        return p;
    }

    private CodeTemplate.Paragraph getFieldInfo(DataModelComponent entity) {
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();

        loadFieldInfo(entity, p);
        return p;
    }



    private void loadFieldInfo(DataModelComponent dto, CodeTemplate.Paragraph p) {

        ListMap<String, Field> fields = dto.getAllFieldListMap();

        List<Field> fieldList = fields.getList();

        for(Field f: fieldList) {

            if(f.hasAnnotation("disable"))
                continue;

            if(isEntityReturnType(f, eCoreModel)) continue;

            setFieldDesc(f, p);
            setFieldDocumentation(f,p);
            setFieldValidation(f,p);
            setJsonInfo(f, p);
            setFormatAnnotation(f,p);


            setNativeAnnotation(f, p);

            String defaultValDefined = getDefaultValueSetup(f);

            /*
            BaseFieldType ft = f.getTypeInfo().getBaseFieldType();
            if (ft == BaseFieldType.DATETIME)
                p.add("@Temporal(TemporalType.TIMESTAMP)");
            else if (ft == BaseFieldType.DATE)
                p.add("@Temporal(TemporalType.DATE)");
            else if (ft == BaseFieldType.TIME)
                p.add("@Temporal(TemporalType.TIME)");

             */


            p.add("%s %s %s%s;", "private", f.getTypeInfo().getDefaultTypeName(), f.getName(), defaultValDefined);
            p.add("");

            setFunctionInfo(f, p);

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





    private void setNativeAnnotation(MetaInfoModel entity, CodeTemplate.Paragraph paragraph) {
        setNativeAnnotation(entity.getMetaInfo().getMetaAnnotationMap(), paragraph);
    }

    private void setNativeAnnotation(Field field, CodeTemplate.Paragraph paragraph) {
        setNativeAnnotation(field.getAnnotationMap(), paragraph);
    }

    private void setNativeAnnotation(Map<String, Annotation> annotationMap, CodeTemplate.Paragraph paragraph) {

        if (annotationMap == null)
            return;

        if (this.publishMode == null) return;

        String prefix = "dto:";
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
