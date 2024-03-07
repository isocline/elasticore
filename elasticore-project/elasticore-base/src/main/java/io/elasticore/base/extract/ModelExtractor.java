package io.elasticore.base.extract;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.ModelLoader;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.core.BaseECoreModelContext;
import io.elasticore.base.model.loader.FileBasedModelLoader;
import io.elasticore.base.model.pub.JPACodePublisher;

public class ModelExtractor {

    public void extract() {
        FileBasedModelLoader loader = FileBasedModelLoader.newInstance();
        loader.setTemplateFileDirPath("C:\\workspace\\Isocline\\elasticore\\elasticore-project\\elasicore-template\\src\\main\\resources\\blueprint\\api-auth-b2c");


        ECoreModelContext ctx = BaseECoreModelContext.getContext(loader);
        ModelDomain defaultDomain = ctx.getDomain();

        ECoreModel model = defaultDomain.getModel();


        JPACodePublisher publisher = JPACodePublisher.newInstance();
        publisher.setDestBaseDirPath("C:\\workspace\\Isocline\\elasticore\\elasticore-project\\elasicore-template\\src\\main\\java");

        ctx.publish(publisher);
    }

    public static void main(String[] args) {

        try {
            ModelExtractor extractor = new ModelExtractor();
            extractor.extract();
        }catch (Throwable e) {
            e.printStackTrace();
        }

    }

}
