package io.elasticore.base.model.pub;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SrcCodeWriterFactory;
import io.elasticore.base.exeption.ProcessException;
import io.elasticore.base.model.pub.jpa.JPACodePublisher;
import io.elasticore.base.model.pub.px.PxCodePublihser;

public class CodePublishManager {

    private SrcCodeWriterFactory srcCodeWriterFactory;

    private CodePublishManager() {
    }

    private static class Holder {
        private static final CodePublishManager INSTANCE = new CodePublishManager();
    }

    public static CodePublishManager getInstance() {
        return Holder.INSTANCE;
    }

    public void setSrcCodeWriterFactory(SrcCodeWriterFactory srcCodeWriterFactory) {
        this.srcCodeWriterFactory = srcCodeWriterFactory;
    }


    public boolean publish(ECoreModelContext ctx) throws ProcessException {

        String[] domainNames = ctx.getDomanNames();

        for (String domainName : domainNames) {
            ModelDomain domain = ctx.getDomain(domainName);

            CodePublisher codePublisher = getCodePublisher(domain);
            codePublisher.publish(ctx ,domain);

        }

        return false;
    }

    private CodePublisher getCodePublisher(ModelDomain domain) {
        CodePublisher codePublisher = null;
        String mode = domain.getModel().getConfig("mode" ,"jpa");
        if("jpa".equals(mode)) {
            codePublisher = JPACodePublisher.newInstance();

        }
        else if("px".equals(mode)) {
            codePublisher = new PxCodePublihser();
        }

        if(codePublisher!=null)
            codePublisher.setSrcCodeWriterFactory(this.srcCodeWriterFactory);
        return codePublisher;
    }

    public void publish(ECoreModelContext ctx, ModelDomain domain) {

    }
}
