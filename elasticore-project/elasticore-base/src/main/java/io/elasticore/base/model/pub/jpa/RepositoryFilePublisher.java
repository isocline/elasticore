package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.pub.JPACodePublisher;
import io.elasticore.base.model.repo.Method;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.util.CodeTemplate;

import java.io.File;

public class RepositoryFilePublisher extends FilePublisher {

    private final static CodeTemplate baseCodeTmpl = CodeTemplate.newInstance()
            .line("package ${packageName};")
            .line()
            .line("import org.springframework.data.jpa.repository.JpaRepository;")
            .line("import org.springframework.data.jpa.repository.Query;")

            .line("import java.util.*;")
            .line("import ${entityPackageName}.*;")


            .line("import ${import};", true)

            .line()
            .line("/**")
            .line(" * <pre>${description}</pre>")
            .line(" * hash:${hashCode}")
            .line(" */")

            .line("${classAnotations}", true)

            .line("public interface ${className} extends JpaRepository<${targetModel},${identity}> {")
            .line()
            .line("    ${methodList}", true)
            .line()
            .line("}");

    private JPACodePublisher publisher;

    private String entityBaseDir;
    private String packageName;
    private String entityPackageName;


    public RepositoryFilePublisher(JPACodePublisher publisher) {
        this.publisher = publisher;

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_REPOSITORY);
        this.entityPackageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);

        entityBaseDir = publisher.getDestBaseDirPath() + "/"+getPath(this.packageName);
        File f = new File(entityBaseDir);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public void publish(ModelDomain domain, Repository repo) {


        CodeTemplate.Parameters params = CodeTemplate.newParameters();

        String targetModelName=repo.getIdentity().getName();

        String classNm = targetModelName+"Repository";

        params
                .set("packageName", packageName)
                .set("entityPackageName", entityPackageName)

                .set("className", classNm)
                .set("targetModel", targetModelName)
                .set("identity", "Long")

                .set("extendInfo", "")
                .set("implementInfo", "implements Serializable");

        Entity entity = domain.getModel().getEntityModels().findByNamne(targetModelName);

        if(entity!=null) {

            params.set("identity",entity.getPkField().getType());

        }



        CodeTemplate.Paragraph p = getMethodInfo(repo);
        params.set("methodList", p);

        String code = baseCodeTmpl.toString(params);

        String filePaht = this.entityBaseDir + "/" + classNm+ ".java";
        writeFile(filePaht, code);
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

            if(method.getQuery()!=null) {
                String queryAnnotation = String.format("@Query(value=\"%s\",nativeQuery=%s)",method.getQuery(), method.isNative());
                p.add(queryAnnotation);
            }

            String code = String.format("%s %s(%s);"
                    , method.getReturnType(), method.getName(), getParametersForMethod(method.getParams()));
            p.add(code);
            p.add("");
        }
        return p;
    }

    private String getParametersForMethod(Items<Field> params) {
        StringBuilder sb = new StringBuilder();
        for (Field f : params.getItemList()) {
            if (sb.length() > 0)
                sb.append(" ,");
            sb.append(f.getTypeInfo().getDefaultTypeName()).append(" ").append(f.getName());
        }
        return sb.toString();
    }

}
