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
import io.elasticore.base.model.dto.DataTransferAnnotation;
import io.elasticore.base.model.dto.DataTransferModels;
import io.elasticore.base.model.entity.EntityAnnotation;
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
        super(publisher);

        ModelDomain modelDomain = publisher.getECoreModelContext().getDomain();
        this.model = modelDomain.getModel();

        if (model.getEntityModels().getItems().size() == 0 || model.getDataTransferModels().getItems().size() == 0)
            return;

        this.relationshipManager = RelationshipManager.getInstance(modelDomain.getName());

        this.packageName = model.getNamespace(ConstanParam.KEYNAME_MAPPER);
        if (this.packageName == null) {
            if(model.getNamespace(ConstanParam.KEYNAME_ENTITY) !=null) {
                this.packageName = model.getNamespace(ConstanParam.KEYNAME_DTO);
            }

        } else {

        }
        this.dtoPackageName = model.getNamespace(ConstanParam.KEYNAME_DTO);
        this.entityPackageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);

        this.publisher = publisher;

        String templatePath = this.publisher.getECoreModelContext().getDomain().getModel().getConfig("template.mapper");
        if (templatePath == null)
            templatePath = "elasticore-template/mapper.tmpl";
        else
            templatePath = "resource://" + templatePath;

        this.baseCodeTmpl = CodeTemplate.newInstance(templatePath);

    }

    public void publish(ModelDomain domain) {
        if(this.packageName ==null) return;

        CodeTemplate.Parameters params = CodeTemplate.newParameters();

        String name = StringUtils.snakeToCamel(domain.getName(), "-");

        this.className = StringUtils.capitalize(name) + "Mapper";


        String enumPackageName =null;
        if(model.getEnumModels().getItems().size()>0)
            enumPackageName = model.getNamespace(ConstanParam.KEYNAME_ENUMERATION);

        params
                .set("packageName", packageName)
                .set("j2eePkgName",getPersistentPackageName(domain))
                .set("entityPackageName", entityPackageName)
                .set("dtoPackageName", dtoPackageName)
                .set("enumPackageName", enumPackageName)
                .set("className", this.className);


        CodeTemplate.Paragraph methodP = CodeTemplate.newParagraph();
        makeMapper(domain.getModel(), methodP);

        makeMapper(domain, methodP);

        params.set("methodList", methodP);

        String code = baseCodeTmpl.toString(params);
        String qualifiedClassName = packageName + "." + this.className;

        this.writeSrcCode(this.publisher, null, qualifiedClassName, code ,false);
    }

    private void makeMapper(ModelDomain domain, CodeTemplate.Paragraph methodP) {
        List<ModelRelationship> list = RelationshipManager.getInstance(domain.getName())
                .findByType(RelationType.SEARCHABLE);

        for (ModelRelationship mrs : list) {
            String searchDtoNm = mrs.getFromName();
            String targetEntityNm = mrs.getToName();

            Entity entity = domain.getModel().getEntityModels().findByName(targetEntityNm);
            DataTransfer dto = domain.getModel().getDataTransferModels().findByName(searchDtoNm);

            makeSpecificationCode(dto, entity, methodP);
        }
    }




    private void makeSpecificationFieldCode(DataTransfer searchDto, CodeStringBuilder cb, String entityNm) {

        ListMap<String, Field> listMap = searchDto.getAllFieldListMap();

        for (Field f : listMap.getList()) {
            //if (!f.hasAnnotation(EntityAnnotation.SEARCH)) continue;

            if(!f.hasAnnotation(EntityAnnotation.SEARCH))
                continue;

            if ("entity".equals(searchDto.getMetaInfo().getMetaAnnotationValue("type"))
                    && f.getTypeInfo().isList())
                continue;




            String type = f.getTypeInfo().getDefaultTypeName();
            String fieldNm = f.getName();


            String condition = f.getAnnotationValue(EntityAnnotation.SEARCH); // =, eq, like, between, !=, neq
            String[] conditionItems = splitConditionText(condition);

            String customerFieldName = null;
            if(condition==null) {
                //continue;
                condition = "auto";
            }
            else {
                condition = conditionItems[0];
                if(conditionItems.length>1) {
                    customerFieldName = conditionItems[1];
                    fieldNm = customerFieldName;
                }
            }

            String capFidNm = StringUtils.capitalize(fieldNm);

            String entityFieldNm = fieldNm;

            boolean isListfield = f.getTypeInfo().isList();
            boolean isListEntityField = isListfield;

            String childFieldName="";
            String refFieldNm = f.getAnnotationValue(EntityAnnotation.REFERENCE);
            if(refFieldNm!=null) {
                String[] fieldItems = refFieldNm.split("\\.");
                if(fieldItems.length==2) {
                    refFieldNm = fieldItems[0];
                    childFieldName = ".get(\""+fieldItems[1] +"\")";
                }

                entityFieldNm = refFieldNm;


                isListEntityField = false;
                try {
                    isListEntityField = listMap.get(refFieldNm).getTypeInfo().isList();
                }catch (NullPointerException e) {}
            }

            boolean isBetweenCondition =("between".equals(condition) ||  "~".equals(condition));



            boolean isJoinWithSingleData = false;
            String joinWithSingleDatafieldName = null;
            String joinWithSingleDataTypeName = null;

            if( isListfield) {
                if ("eq".equals(condition) || "=".equals(condition)) {

                    isJoinWithSingleData = true;

                    // check : SearchDtoSrcFilePublisher
                    joinWithSingleDatafieldName = fieldNm+"Item";
                    joinWithSingleDataTypeName = f.getTypeInfo().getCoreItemType();

                    cb.line("%s %s = searchDTO.get%s();", joinWithSingleDataTypeName
                            , joinWithSingleDatafieldName
                            , capFidNm+ "Item");
                    cb.line("if(hasValue(%s))", joinWithSingleDatafieldName);
                    cb.block();

                }

            }

            boolean isAutoCondition = false;

            if("auto".equals(condition)) {
                cb.line("sp=setSpec(sp, %s, searchDTO.get%s());", quoteString(entityFieldNm),capFidNm );
                isAutoCondition = true;
            }else if (!isJoinWithSingleData && !isBetweenCondition) {
                cb.line("%s %s = searchDTO.get%s();", type, fieldNm, capFidNm);
                cb.line("if(hasValue(%s))", fieldNm);
                cb.block();
            }

            if(isAutoCondition) {

            }
            else if (">=".equals(condition)) {
                cb.line("sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get(%s)%s,%s));", quoteString(entityFieldNm),childFieldName, fieldNm);
            }
            else if ("<=".equals(condition)) {
                cb.line("sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get(%s)%s,%s));", quoteString(entityFieldNm),childFieldName, fieldNm);
            }
            else if (">".equals(condition)) {
                cb.line("sp = sp.and((r,q,c) -> c.greaterThan(r.get(%s)%s,%s));", quoteString(entityFieldNm),childFieldName, fieldNm);
            }
            else if ("<".equals(condition)) {
                cb.line("sp = sp.and((r,q,c) -> c.lessThan(r.get(%s)%s,%s));", quoteString(entityFieldNm),childFieldName, fieldNm);
            }
            else if ("like".equals(condition) || "%%".equals(condition)) {
                String percent = quoteString("%");
                cb.line("sp = sp.and((r,q,c) -> c.like(r.get(%s)%s,%s +%s+ %s));", quoteString(entityFieldNm) ,childFieldName, percent, fieldNm, percent);
            } else if (condition.startsWith("%")) {
                String percent = quoteString("%");
                cb.line("sp = sp.and((r,q,c) -> c.like(r.get(%s)%s,%s +%s));", quoteString(entityFieldNm),childFieldName, percent, fieldNm);
            } else if (condition.endsWith("%")) {
                String percent = quoteString("%");
                cb.line("sp = sp.and((r,q,c) -> c.like(r.get(%s)%s,%s+ %s));", quoteString(entityFieldNm),childFieldName, fieldNm, percent);
            } else if ("in".equals(condition)) {
                if(isListEntityField) {
                    cb.line("sp = sp.and((root, query, criteriaBuilder) -> {");
                    cb.line("    Join<%s, %s> join = root.join(%s);",entityNm
                            , f.getTypeInfo().getCoreItemType()
                            , quoteString(entityFieldNm)
                    );
                    cb.line("    return join.in(%s);",fieldNm);
                    cb.line("});");

                }else{
                    cb.line("sp = sp.and((r, q, c) -> r.get(%s).in(%s));",quoteString(entityFieldNm),fieldNm);
                    //cb.line("sp = sp.and((r,q,c) -> c.in(r.get(%s)%s,%s));", quoteString(entityFieldNm),childFieldName, fieldNm);
                }


            } else if ("between".equals(condition) || "~".equals(condition)) {
                String fromNm = fieldNm + "From";
                String toNm = fieldNm + "To";

                String fromFd = "searchDTO.get"+capitalize(fromNm);
                String toFd = "searchDTO.get"+capitalize(fromNm);
                cb.line("%s %s = searchDTO.get%s();", type, fromNm, capitalize(fromNm));
                cb.line("%s %s = searchDTO.get%s();", type, toNm, capitalize(toNm));

                boolean isLocalDate = false;
                if("java.time.LocalDate".equals(type)) {
                    isLocalDate = true;

                }

                cb.line("if(hasValue(%s) && hasValue(%s))", fromNm, toNm);
                cb.block();
                if(isLocalDate) {
                    cb.line("%s %s_2 = %s.plusDays(1);",  type, toNm, toNm);
                    cb.line("sp = sp.and((r,q,c) -> c.between(r.get(%s)%s,%s,%s));", quoteString(entityFieldNm),childFieldName, fromNm, toNm+"_2");
                }else {
                    cb.line("sp = sp.and((r,q,c) -> c.between(r.get(%s)%s,%s,%s));", quoteString(entityFieldNm),childFieldName, fromNm, toNm);
                }

                cb.end();

                cb.line("else if(hasValue(%s))", fromNm);
                cb.block();
                cb.line("sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get(%s)%s,%s));", quoteString(entityFieldNm),childFieldName, fromNm);
                cb.end();

                cb.line("else if(hasValue(%s))", toNm);
                cb.block();
                if(isLocalDate) {
                    cb.line("%s %s_2 = %s.plusDays(1);",  type, toNm, toNm);
                    cb.line("sp = sp.and((r,q,c) -> c.lessThan(r.get(%s)%s,%s));", quoteString(entityFieldNm),childFieldName, toNm+"_2");
                }else {
                    cb.line("sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get(%s)%s,%s));", quoteString(entityFieldNm),childFieldName, toNm);
                }


                cb.end();
            } else {
                // equals

                if( isJoinWithSingleData) {

                    cb.line("sp = sp.and((root, query, criteriaBuilder) -> {");
                    cb.line("  Join<%s, %s> join = root.join(%s);",entityNm
                            ,joinWithSingleDataTypeName
                            ,quoteString(entityFieldNm));
                    cb.line("  return criteriaBuilder.equal(join, %s);",joinWithSingleDatafieldName);
                    cb.line("});");

                }
                else {
                    cb.line("sp = sp.and((r,q,c) -> c.equal(r.get(%s)%s,%s));", quoteString(entityFieldNm),childFieldName, fieldNm);
                }

            }

            if (!isBetweenCondition && !isAutoCondition)
                cb.end();
        }


    }


    private void makeSpecificationCode(DataTransfer searchDto, Entity targetEntity, CodeTemplate.Paragraph p) {

        String dtoNm = searchDto.getIdentity().getName();
        //String entityNm = targetEntity.getIdentity().getName();
        String entityNm = targetEntityName(targetEntity);

        String mainMethodNm = "toSpec";

        CodeStringBuilder cb = new CodeStringBuilder("{", "}");
        cb.line("public static Specification<%s> %s(%s searchDTO)", entityNm, mainMethodNm, dtoNm);
        cb.block();
        cb.line("return %s(searchDTO, Specification.where(null));", mainMethodNm);
        cb.end();

        p.add(cb);
        p.add("");


        CodeStringBuilder cb2 = new CodeStringBuilder("{", "}");
        cb2.line("public static Specification<%s> %s(%s searchDTO, Specification<%s> sp)", entityNm, mainMethodNm, dtoNm, entityNm);
        cb2.block();

        makeSpecificationFieldCode(searchDto, cb2 ,entityNm);

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
                RelationType rtype = r.getRelationType();


                if (rtype == RelationType.TEMPLATE
                        || rtype == RelationType.TEMPLATE_TO
                        || rtype == RelationType.SEARCH_RESULT
                ) {
                    makeMappingCode(model, r.getFromName(), r.getToName(), methodP, rtype);
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
                RelationType relationType = r.getRelationType();
                if (relationType == RelationType.TEMPLATE
                        || relationType == RelationType.TEMPLATE_TO
                ) {
                    makeMappingCode(model, r.getFromName(), r.getToName(), methodP ,relationType);
                }

            }

        }

    }

    private boolean makeModelRefMappingCode(Field fieldWithRefInfo, AbstractDataModel fromModel, CodeStringBuilder cb, boolean isDtoSet) {

        String fieldName = fieldWithRefInfo.getName();
        String setFieldNm = StringUtils.capitalize(fieldName);

        String targetRefFieldNm = fieldWithRefInfo.getAnnotationValue(EntityAnnotation.REFERENCE);
        //if(targetRefFieldNm!=null && toModel.getIdentity().getComponentType()==ComponentType.DTO) {
        if (targetRefFieldNm != null) {
            String targetChildFieldNm = null;
            String[] targetItems = targetRefFieldNm.split("\\.");
            if (targetItems.length == 2) {
                targetRefFieldNm = targetItems[0];
                targetChildFieldNm = targetItems[1];
            }

            //boolean isDtoSet = fromModel.getIdentity().getComponentType()==ComponentType.ENTITY;

            //Field entityGetField = findFieldByTypeName(fromModel, targetRefFieldNm);
            Field entityGetField = (Field) fromModel.getAllFieldListMap().get(targetRefFieldNm);
            if (entityGetField != null) {
                String getFieldNm = StringUtils.capitalize(entityGetField.getName());

                if(!isDtoSet) {
                    String tmp = getFieldNm;
                    getFieldNm = setFieldNm;
                    setFieldNm = tmp;
                }

                if (targetChildFieldNm == null) {
                    if (entityGetField.getTypeInfo().isList()) {

                        String setTypeNm = fieldWithRefInfo.getTypeInfo().getTypeParameterName();
                        if(!isDtoSet) {
                            setTypeNm = entityGetField.getTypeInfo().getTypeParameterName();
                        }
                        setTypeNm = StringUtils.capitalize(setTypeNm);

                        // 20240820
                        cb.line("if(to.get%s() != null && from.get%s()!=null) {", setFieldNm ,getFieldNm);
                        cb.line("    to.get%s().clear();",setFieldNm);
                        cb.line("    to.get%s().addAll(to%sList(from.get%s()));", setFieldNm, setTypeNm, getFieldNm);
                        cb.line("} else {");
                        cb.line("    to.set%s(to%sList(from.get%s()));", setFieldNm, setTypeNm, getFieldNm);
                        cb.line("}");
                    } else {
                        if(isDtoSet)
                            cb.line("to.set%s(toDTO(from.get%s()));", setFieldNm, getFieldNm);
                        else
                            cb.line("to.set%s(toEntity(from.get%s()));", setFieldNm, getFieldNm);
                    }

                } else if(isDtoSet){

                    cb.line("if(hasValue(from.get%s()))", getFieldNm);
                    cb.line("    to.set%s(from.get%s().get%s());"
                            , setFieldNm, getFieldNm, StringUtils.capitalize(targetChildFieldNm));
                }
                else {
                    // check if targetChildFieldNm is pk?
                    Entity e = this.findEntityByField(entityGetField);
                    if(e!=null) {
                        if(e.getPkField().getName().equals(targetChildFieldNm)) {
                            String entityNm = e.getIdentity().getName();

                            cb.line("");
                            cb.line("");

                            cb.line("if(hasValue(from.get%s()))",getFieldNm);

                            cb.block("{");
                            cb.line("%s t = new %s();", entityNm,entityNm);
                            cb.line("t.set%s(from.get%s());"
                                    , StringUtils.capitalize(targetChildFieldNm)
                                    , getFieldNm
                            );
                            cb.line("to.set%s(t);", StringUtils.capitalize(targetRefFieldNm));
                            cb.end("}");

                        }
                    }

                }
            }

            return true;
        }

        return false;
    }


    private void makeMappingCode(ECoreModel model, String fromName, String toName, CodeTemplate.Paragraph methodP ,RelationType relationType) {

        AbstractDataModel fromModel = getDataModel(model, fromName);
        AbstractDataModel toModel = getDataModel(model, toName);

        if (fromModel == null || toModel == null || fromModel.getIdentity().getComponentType() == toModel.getIdentity().getComponentType())
            return;

        boolean isEntityTarget = (toModel.getIdentity().getComponentType() == ComponentType.ENTITY);
        String toMethodName = "toDTO";
        if (isEntityTarget)
            toMethodName = "toEntity";

        String fromClassNm = fromModel.getIdentity().getName();
        String toClassNm = toModel.getIdentity().getName();

        if(relationType == RelationType.CHILD.SEARCH_RESULT)
            toMethodName = "to"+toClassNm;

        this.relationshipManager.addRelationship(ModelRelationship.create(fromClassNm
                , this.className, RelationType.MAPPER)
        );

        CodeStringBuilder cb = new CodeStringBuilder("{", "}");
        cb.line("public static void mapping(%s from, %s to, boolean isSkipNull)", fromClassNm, toClassNm).block();
        cb.line("checkPermission(from, to);");
        cb.line("if(from ==null || to ==null) return;");

        ListMap<String, Field> fromListMap = fromModel.getAllFieldListMap();
        ListMap<String, Field> toListMap = toModel.getAllFieldListMap();

        List<Field> list = toListMap.getList();

        String parentName = null;
        for (Field f : list) {

            if(f.hasAnnotation(DataTransferAnnotation.META_SEARCHABLE_BYPASS)) {
                String typeName = f.getTypeInfo().getDefaultTypeName();
                if(! f.getTypeInfo().isBaseType()) {

                    String checkTypeName = typeName;

                    if(f.getTypeInfo().isList()) {
                        String coreType = f.getTypeInfo().getCoreItemType();
                        checkTypeName = coreType;
                    }

                    if( !this.isEnableInDTO(model, toModel.getMetaInfo(), checkTypeName ,toModel.getIdentity()))
                        continue;

                }
            }


            if (f.hasAnnotation(EntityAnnotation.META_DISABLE)) continue;

            if (isEntityReturnType(f, model)) continue;

            if(!isEntityTarget) {

                String fieldParentName = f.getParentMetaInfo().getMetaAnnotationValue("name");
                if(parentName !=null && fieldParentName!=null && !parentName.equals(fieldParentName)) {

                    if( f.getParentMetaInfo().hasMetaAnnotation("abstract")) {
                        if(!fromClassNm.equals(fieldParentName)) {
                            cb.line("if(isSkip(\"%s\",\"%s\")) return;", fromClassNm,fieldParentName);
                        }

                    }

                }
                parentName = fieldParentName;
            }

            String fieldName = f.getName();

            Field fromField = fromListMap.get(fieldName);

            String fldNm = StringUtils.capitalize(fieldName);

            if (!makeModelRefMappingCode(f, fromModel, cb ,true)) {

                String mappingfunc =null;
                String prefix = "";
                String postfix = "";
                if(!f.getTypeInfo().isBaseType()) {

                    String checkTypeName = f.getTypeInfo().getDefaultTypeName();
                    if(f.getTypeInfo().isList()) {
                        checkTypeName = f.getTypeInfo().getCoreItemType();
                    }

                    if( this.isDTOModel(this.publisher.getECoreModelContext().getDomain().getModel(), checkTypeName))
                    {
                        if(f.getTypeInfo().isList()) {
                            prefix = "to"+checkTypeName+"List(";
                            mappingfunc = "to"+checkTypeName+"List";
                        }else {
                            prefix = "toDTO(";
                            mappingfunc = "toDTO";
                        }

                        postfix = ")";
                    }
                    else if(! this.isEnumModel(this.publisher.getECoreModelContext().getDomain().getModel(), checkTypeName)) {
                        continue;
                    }
                }

                if (fromField == null) continue;
                if (fromField.hasAnnotation(DataTransferAnnotation.META_DISABLE)) continue;

                if("toDTO".equals(toMethodName))
                    if (fromField.hasAnnotation("password")) continue;


                if(fromField.hasAnnotation("forceupdate")) {
                    cb.line("to.set%s(%sfrom.get%s()%s);", fldNm,prefix, fldNm ,postfix);
                }else {
                    if(mappingfunc!=null)
                        cb.line("setVal(from.get%s(), to::set%s, isSkipNull, %s::%s);", fldNm, fldNm, this.className,mappingfunc);
                    else
                        cb.line("setVal(from.get%s(), to::set%s, isSkipNull);", fldNm, fldNm);

                    /*
                    cb.line("if(!isSkipNull || hasValue(from.get%s()))", fldNm);
                    cb.line("    to.set%s(%sfrom.get%s()%s);", fldNm, prefix, fldNm ,postfix);

                     */
                }
            }
        }

        List<Field> list2 = fromListMap.getList();

        for (Field f : list2) {
            makeModelRefMappingCode(f, toModel, cb ,false);
        }
        cb.end();

        methodP.add(cb);
        methodP.add("");

        CodeStringBuilder cb_sub = new CodeStringBuilder("{", "}");
        cb_sub.line("public static void mapping(%s from, %s to)", fromModel.getIdentity().getName(), toModel.getIdentity().getName()).block();
        cb_sub.line("mapping(from,to,false);");
        cb_sub.end();

        methodP.add(cb_sub);
        methodP.add("");


        CodeStringBuilder cb2 = new CodeStringBuilder("{", "}");

        cb2.line("public static %s %s(%s from)", toClassNm, toMethodName, fromClassNm).block();
        cb2.line("if(from==null) return null;");
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
