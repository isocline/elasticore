package io.elasticore.test;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.ModelLoader;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.core.BaseECoreModelContext;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.loader.FileBasedModelLoader;
import io.elasticore.base.model.pub.JPACodePublisher;

public class Test {


    @org.junit.jupiter.api.Test
    public void test() {


        ModelLoader loader = FileBasedModelLoader.newInstance();
        ECoreModelContext ctx = BaseECoreModelContext.getContext(loader);
        ModelDomain defaultDomain = ctx.getDomain();

        ECoreModel model = defaultDomain.getModel();


        CodePublisher publisher = JPACodePublisher.newInstance();

        ctx.publish(publisher);

    }
}
