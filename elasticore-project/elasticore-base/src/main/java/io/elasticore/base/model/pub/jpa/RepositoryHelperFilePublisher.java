package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.BaseModelDomain;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.entity.PkField;
import io.elasticore.base.model.repo.Method;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.model.repo.RepositoryModels;
import io.elasticore.base.model.repo.SqlQueryInfo;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepositoryHelperFilePublisher extends SrcFilePublisher {

    private CodeTemplate baseCodeTmpl;

    private CodePublisher publisher;


    private String packageName;
    private String entityPackageName;
    private String dtoPackageName;


    public RepositoryHelperFilePublisher(CodePublisher publisher) {
        this.publisher = publisher;

        String templatePath = this.publisher.getECoreModelContext().getDomain().getModel().getConfig("template.templateHelper");
        if (templatePath == null)
            templatePath = "elasticore-template/repositoryHelper.tmpl";
        else
            templatePath = "resource://"+templatePath;

        this.baseCodeTmpl = CodeTemplate.newInstance(templatePath);

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_REPOSITORY);
        this.entityPackageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);
        this.dtoPackageName = model.getNamespace(ConstanParam.KEYNAME_DTO);

    }

    public void publish(ModelDomain domain, RepositoryModels repositoryModels) {


        CodeTemplate.Parameters params = CodeTemplate.newParameters();

        String name = StringUtils.snakeToCamel(domain.getName(),"-");

        String classNm = StringUtils.capitalize(name)+"RepositoryHelper";

        params
                .set("packageName", packageName)
                .set("j2eePkgName",getPersistentPackageName(domain))
                .set("entityPackageName", entityPackageName)
                .set("dtoPackageName", dtoPackageName)
                .set("className", classNm);


        CodeTemplate.Paragraph fieldP = CodeTemplate.newParagraph();
        CodeTemplate.Paragraph methodP = CodeTemplate.newParagraph();
        loadRepositoryInfo(repositoryModels, fieldP, methodP);

        params.set("repositoryList", fieldP);
        params.set("methodList", methodP);

        String code = baseCodeTmpl.toString(params);
        String qualifiedClassName = packageName + "." + classNm;

        this.writeSrcCode(this.publisher, null, qualifiedClassName, code);
    }

    private boolean isEnable(Repository repo) {
        String targetModelName = repo.getIdentity().getName();
        ModelDomain modelDomain =BaseModelDomain.getModelDomain(repo.getIdentity().getDomainId());

        Entity entity = modelDomain.getModel().getEntityModels().findByName(targetModelName);
        PkField pkField = entity.findPkField(modelDomain);
        if (entity == null
                //|| entity.getMetaInfo().hasMetaAnnotation("abstract")
                || pkField == null) {
            return false;
        }

        return true;
    }

    private void loadRepositoryInfo(RepositoryModels repositoryModels, CodeTemplate.Paragraph fieldP, CodeTemplate.Paragraph methodP) {
        //private final ContactInfoRepository contactInfo;
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();
        ModelComponentItems<Repository> repoItems = repositoryModels.getItems();

        while (repoItems.hasNext()) {
            Repository repo = repoItems.next();
            if(!isEnable(repo))
                continue;

            String repoName = repo.getIdentity().getName();

            String fieldNm = StringUtils.uncapitalize(repoName);


            fieldP.add("private final %s %s;", repoName + "Repository", fieldNm);
            fieldP.add("");

            /*
            public List<ContractGroupDTO2> selectCnctCustList(Integer grpSeq) {


        String[] fieldNames = { "carSeq","contrNm","custNm"};

        List<Object[]> result = contactInfo.selectCnctCustList(grpSeq);
        return new ModelTransList<ContractGroupDTO2>(result, ContractGroupDTO2.class, fieldNames);
    }
             */


            ModelComponentItems<Method> methods = repo.getItems();
            while (methods.hasNext()) {
                Method method = methods.next();

                if (method.getQueryInfo() != null) {
                    if (method.getQueryInfo().isDynamicQuery()) {
                        makeDynamicQueryMethod(method, methodP);
                    } else if (method.getQueryInfo().isOwnOutputDTO()) {
                        if (!"List<Object[]>".equals(method.getReturnType()))
                            continue;

                        makeTransformMethod(method, methodP, fieldNm);
                    }
                }
            }

        }
    }


    /**
     * public List<ContractGroup> findAllConnectionGroups(String groupName, Integer grpSeq) {
     * StringBuilder sb = new StringBuilder("SELECT * FROM T_CNCT_GRP");
     * sb.append( " WHERE 1=1 ");
     * <p>
     * if(groupName!=null && !groupName.isEmpty())
     * sb.append(" AND group_name like CONCAT('%', :groupName, '%') ");
     * <p>
     * if(grpSeq!=null)
     * sb.append(" AND grp_seq = :grpSeq");
     * <p>
     * Query query = entityManager.createNativeQuery(sb.toString(), ContractGroup.class);
     * <p>
     * if(groupName!=null && !groupName.isEmpty())
     * query.setParameter("groupName" , groupName);
     * <p>
     * if(grpSeq!=null)
     * query.setParameter("grpSeq" , grpSeq);
     * <p>
     * <p>
     * return query.getResultList();
     * }
     */
    private void makeDynamicQueryMethod(Method method, CodeTemplate.Paragraph p) {

        SqlQueryInfo queryInfo = method.getQueryInfo();
        if (queryInfo == null) return;
        String sqlQuery = queryInfo.getSqlTxt();

        if (sqlQuery == null || sqlQuery.indexOf("/* if:") < 0) return;


        DataTransfer outputDTO = queryInfo.getDataTransfer();

        String params = getParametersForMethod(method.getParams(), true);
        String fieldNames = null;

        String outputClassNm = extractContentBetweenBrackets(method.getReturnType());
        if (queryInfo.isNativeQuery()) {
            if (queryInfo.isOwnOutputDTO()) {

                DataTransfer outputDTO2 = queryInfo.getDataTransfer();
                outputClassNm = outputDTO2.getIdentity().getName();

                fieldNames = getMappingFieldNames(outputDTO2.getItems());
            } else
                outputClassNm = "Object[]";

        }
        String methodName = method.getName();

        boolean isPageable = method.isPageable() && !queryInfo.isNativeQuery();
        if(isPageable)
            p.add("public Page<%s> %s(%s ,Pageable pageable) {", outputClassNm, methodName, params);
        else
            p.add("public List<%s> %s(%s) {", outputClassNm, methodName, params);

        if (fieldNames != null)
            p.add("    String[] fieldNames = {%s};", fieldNames);

        p.add("  StringBuilder sb = new StringBuilder();");


        String[] lines = sqlQuery.split("\n");
        Pattern pattern = Pattern.compile("(/\\* if:(\\w+) \\*/)$");

        List<String> varList = new ArrayList<>();

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                // Extracting variable name
                String variableName = matcher.group(2);
                // Removing the pattern from the line
                String modifiedLine = line.replaceAll("/\\* if:\\w+ \\*/", "").trim();


                varList.add(variableName);

                p.add("    if(" + variableName + "!=null)");
                p.add("      sb.append(\"" + modifiedLine + "\");");
            } else {
                p.add("    sb.append(\"" + line + "\");");
            }
        }

        if (queryInfo.isNativeQuery()) {
            p.add("    Query query = entityManager.createNativeQuery(sb.toString());");
        } else {
            p.add("    Query query = entityManager.createQuery(sb.toString(), %s.class);", outputClassNm);
        }


        for (String var : varList) {
            p.add("    if(" + var + "!=null)");
            p.add("      query.setParameter(\"" + var + "\" , " + var + ");");
        }

        if(isPageable) {
            p.add("    int totalRows = query.getResultList().size();");
            p.add("    query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());");
            p.add("    query.setMaxResults(pageable.getPageSize());");
            p.add("    return new PageImpl<%s>(query.getResultList(), pageable, totalRows);",outputClassNm);
        }else {
            if (fieldNames != null) {
                p.add("    return new ModelTransList<%s>(query.getResultList(), %s.class, fieldNames);", outputClassNm, outputClassNm);
            } else {
                p.add("    return query.getResultList();");
            }
        }



        p.add("}");
        p.add("");


    }

    public static String extractContentBetweenBrackets(String input) {
        Pattern pattern = Pattern.compile("<([^>]+)>");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }


    private void makeTransformMethod(Method method, CodeTemplate.Paragraph methodP, String repoName) {
        SqlQueryInfo queryInfo = method.getQueryInfo();
        DataTransfer outputDTO = queryInfo.getDataTransfer();

        String params = getParametersForMethod(method.getParams(), true);

        String outputClassNm = outputDTO.getIdentity().getName();
        String methodName = method.getName();

        methodP.add("public List<%s> %s(%s) {", outputClassNm, methodName, params);

        String fieldNames = getMappingFieldNames(outputDTO.getItems());
        methodP.add("    String[] fieldNames = {%s};", fieldNames);

        params = getParametersForMethod(method.getParams(), false);
        methodP.add("    List<Object[]> result = %s.%s(%s);", repoName, methodName, params);
        methodP.add("    return new ModelTransList<%s>(result, %s.class, fieldNames);", outputClassNm, outputClassNm);
        methodP.add("}");
        methodP.add("");
    }

    private String getMappingFieldNames(ModelComponentItems<Field> params) {
        StringBuilder sb = new StringBuilder();
        if (params != null) {
            while (params.hasNext()) {
                Field f = params.next();
                if (sb.length() > 0)
                    sb.append(" ,");
                sb.append("\"").append(f.getName()).append("\"");
            }
        }
        return sb.toString();
    }


    private String getParametersForMethod(Items<Field> params, boolean hasType) {
        StringBuilder sb = new StringBuilder();
        if (params != null) {
            for (Field f : params.getItemList()) {
                if (sb.length() > 0)
                    sb.append(" ,");
                // @Param("username")'
                if (hasType) {
                    sb.append(f.getTypeInfo().getDefaultTypeName()).append(" ");
                }
                sb.append(f.getName());
            }
        }

        return sb.toString();
    }

}
