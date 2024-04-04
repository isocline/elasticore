package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.entity.PkField;
import io.elasticore.base.model.pub.JPACodePublisher;
import io.elasticore.base.model.repo.Method;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.StringUtils;

public class RepositoryFilePublisher extends SrcFilePublisher {

    private CodeTemplate baseCodeTmpl;

    private JPACodePublisher publisher;


    private String packageName;
    private String entityPackageName;


    public RepositoryFilePublisher(JPACodePublisher publisher) {
        this.publisher = publisher;

        String templatePath = this.publisher.getECoreModelContext().getDomain().getModel().getConfig("template.repository");
        if(templatePath==null)
            templatePath = "elasticore-template/repository.tmpl";

        this.baseCodeTmpl = CodeTemplate.newInstance(templatePath);

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_REPOSITORY);
        this.entityPackageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);


    }

    public void publish(ModelDomain domain, Repository repo) {

        String targetModelName = repo.getIdentity().getName();
        Entity entity = domain.getModel().getEntityModels().findByName(targetModelName);
        PkField pkField = entity.findPkField(domain);
        if (entity == null
                //|| entity.getMetaInfo().hasMetaAnnotation("abstract")
                || pkField == null) {
            return;
        }

        CodeTemplate.Parameters params = CodeTemplate.newParameters();


        String classNm = targetModelName + "Repository";

        params
                .set("packageName", packageName)
                .set("entityPackageName", entityPackageName)

                .set("className", classNm)
                .set("targetModel", targetModelName)
                .set("identity", "Long")

                .set("extendInfo", "")
                .set("implementInfo", "implements Serializable")
                .set("identity", pkField.getType());


        CodeTemplate.Paragraph p = getMethodInfo(repo);
        params.set("methodList", p);

        String code = baseCodeTmpl.toString(params);
        String qualifiedClassName = packageName + "." + classNm;

        this.writeSrcCode(this.publisher, repo, qualifiedClassName, code);
    }


    /*
     @Query(
            value = "SELECT *, earth_distance(ll_to_earth(:latitude, :longitude), ll_to_earth(urfs.car_port_mapy, urfs.car_port_mapx)) as map_point FROM used_rent_for_sale urfs WHERE urfs.month1age21is_use AND urfs.for_sale_status = 'SALEING' AND urfs.car_maker_type = :carMakerType ORDER BY map_point ASC, month1age21price ASC",
            nativeQuery = true
    )
     */
    private CodeTemplate.Paragraph getMethodInfo(Repository repo) {
        ModelComponentItems<Method> methodItems = repo.getItems();

        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();
        while (methodItems.hasNext()) {
            Method method = methodItems.next();

            //if(method.getName()==null) continue;
            if (!method.isEnable()) continue;


            boolean isNeedParamAnnotation = false;
            if (method.getQuery() != null && method.isNeedQueryAnnotation()) {
                String query = StringUtils.splitByDoubleQuotation(method.getQuery());
                String queryAnnotation = String.format("@Query(value=%s,nativeQuery=%s)", query, method.isNative());
                p.add(queryAnnotation);
                isNeedParamAnnotation = true;
            }

            String code = String.format("%s %s(%s);"
                    , method.getReturnType(), method.getName(), getParametersForMethod(method.getParams(), isNeedParamAnnotation));
            p.add(code);
            p.add("");
        }
        return p;
    }

    private String getParametersForMethod(Items<Field> params, boolean isNeedParamAnnotation) {
        StringBuilder sb = new StringBuilder();
        if (params != null) {
            for (Field f : params.getItemList()) {
                if (sb.length() > 0)
                    sb.append(" ,");
                // @Param("username")
                if (isNeedParamAnnotation)
                    sb.append("@Param(\"").append(f.getName()).append("\") ");
                sb.append(f.getTypeInfo().getDefaultTypeName()).append(" ").append(f.getName());
            }
        }

        return sb.toString();
    }

}
