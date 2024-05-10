package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponentItems;
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

public class RepositoryHelperFilePublisher extends SrcFilePublisher {

    private CodeTemplate baseCodeTmpl;

    private CodePublisher publisher;


    private String packageName;
    private String entityPackageName;
    private String dtoPackageName;


    public RepositoryHelperFilePublisher(CodePublisher publisher) {
        this.publisher = publisher;

        String templatePath = this.publisher.getECoreModelContext().getDomain().getModel().getConfig("templateHelper.repository");
        if(templatePath==null)
            templatePath = "elasticore-template/repositoryHelper.tmpl";

        this.baseCodeTmpl = CodeTemplate.newInstance(templatePath);

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_REPOSITORY);
        this.entityPackageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);
        this.dtoPackageName = model.getNamespace(ConstanParam.KEYNAME_DTO);
    }

    public void publish(ModelDomain domain, RepositoryModels repositoryModels) {


        CodeTemplate.Parameters params = CodeTemplate.newParameters();

        String classNm = "RepositoryHelper";

        params
                .set("packageName", packageName)
                .set("entityPackageName", entityPackageName)
                .set("dtoPackageName", dtoPackageName)
                .set("className", classNm);





        CodeTemplate.Paragraph fieldP = CodeTemplate.newParagraph();
        CodeTemplate.Paragraph methodP = CodeTemplate.newParagraph();
        loadRepositoryInfo(repositoryModels,fieldP,methodP );

        params.set("repositoryList", fieldP);
        params.set("methodList", methodP);

        String code = baseCodeTmpl.toString(params);
        String qualifiedClassName = packageName + "." + classNm;

        this.writeSrcCode(this.publisher, null, qualifiedClassName, code);
    }



    private CodeTemplate.Paragraph getRepositoryInfo(RepositoryModels repositoryModels) {
        //private final ContactInfoRepository contactInfo;
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();
        ModelComponentItems<Repository> repoItems = repositoryModels.getItems();

        while (repoItems.hasNext()) {
            Repository repo = repoItems.next();

            String repoName = repo.getIdentity().getName();

            String fieldNm = StringUtils.uncapitalize(repoName);

            String code = String.format("private final %s %s;", repoName+"Repository", fieldNm);
            p.add(code);
            p.add("");



            ModelComponentItems<Method> methods = repo.getItems();
            while (methods.hasNext()) {
                Method method = methods.next();
                if(method.getQueryInfo()!=null && method.getQueryInfo().isOwnOutputDTO()) {
                    System.err.println( method.getName() + "<<< ");
                }
            }

        }

        return p;

    }

    private void loadRepositoryInfo(RepositoryModels repositoryModels, CodeTemplate.Paragraph fieldP, CodeTemplate.Paragraph methodP) {
        //private final ContactInfoRepository contactInfo;
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();
        ModelComponentItems<Repository> repoItems = repositoryModels.getItems();

        while (repoItems.hasNext()) {
            Repository repo = repoItems.next();

            String repoName = repo.getIdentity().getName();

            String fieldNm = StringUtils.uncapitalize(repoName);

            String code = String.format("private final %s %s;", repoName+"Repository", fieldNm);
            fieldP.add(code);
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
                if(method.getQueryInfo()!=null && method.getQueryInfo().isOwnOutputDTO()) {
                    if(!"List<Object[]>".equals(method.getReturnType()))
                        continue;

                    SqlQueryInfo queryInfo = method.getQueryInfo();
                    DataTransfer outputDTO = queryInfo.getDataTransfer();

                    String params = getParametersForMethod(method.getParams() ,true);

                    String outputClassNm = outputDTO.getIdentity().getName();
                    String methodName = method.getName();
                    String methodCode = String.format("public List<%s> %s(%s) {", outputClassNm,methodName ,params);
                    methodP.add(methodCode);

                    String fieldNames = getMappingFieldNames(outputDTO.getItems());
                    methodP.add("    String[] fieldNames = {"+fieldNames+"};");

                    params = getParametersForMethod(method.getParams() ,false);
                    methodCode = String.format("    List<Object[]> result = %s.%s(%s);", fieldNm, methodName,params);
                    methodP.add(methodCode);


                    methodCode = String.format("    return new ModelTransList<%s>(result, %s.class, fieldNames);", outputClassNm, outputClassNm);
                    methodP.add(methodCode);
                    methodP.add("}");
                    methodP.add("");
                }
            }

        }

    }

    private String getMappingFieldNames(ModelComponentItems<Field> params) {
        StringBuilder sb = new StringBuilder();
        if (params != null) {
            while(params.hasNext()) {
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
                if(hasType) {
                    sb.append(f.getTypeInfo().getDefaultTypeName()).append(" ");
                }
                sb.append(f.getName());
            }
        }

        return sb.toString();
    }

}
