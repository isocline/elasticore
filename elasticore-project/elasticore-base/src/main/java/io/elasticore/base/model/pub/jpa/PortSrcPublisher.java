package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.MetaInfo;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.Annotation;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityAnnotation;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.port.Port;
import io.elasticore.base.model.repo.Method;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.StringUtils;

public class PortSrcPublisher extends SrcFilePublisher {

    private CodeTemplate baseCodeTmpl;

    private CodePublisher publisher;

    private String packageName;

    private String entityPackageName;

    private String dtoPackageName;

    protected ECoreModel eCoreModel;


    public PortSrcPublisher(CodePublisher publisher) {
        super(publisher);

        this.publisher = publisher;

        this.eCoreModel = this.publisher.getECoreModelContext().getDomain().getModel();

        String templatePath = eCoreModel.getConfig("template.port");
        if (templatePath == null)
            templatePath = "elasticore-template/port.tmpl";
        else
            templatePath = "resource://" + templatePath;

        this.baseCodeTmpl = CodeTemplate.newInstance(templatePath);

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();
        this.packageName = model.getNamespace(ConstanParam.KEYNAME_PORT);

        if (eCoreModel.getEntityModels().getItems().size() > 0)
            this.entityPackageName = model.getNamespace(ConstanParam.KEYNAME_ENTITY);

        if (eCoreModel.getDataTransferModels().getItems().size() > 0)
            this.dtoPackageName = model.getNamespace(ConstanParam.KEYNAME_DTO);

    }

    public void publish(ModelDomain domain) {
        ModelComponentItems<Port> portServices = this.eCoreModel.getPortModels().getItems();
        while (portServices.hasNext()) {
            Port port = portServices.next();
            publish(domain, port);
        }
    }


    private CodeTemplate.Paragraph getSvcAnnotation(Port portService) {
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();

        String type = portService.getMeta().getMetaAnnotationValue("type");
        if(type!=null) {
            type = type.toLowerCase();
            if("http".equals(type)) {
                p.add("@ExternalService(type=\"http\", id=%s)",StringUtils.quoteString(portService.getIdentity().getName()));
            }
            else if("dbms".equals(type)) {
                String datasource = portService.getMeta().getMetaAnnotationValue("datasource");
                if(datasource!=null) {
                    p.add("@DbmsService(datasource=%s)",StringUtils.quoteString(datasource));
                }
                else {
                    p.add("@DbmsService");
                }

            }
        }

        return p;
    }

    public void publish(ModelDomain domain, Port portService) {

        if (this.packageName == null) return;

        CodeTemplate.Parameters params = CodeTemplate.newParameters();

        CodeTemplate.Paragraph classAnnotationList = getSvcAnnotation(portService);

        String classNm = portService.getIdentity().getName();

        params
                .set("packageName", packageName)
                .set("j2eePkgName", getPersistentPackageName(domain))
                .set("entityPackageName", entityPackageName)
                .set("dtoPackageName", dtoPackageName)
                .set("classAnnotationList", classAnnotationList)
                .set("className", classNm);


        CodeTemplate.Paragraph fieldP = CodeTemplate.newParagraph();
        CodeTemplate.Paragraph methodP = CodeTemplate.newParagraph();
        loadRepositoryInfo(portService, fieldP, methodP);

        if (fieldP.size() == 0 && methodP.size() == 0)
            return;

        params.set("repositoryList", fieldP);
        params.set("methodList", methodP);

        String code = baseCodeTmpl.toString(params);
        String qualifiedClassName = packageName + "." + classNm;

        this.writeSrcCode(this.publisher, null, qualifiedClassName, code);
    }

    protected String geParamInfos(Items<Field> fieldItems) {
        StringBuilder sb = new StringBuilder();
        for (Field f : fieldItems.getItemList()) {
            if (sb.length() > 0)
                sb.append(",");

            sb.append(f.getTypeInfo()
                            .getBaseTypeName())
                    .append(" ")
                    .append(f.getName());
        }

        return sb.toString();

    }


    private void loadRepositoryInfo(Port portService, CodeTemplate.Paragraph fieldP, CodeTemplate.Paragraph methodP) {
        //private final ContactInfoRepository contactInfo;
        CodeTemplate.Paragraph p = CodeTemplate.newParagraph();
        ModelComponentItems<Method> methods = portService.getItems();

        ECoreModel model = publisher.getECoreModelContext().getDomain().getModel();

        while (methods.hasNext()) {
            Method method = methods.next();

            String returnType = method.getReturnType();
            Items<Field> fieldItems = method.getParams();

            String paramInfos = geParamInfos(fieldItems);

            String methodName = method.getIdentity().getName();

            MetaInfo metaInfo = method.getMetaInfo();


            if (metaInfo != null) {
                Annotation metaAnnotation = metaInfo.getMetaAnnotation("httpendpoint");

                if (metaAnnotation != null) {
                    try {
                        String url = metaAnnotation.getProperties().get("url").toString();
                        String httpMethod = metaAnnotation.getProperties().get("method").toString().toUpperCase();

                        methodP.add("@HttpEndpoint(url=%s, method=%s)", StringUtils.quoteString(url), StringUtils.quoteString(httpMethod));
                    } catch (NullPointerException npe) {
                    }
                }
            }

            methodP.add("%s %s(%s);", returnType, methodName, paramInfos);
            methodP.add("");
        }
    }


}
