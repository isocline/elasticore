package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.*;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferModels;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.entity.PkField;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
import io.elasticore.base.model.repo.Method;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.model.repo.RepositoryModels;
import io.elasticore.base.model.repo.SqlQueryInfo;
import io.elasticore.base.util.CodeStringBuilder;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.StringUtils;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapperSrcPublisher extends SrcFilePublisher {

    private CodeTemplate baseCodeTmpl;

    private CodePublisher publisher;


    private String className;
    private String packageName;
    private String entityPackageName;
    private String dtoPackageName;


    public MapperSrcPublisher(CodePublisher publisher) {
        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();

        if (model.getEntityModels().getItems().size() == 0 || model.getDataTransferModels().getItems().size() == 0)
            return;

        this.packageName = model.getNamespace(ConstanParam.KEYNAME_MAPPER);
        if (this.packageName == null) {
            this.packageName = model.getNamespace(ConstanParam.KEYNAME_DTO);
        } else {

        }
        this.dtoPackageName = model.getNamespace(ConstanParam.KEYNAME_DTO);
        this.entityPackageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);

        this.publisher = publisher;

        String templatePath = this.publisher.getECoreModelContext().getDomain().getModel().getConfig("template.mapper");
        if (templatePath == null)
            templatePath = "elasticore-template/mapper.tmpl";
        else
            templatePath = "resource://"+templatePath;

        this.baseCodeTmpl = CodeTemplate.newInstance(templatePath);

        System.err.println(this.baseCodeTmpl);

    }

    public void publish(ModelDomain domain) {

        CodeTemplate.Parameters params = CodeTemplate.newParameters();

        String name = StringUtils.snakeToCamel(domain.getName(), "-");

        this.className = StringUtils.capitalize(name) + "Mapper";

        params
                .set("packageName", packageName)
                .set("entityPackageName", entityPackageName)
                .set("dtoPackageName", dtoPackageName)
                .set("className", this.className);


        CodeTemplate.Paragraph methodP = CodeTemplate.newParagraph();
        test(domain.getModel(), methodP);

        params.set("methodList", methodP);

        String code = baseCodeTmpl.toString(params);
        String qualifiedClassName = packageName + "." + this.className;

        this.writeSrcCode(this.publisher, null, qualifiedClassName, code);
    }


    private void test(ECoreModel model, CodeTemplate.Paragraph methodP) {


        EntityModels entityModels = model.getEntityModels();

        ModelComponentItems<Entity> items = entityModels.getItems();

        for (int i = 0; i < items.size(); i++) {
            Entity entity = items.get(i);

            String entityNm = entity.getIdentity().getName();

            List<ModelRelationship> list = RelationshipManager.getInstance(entity.getIdentity().getDomainId()).getRelationshipsForFromObj(entityNm);

            for (ModelRelationship r : list) {
                if (r.getRelationType() == RelationType.TEMPLATE) {
                    makeMappingCode(model, r.getFromName(), r.getToName(), methodP);

                } else if (r.getRelationType() == RelationType.TEMPLATE_TO) {
                    makeMappingCode(model, r.getFromName(), r.getToName(), methodP);
                }

            }

        }
        DataTransferModels dataTransferModels = model.getDataTransferModels();
        ModelComponentItems<DataTransfer> items2 = dataTransferModels.getItems();


        for (int i = 0; i < items2.size(); i++) {
            DataTransfer entity = items2.get(i);

            String entityNm = entity.getIdentity().getName();

            List<ModelRelationship> list = RelationshipManager.getInstance(entity.getIdentity().getDomainId()).getRelationshipsForFromObj(entityNm);

            for (ModelRelationship r : list) {
                if (r.getRelationType() == RelationType.TEMPLATE) {
                    makeMappingCode(model, r.getFromName(), r.getToName(), methodP);
                } else if (r.getRelationType() == RelationType.TEMPLATE_TO) {
                    makeMappingCode(model, r.getFromName(), r.getToName(), methodP);
                }

            }

        }

    }


    private void makeMappingCode(ECoreModel model, String fromName, String toName, CodeTemplate.Paragraph methodP) {

        AbstractDataModel fromModel = getDataModel(model, fromName);
        AbstractDataModel toModel = getDataModel(model, toName);

        if (fromModel == null || toModel == null || fromModel.getIdentity().getComponentType() == toModel.getIdentity().getComponentType())
            return;

        boolean isEntityTarget = (toModel.getIdentity().getComponentType()== ComponentType.ENTITY);
        String toMethodName = "toDTO";
        if(isEntityTarget)
            toMethodName = "toEntity";

        CodeStringBuilder cb = new CodeStringBuilder("{", "}");
        cb.line("public static void mapping(%s from, %s to)", fromModel.getIdentity().getName(), toModel.getIdentity().getName()).block();


        ListMap<String, Field> fromListMap = fromModel.getAllFieldListMap();
        ListMap<String, Field> toListMap = toModel.getAllFieldListMap();

        List<Field> list = toListMap.getList();

        for (Field f : list) {
            if (f.hasAnnotation("disable")) continue;

            String fieldName = f.getName();

            Field fromField = fromListMap.get(fieldName);
            if (fromField == null) continue;
            if (fromField.hasAnnotation("disable")) continue;

            String fldNm = StringUtils.capitalize(fieldName);

            cb.line("to.set%s(from.get%s());", fldNm, fldNm);
        }


        cb.end();

        methodP.add(cb);
        methodP.add("");


        CodeStringBuilder cb2 = new CodeStringBuilder("{", "}");
        String toClassNm = toModel.getIdentity().getName();
        String fromClassNm = fromModel.getIdentity().getName();
        cb2.line("public static %s %s(%s from)", toClassNm, toMethodName, fromClassNm).block();

        cb2.line("%s to = new %s();", toClassNm, toClassNm);
        cb2.line("mapping(from, to);");
        cb2.line("return to;");
        cb2.end();

        methodP.add(cb2);
        methodP.add("");


        /**
         * for (LoanCar from : fromList) {
         *             LoanCarDTO to = mapping(from);
         *             toList.add(to);
         *         }
         *         return toList;
         */

        CodeStringBuilder cb3 = new CodeStringBuilder("{", "}");
        cb3.line("public static List<%s> to%sList(List<%s> fromList)", toClassNm, toClassNm, fromClassNm).block();

        //return fromList.stream().map(LoanCarMapper::toLoanCarDTO).collect(Collectors.toList());
        cb3.line("if(fromList==null) return null;");
        cb3.line("return fromList.stream().map(%s::%s).collect(Collectors.toList());", this.className, toMethodName);

        /*
        cb3.line("List<%s> toList = new java.util.ArrayList<>();",toClassNm);
        cb3.line("for (%s from : fromList)",fromClassNm);
        cb3.block();
        cb3.line("%s to = to%s(from);", toClassNm,toClassNm);
        cb3.line("toList.add(to);");
        cb3.end();
        cb3.line("return toList;");

         */
        cb3.end();

        methodP.add(cb3);
        methodP.add("");

        //BiFunction<LoanCar, LoanCarDTO, LoanCarDTO> modifier
        CodeStringBuilder cb4 = new CodeStringBuilder("{", "}");
        cb4.line("public static List<%s> to%sList(List<%s> fromList, BiFunction<%s, %s, %s> modifier)"
                        , toClassNm, toClassNm, fromClassNm
                        , fromClassNm, toClassNm, toClassNm)
                .block();

        cb4.line("if(fromList==null) return null;");
        cb4.line("return fromList.stream()").block("");
        cb4.line(".map(from -> ").block();
        cb4.line("%s to = %s(from);", toClassNm, toMethodName);
        cb4.line("return modifier.apply(from, to);").end();
        cb4.line(")");

        cb4.line(".filter(Objects::nonNull)");
        cb4.line(".collect(Collectors.toList());");
        cb4.end("");

        cb4.end();
        methodP.add(cb4);
        methodP.add("");

        /**
         *  return fromList.stream()
         *                        .map(from -> {
         *                            LoanCarDTO to = toLoanCarDTO(from);
         *                            return modifier.apply(from, to);
         *                        })
         *                        .filter(Objects::nonNull) // Filter out null values
         *                        .collect(Collectors.toList());
         */


        //methodP.add( fromModel.getIdentity().getComponentType() +" ==>> "+ toModel.getIdentity().getComponentType());
        //methodP.add( fromModel.getIdentity().getName() +" ==>> "+ toModel.getIdentity().getName());


    }

    private AbstractDataModel getDataModel(ECoreModel model, String name) {
        AbstractDataModel dataModel = model.getEntityModels().findByName(name);
        if (dataModel == null)
            dataModel = model.getDataTransferModels().findByName(name);


        return dataModel;
    }


}
