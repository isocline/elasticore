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
import io.elasticore.base.model.entity.BaseFieldType;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.util.CodeTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handles the publishing of source files for entities.
 * This class is responsible for generating Java source code
 * based on entity definitions and their annotations.
 */
public class SearchDtoSrcFilePublisher extends SrcFilePublisher {

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
    public SearchDtoSrcFilePublisher(CodePublisher publisher) {
        this.publisher = publisher;

        this.publishMode = this.publisher.getECoreModelContext().getDomain().getModel().getConfig("mode");

        String templatePath = this.publisher.getECoreModelContext().getDomain().getModel().getConfig("template.dto");
        if (templatePath == null)
            templatePath = "elasticore-template/dto.tmpl";
        else
            templatePath = "resource://" + templatePath;

        this.javaClassTmpl = CodeTemplate.newInstance(templatePath);

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_DTO);

        if (model.getEnumModels().getItems().size() > 0)
            this.enumPackageName = model.getNamespace(ConstanParam.KEYNAME_ENUMERATION);
        else
            this.enumPackageName = "";

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
     * Generates and publishes Java source code for the specified dtoModel.
     * This method orchestrates the creation of dtoModel classes based on
     * dtoModel definition and annotations.
     *
     * @param domain The model domain to which the dtoModel belongs.
     * @param dtoModel The dtoModel for which source code is to be published.
     */
    public void publish(ModelDomain domain, DataTransfer dtoModel) {

        MetaInfo metaInfo = dtoModel.getMetaInfo();

        Annotation typeAnnotation = metaInfo.getMetaAnnotation("type");
        if (typeAnnotation != null) {
            if ("template".equals(typeAnnotation.getValue()))
                return;
        }

        if(!metaInfo.hasMetaAnnotation("searchable")) return;

        CodeTemplate.Parameters p = CodeTemplate.newParameters();
        String classNm = dtoModel.getIdentity().getName();

        this.paragraphForEntity = CodeTemplate.newParagraph();

        setNativeAnnotation(dtoModel, this.paragraphForEntity);

        // must call 'getFieldInfo' first
        CodeTemplate.Paragraph pr = getFieldInfo(dtoModel);

        p
                .set("packageName", packageName)
                .set("enumPackageName", enumPackageName)
                .set("abstract", getAbstractInfo(dtoModel))
                .set("classAnnotationList", this.paragraphForEntity)
                .set("extendInfo", getExtendInfo(dtoModel))
                .set("implementInfo", "implements java.io.Serializable")
                .set("className", classNm);


        p.set("fieldList", pr);

        String qualifiedClassName = packageName + "." + classNm;
        String code = javaClassTmpl.toString(p);

        this.writeSrcCode(this.publisher, dtoModel, qualifiedClassName, code);
    }


    private CodeTemplate.Paragraph getFieldInfo(DataModelComponent entity) {
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();

        loadFieldInfo(entity, p);
        return p;
    }


    private void loadFieldInfo(DataModelComponent entity, CodeTemplate.Paragraph p) {

        boolean isEntity = false;
        if (entity instanceof Entity) {
            isEntity = true;
        }

        ListMap<String, Field> fields = entity.getAllFieldListMap();
        List<Field> fieldList = fields.getList();

        for (Field f : fieldList) {
            if (!f.hasAnnotation("id") && !f.hasAnnotation("search")
                    && entity.getItems().findByName(f.getName())==null)
                continue;


            if (isEntity) {
                if (!f.getTypeInfo().isBaseType())
                    continue;
            }


            setFieldDesc(f, p);

            setNativeAnnotation(f, p);

            String defaultValDefined = getDefaultValueSetup(f);

            String searchCondition = f.getAnnotationValue("search");
            String fieldNm = f.getName();
            if("between".equals(searchCondition)) {
                p.add("%s %s %s%s;", "private", f.getTypeInfo().getDefaultTypeName(), fieldNm+"From", defaultValDefined);
                p.add("%s %s %s%s;", "private", f.getTypeInfo().getDefaultTypeName(), fieldNm+"To", defaultValDefined);
            }
            else {
                p.add("%s %s %s%s;", "private", f.getTypeInfo().getDefaultTypeName(), fieldNm, defaultValDefined);
            }


            p.add("");

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

        String prefix = this.publishMode + ":";
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
