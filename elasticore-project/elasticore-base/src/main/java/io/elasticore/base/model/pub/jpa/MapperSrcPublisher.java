package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ComponentType;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.AbstractDataModel;
import io.elasticore.base.model.core.ListMap;
import io.elasticore.base.model.core.RelationshipManager;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferModels;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
import io.elasticore.base.util.CodeStringBuilder;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.StringUtils;
import static io.elasticore.base.util.StringUtils.*;

import java.util.List;

public class MapperSrcPublisher extends SrcFilePublisher {

    private CodeTemplate baseCodeTmpl;
    private ECoreModel model;

    private CodePublisher publisher;


    private String className;
    private String packageName;
    private String entityPackageName;
    private String dtoPackageName;

    private RelationshipManager relationshipManager;


    public MapperSrcPublisher(CodePublisher publisher) {

        ModelDomain modelDomain =publisher.getECoreModelContext().getDomain();
        this.model = modelDomain.getModel();

        if (model.getEntityModels().getItems().size() == 0 || model.getDataTransferModels().getItems().size() == 0)
            return;

        this.relationshipManager = RelationshipManager.getInstance(modelDomain.getName());

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
        makeMapper(domain.getModel(), methodP);

        makeMapper(domain, methodP);

        params.set("methodList", methodP);

        String code = baseCodeTmpl.toString(params);
        String qualifiedClassName = packageName + "." + this.className;

        this.writeSrcCode(this.publisher, null, qualifiedClassName, code);
    }

    private void makeMapper(ModelDomain domain, CodeTemplate.Paragraph methodP) {
        List<ModelRelationship> list = RelationshipManager.getInstance(domain.getName())
                .findByType(RelationType.SEARCHABLE);

        for(ModelRelationship mrs: list) {
            String searchDtoNm = mrs.getFromName();
            String targetEntityNm = mrs.getToName();

            Entity  entity = domain.getModel().getEntityModels().findByName(targetEntityNm);
            DataTransfer dto = domain.getModel().getDataTransferModels().findByName(searchDtoNm);

            makeSpecificationCode(dto, entity ,methodP);
        }
    }

    private void makeSpecificationFieldCode(DataTransfer searchDto, CodeStringBuilder cb) {

        ListMap<String, Field> listMap = searchDto.getAllFieldListMap();

        for(Field f:listMap.getList()) {
            if(!f.hasAnnotation("search") && !f.hasAnnotation("s")) continue;

            String condition = f.getAnnotationValue("search" ,"s"); // =, eq, like, between, !=, neq

            String type = f.getTypeInfo().getDefaultTypeName();
            String fieldNm = f.getName();
            String capFidNm = StringUtils.capitalize(fieldNm);

            if(!"between".equals(condition) || "~".equals(condition)) {
                cb.line("%s %s = searchDTO.get%s();",type,fieldNm,capFidNm);
                cb.line("if(%s != null)",fieldNm);
                cb.block();
            }

            if("like".equals(condition) || "%%".equals(condition)) {
                String percent = quoteString("%");
                cb.line("sp = sp.and((r,q,c) -> c.like(r.get(%s),%s +%s+ %s));", quoteString(fieldNm),percent,fieldNm,percent);
            }

            else if("%-".equals(condition)) {
                String percent = quoteString("%");
                cb.line("sp = sp.and((r,q,c) -> c.like(r.get(%s),%s +%s));", quoteString(fieldNm),percent,fieldNm);
            }
            else if("-%".equals(condition)) {
                String percent = quoteString("%");
                cb.line("sp = sp.and((r,q,c) -> c.like(r.get(%s),%s+ %s));", quoteString(fieldNm),fieldNm,percent);
            }

            else if("between".equals(condition)|| "~".equals(condition)) {
                String fromNm = fieldNm+"From";
                String toNm = fieldNm+"To";
                cb.line("%s %s = searchDTO.get%s();",type,fromNm,capitalize(fromNm));
                cb.line("%s %s = searchDTO.get%s();",type,toNm,capitalize(toNm));

                cb.line("if(%s !=null && %s !=null)", fromNm, toNm);
                cb.block();
                cb.line("sp = sp.and((r,q,c) -> c.between(r.get(%s),%s,%s));", quoteString(fieldNm),fromNm,toNm);
                cb.end();

                cb.line("else if(%s !=null)", fromNm);
                cb.block();
                cb.line("sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get(%s),%s));", quoteString(fieldNm),fromNm);
                cb.end();

                cb.line("else if(%s !=null)", toNm);
                cb.block();
                cb.line("sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get(%s),%s));", quoteString(fieldNm),toNm);

                cb.end();
            }
            else {
                // equals
                cb.line("sp = sp.and((r,q,c) -> c.equal(r.get(%s),%s));", quoteString(fieldNm),fieldNm);
            }

            if(!"between".equals(condition))
                cb.end();
        }


    }

    private void makeSpecificationCode(DataTransfer searchDto, Entity targetEntity, CodeTemplate.Paragraph p) {

        String dtoNm = searchDto.getIdentity().getName();
        String entityNm = targetEntity.getIdentity().getName();

        String mainMethodNm = "toSpec";

        CodeStringBuilder cb = new CodeStringBuilder("{","}");
        cb.line("public static Specification<%s> %s(%s searchDTO)", entityNm, mainMethodNm, dtoNm);
        cb.block();
        cb.line("return %s(searchDTO, Specification.where(null));",mainMethodNm);
        cb.end();

        p.add(cb);
        p.add("");


        CodeStringBuilder cb2 = new CodeStringBuilder("{","}");
        cb2.line("public static Specification<%s> %s(%s searchDTO, Specification<%s> sp)", entityNm, mainMethodNm, dtoNm, entityNm);
        cb2.block();

        makeSpecificationFieldCode(searchDto, cb2);

        cb2.line("return sp;");
        cb2.end();

        p.add(cb2);
        p.add("");
    }

    private void makeMapper(ECoreModel model, CodeTemplate.Paragraph methodP) {
        EntityModels entityModels = model.getEntityModels();

        ModelComponentItems<Entity> items = entityModels.getItems();

        for (int i = 0; i < items.size(); i++) {
            Entity entity = items.get(i);

            String entityNm = entity.getIdentity().getName();

            List<ModelRelationship> list = RelationshipManager.getInstance(entity.getIdentity().getDomainId()).findByFromName(entityNm);

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

            List<ModelRelationship> list = RelationshipManager.getInstance(entity.getIdentity().getDomainId()).findByFromName(entityNm);

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

        this.relationshipManager.addRelationship(ModelRelationship.create(fromModel.getIdentity().getName()
                        , this.className ,RelationType.MAPPER)
                );

        CodeStringBuilder cb = new CodeStringBuilder("{", "}");
        cb.line("public static void mapping(%s from, %s to)", fromModel.getIdentity().getName(), toModel.getIdentity().getName()).block();
        cb.line("if(from ==null || to ==null) return;");

        ListMap<String, Field> fromListMap = fromModel.getAllFieldListMap();
        ListMap<String, Field> toListMap = toModel.getAllFieldListMap();

        List<Field> list = toListMap.getList();

        for (Field f : list) {
            if (f.hasAnnotation("disable")) continue;

            if(isEntityReturnType(f, model)) continue;

            String fieldName = f.getName();

            Field fromField = fromListMap.get(fieldName);


            String fldNm = StringUtils.capitalize(fieldName);

            String targetEntityNm = f.getAnnotationValue("reference", "ref");
            if(targetEntityNm!=null && toModel.getIdentity().getComponentType()==ComponentType.DTO) {
                String targetFieldNm = null;
                String[] targetItems = targetEntityNm.split("\\.");
                if(targetItems.length==2){
                    targetEntityNm = targetItems[0];
                    targetFieldNm = targetItems[1];
                }

                Field entityGetField = findFieldByTypeName(fromModel, targetEntityNm);
                if(entityGetField!=null) {
                    String nm = StringUtils.capitalize(entityGetField.getName());

                    if(targetFieldNm==null) {
                        cb.line("to.set%s(toDTO(from.get%s()));", fldNm, nm);
                    }else {

                        cb.line("if(from.get%s()!=null)", nm);
                        cb.line("    to.set%s(from.get%s().get%s());"
                                ,fldNm,nm, StringUtils.capitalize(targetFieldNm));
                    }
                }
            }
            else {
                if (fromField == null) continue;
                if (fromField.hasAnnotation("disable")) continue;

                cb.line("to.set%s(from.get%s());", fldNm, fldNm);
            }




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
