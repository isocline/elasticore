package io.elasticore.base.model.pub.mermaid;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SourceFileAccessFactory;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.RelationshipManager;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
import io.elasticore.base.util.CodeStringBuilder;
import io.elasticore.base.util.CodeTemplate;
import io.elasticore.base.util.HashUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;


public class MermaidCodePublisher implements CodePublisher {

    private ECoreModelContext ctx;

    private SourceFileAccessFactory sourceFileAccessFactory;


    private MermaidCodePublisher() {

    }

    @Override
    public void setSrcCodeWriterFactory(SourceFileAccessFactory sourceFileAccessFactory) {
        this.sourceFileAccessFactory = sourceFileAccessFactory;
    }

    @Override
    public SourceFileAccessFactory getSrcFileAccessFactory() {
        return this.sourceFileAccessFactory;
    }


    @Override
    public void errorOnPublish(ModelComponent modelComponent, Throwable e) {

        // TODO
        e.printStackTrace();
    }

    public static MermaidCodePublisher newInstance() {
        return new MermaidCodePublisher();
    }


    @Override
    public ECoreModelContext getECoreModelContext() {
        return this.ctx;
    }

    public void publish(ECoreModelContext ctx, ModelDomain domain) {
        this.ctx = ctx;
        ECoreModel model = domain.getModel();
        EntityModels entityModels = model.getEntityModels();
        ModelComponentItems<Entity> items = entityModels.getItems();

        CodeStringBuilder cb = new CodeStringBuilder("{", "}");
        cb.line("classDiagram").block("");
        // entity
        for (int i = 0; i < items.size(); i++) {
            Entity entity = items.get(i);
            String entityNm = entity.getIdentity().getName();

            makeClassInfoScript(entity, cb);
        }
        cb.end("");
        //System.err.println( cb.toString());


        String templatePath = "elasticore-template/mermaid/uml_html.tmpl";
        CodeTemplate umlHtmlTmpl = CodeTemplate.newInstance(templatePath);

        CodeTemplate.Parameters params = CodeTemplate.newParameters();
        params.set("umlTxt", cb.toString());

        String content = umlHtmlTmpl.toString(params);


        String fileName = "uml_" + ctx.getDomain().getName() + ".html";
        try (Writer writer = new FileWriter(new File(fileName))) {
            writer.write(content);

            writer.flush();
        } catch (Throwable e) {
            e.printStackTrace();

        }


    }


    private void makeUMLFile(String umlTxt) {


    }

    private void makeClassInfoScript(Entity entity, CodeStringBuilder cb) {
        StringBuilder sb = new StringBuilder();

        String classNm = entity.getIdentity().getName();

        cb.line("class %s", classNm).block();

        ModelComponentItems<Field> fields = entity.getItems();
        while (fields.hasNext()) {
            Field f = fields.next();

            String type = f.getTypeInfo().getDefaultTypeName();
            if(f.getTypeInfo().isList()) {

                type = f.getTypeInfo().getCoreItemType()+"[]";
            }
            String fieldName = f.getName();

            String label = f.getAnnotationValue("label","desc");
            if(label !=null && !label.isEmpty() ) {

                fieldName = fieldName+" //"+label;
            }


            cb.line("%s %s", type, fieldName);
        }

        cb.end();


        List<ModelRelationship> list = RelationshipManager.getInstance(entity.getIdentity().getDomainId()).findByFromName(classNm);

        for (ModelRelationship r : list) {
            if (r.getRelationType() == RelationType.MANY_TO_ONE)
                cb.line("%s --> \"0..*\" %s : %s", r.getFromName(), r.getToName(), r.getRelationName());

            else if (r.getRelationType() == RelationType.SUPER)
                cb.line("%s --> %s : %s", r.getFromName(), r.getToName(), "extend");
            else if (r.getRelationType() == RelationType.ROLLUP)
                cb.line("%s --> %s : %s", r.getFromName(), r.getToName(), r.getRelationType().getName());
            else if (r.getRelationType() == RelationType.EMBEDDED)
                cb.line("%s --> %s : %s", r.getFromName(), r.getToName(), "embedded");
        }
    }
}
