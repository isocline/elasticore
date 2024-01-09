package io.elasticore.base.model.pub;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.exeption.ProcessException;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.pub.jpa.EntityCodePublisher;

public class JPACodePublisher implements CodePublisher {

    private JPACodePublisher() {

    }

    public static JPACodePublisher newInstance() {
        return new JPACodePublisher();
    }

    @Override
    public boolean publish(ECoreModelContext ctx) throws ProcessException {

        String[] domainNames = ctx.getDomanNames();

        for(String domainName:domainNames) {
            ModelDomain domain = ctx.getDomain(domainName);

            publish(domain);
        }

        return false;
    }


    private void publish(ModelDomain domain) {

        ECoreModel model = domain.getModel();

        EntityModels entityModels = model.getEntityModels();

        ModelComponentItems<Entity> items = entityModels.getItems();

        EntityCodePublisher entityCodePublisher = new EntityCodePublisher();
        for(int i=0;i<items.size();i++) {
            Entity entity = items.get(i);

            String name = entity.getIdentity().getName();

            entityCodePublisher.publish(entity);



        }


    }
}
