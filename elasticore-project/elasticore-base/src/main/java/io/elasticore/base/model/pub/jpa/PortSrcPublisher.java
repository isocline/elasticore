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

import java.util.Properties;

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
            String id = portService.getIdentity().getDomainId() +"."+ portService.getIdentity().getName();
            String url = portService.getMeta().getMetaAnnotationValue("url");
            if("http".equals(type)) {
                p.add("@ExternalService(protocol=\"http\", id=%s ,url=%s)",StringUtils.quoteString(id),StringUtils.quoteString(url));
            }
            else if("dbms".equals(type)) {
                MetaInfo meta = portService.getMeta();
                String datasource = meta.getMetaAnnotationValue("datasource");
                String sqlSource = meta.getMetaAnnotationValue("sqlsource");
                if(datasource!=null) {
                    p.add("@DbmsService(id=%s ,datasource=%s ,sqlSource=%s)"
                            ,StringUtils.quoteString(id)
                            ,StringUtils.quoteString(datasource), StringUtils.quoteString(sqlSource));
                }
                else {
                    p.add("@DbmsService(id=%s ,sqlSource=%s)"
                            ,StringUtils.quoteString(id),StringUtils.quoteString(sqlSource));
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
                        Properties properties = metaAnnotation.getProperties();
                        String url = properties.get("url").toString();
                        String httpMethod = properties.get("method").toString().toUpperCase();
                        String contentType = properties.get("contenttype").toString();
                        if(contentType!=null && !contentType.isEmpty()) {
                            methodP.add("@HttpEndpoint(url=%s, method=%s ,contentType=%s)"
                                    , StringUtils.quoteString(url), StringUtils.quoteString(httpMethod), StringUtils.quoteString(contentType));
                        }else{
                            methodP.add("@HttpEndpoint(url=%s, method=%s)", StringUtils.quoteString(url), StringUtils.quoteString(httpMethod));
                        }


                    } catch (NullPointerException npe) {
                    }
                }
            }

            methodP.add("%s %s(%s);", returnType, methodName, paramInfos);
            methodP.add("");
        }
    }


}
