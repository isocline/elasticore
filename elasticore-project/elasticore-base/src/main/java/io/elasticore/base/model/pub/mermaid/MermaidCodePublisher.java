package io.elasticore.base.model.pub.mermaid;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SrcCodeWriterFactory;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.core.RelationshipManager;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferModels;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.enums.EnumModels;
import io.elasticore.base.model.pub.jpa.*;
import io.elasticore.base.model.relation.ModelRelationship;
import io.elasticore.base.model.relation.RelationType;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.model.repo.RepositoryModels;
import io.elasticore.base.util.CodeStringBuilder;

import java.util.List;


public class MermaidCodePublisher implements CodePublisher {

    private ECoreModelContext ctx;

    private SrcCodeWriterFactory srcCodeWriterFactory;

    private MermaidCodePublisher() {

    }

    @Override
    public void setSrcCodeWriterFactory(SrcCodeWriterFactory srcCodeWriterFactory) {
        this.srcCodeWriterFactory = srcCodeWriterFactory;
    }

    @Override
    public SrcCodeWriterFactory getSrcCodeWriterFactory() {
        return this.srcCodeWriterFactory;
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

        System.out.println("UML >> ---- >>  &&&&&&&&&&&&&&&&&&");

        this.ctx = ctx;

        ECoreModel model = domain.getModel();

        EntityModels entityModels = model.getEntityModels();

        ModelComponentItems<Entity> items = entityModels.getItems();

        CodeStringBuilder cb = new CodeStringBuilder("{","}");
        cb.line("classDiagram").block("");
        // entity
        for (int i = 0; i < items.size(); i++) {
            Entity entity = items.get(i);

            String entityNm = entity.getIdentity().getName();

            makeClassInfoScript(entity,cb);



        }





        cb.end("");

        System.err.println( cb.toString());
    }


    private void makeClassInfoScript(Entity entity, CodeStringBuilder cb) {
        StringBuilder sb = new StringBuilder();

        String classNm = entity.getIdentity().getName();

        cb.line("class %s", classNm).block();

        ModelComponentItems<Field> fields = entity.getItems();
        while(fields.hasNext()) {
            Field f = fields.next();

            cb.line("%s %s", f.getTypeInfo().getInitTypeInfo(), f.getName());
        }

        cb.end();


        List<ModelRelationship> list = RelationshipManager.getInstance(entity.getIdentity().getDomainId()).getRelationshipsForFromObj(classNm);

        for(ModelRelationship r : list) {
            if(r.getRelationType()== RelationType.MANY_TO_ONE)
                cb.line("%s --> \"0..*\" %s : %s", r.getFromName(), r.getToName(), r.getRelationName());

            else if(r.getRelationType()== RelationType.SUPER)
                cb.line("%s --> %s : %s", r.getFromName(), r.getToName(), "extend");
            else if(r.getRelationType()== RelationType.ROLLUP)
                cb.line("%s --> %s : %s", r.getFromName(), r.getToName(), r.getRelationType().getName());
            else if(r.getRelationType()== RelationType.EMBEDDED)
                cb.line("%s --> %s : %s", r.getFromName(), r.getToName(),"embedded");
        }
    }
}
