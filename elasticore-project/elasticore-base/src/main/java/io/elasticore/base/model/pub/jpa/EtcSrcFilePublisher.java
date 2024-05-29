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
public class EtcSrcFilePublisher extends SrcFilePublisher {

    private CodePublisher publisher;


    /**
     * Initializes a new instance of the EntitySrcFilePublisher class.
     * Sets up the template path and packages based on the provided publisher.
     *
     * @param publisher The JPACodePublisher instance used for publishing.
     */
    public EtcSrcFilePublisher(CodePublisher publisher) {
        super(publisher);

        this.publisher = publisher;
    }

    /**
     * Generates and publishes Java source code for the specified entity.
     * This method orchestrates the creation of entity classes based on
     * entity definition and annotations.
     *
     * @param domain The model domain to which the entity belongs.
     */
    public void publish(ModelDomain domain) {
        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        String packageNm = model.getNamespace(ConstanParam.KEYNAME_DTO);

        publishSrcFile(packageNm, "PageableObject", "template.etc.PageableObject", "java_PageableObject.tmpl");
        publishSrcFile(packageNm, "SortableObject", "template.etc.SortableObject" ,"java_SortableObject.tmpl");
    }

    private void publishSrcFile(String packageName, String className, String configPath, String defaultFileNm) {
        String templatePath = this.publisher.getECoreModelContext().getDomain().getModel().getConfig(configPath);
        if (templatePath == null)
            templatePath = "elasticore-template/"+defaultFileNm;
        else
            templatePath = "resource://" + templatePath;

        CodeTemplate javaClassTmpl = CodeTemplate.newInstance(templatePath);
        CodeTemplate.Parameters p = CodeTemplate.newParameters();
        p.set("packageName", packageName);

        String qualifiedClassName = packageName + "."+className;
        String code = javaClassTmpl.toString(p);

        this.writeSrcCode(this.publisher, null, qualifiedClassName, code);
    }


}
