package io.elasticore.base.model.pub;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SrcCodeWriterFactory;
import io.elasticore.base.exeption.ProcessException;
import io.elasticore.base.model.pub.jpa.JPACodePublisher;
import io.elasticore.base.model.pub.mermaid.MermaidCodePublisher;
import io.elasticore.base.model.pub.px.PxCodePublihser;

import java.util.ArrayList;
import java.util.List;

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

            List<CodePublisher> codePublishers = getCodePublisher(domain);
            for(CodePublisher publisher : codePublishers) {
                publisher.publish(ctx ,domain);
            }


        }

        return false;
    }

    private List<CodePublisher> getCodePublisher(ModelDomain domain) {
        List<CodePublisher> codePublishers = new ArrayList<>();


        String mode = domain.getModel().getConfig("mode" ,"jpa");

        String[] items = mode.split(",");

        for(String eachMode : items) {
            CodePublisher codePublisher = null;

            if("jpa".equals(eachMode)) {
                codePublisher = JPACodePublisher.newInstance();

            }
            else if("px".equals(eachMode)) {
                codePublisher = new PxCodePublihser();
            }
            else if("uml".equals(eachMode)) {
                codePublisher = MermaidCodePublisher.newInstance();
            }

            if(codePublisher!=null) {
                codePublisher.setSrcCodeWriterFactory(this.srcCodeWriterFactory);

                codePublishers.add(codePublisher);
            }
        }


        return codePublishers;
    }

    public void publish(ECoreModelContext ctx, ModelDomain domain) {

    }
}
