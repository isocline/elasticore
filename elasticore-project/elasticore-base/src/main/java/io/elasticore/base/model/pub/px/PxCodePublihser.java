package io.elasticore.base.model.pub.px;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SourceFileAccessFactory;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.enums.EnumModels;
import io.elasticore.base.model.pub.jpa.EnumFilePublisher;
import io.elasticore.base.model.pub.jpa.RepositoryFilePublisher;
import io.elasticore.base.model.repo.Port;
import io.elasticore.base.model.repo.RepositoryModels;
import io.elasticore.base.util.ConsoleLog;

public class PxCodePublihser implements CodePublisher {

    private ECoreModelContext ctx;

    private SourceFileAccessFactory sourceFileAccessFactory;

    @Override
    public void setSrcCodeWriterFactory(SourceFileAccessFactory sourceFileAccessFactory) {

        this.sourceFileAccessFactory = sourceFileAccessFactory;
    }

    @Override
    public SourceFileAccessFactory getSrcFileAccessFactory() {
        return this.sourceFileAccessFactory;
    }

    @Override
    public ECoreModelContext getECoreModelContext() {
        return this.ctx;
    }


    @Override
    public boolean deleteSource(ModelDomain domain, String rootDir) {
        return false;
    }

    @Override
    public void publish(ECoreModelContext ctx, ModelDomain domain) {
        this.ctx = ctx;

        ECoreModel model = domain.getModel();

        EntityModels entityModels = model.getEntityModels();

        ModelComponentItems<Entity> items = entityModels.getItems();


        DtoSrcFilePublisher dtoSrcFilePublisher = new DtoSrcFilePublisher(this);
        dtoSrcFilePublisher.publish(domain);


        EnumFilePublisher enumCodePublisher = new EnumFilePublisher(this);
        EnumModels enumModels = model.getEnumModels();

        while (enumModels.getItems().hasNext()) {
            EnumModel enumModel = enumModels.getItems().next();

            enumCodePublisher.publish(domain, enumModel);
        }


        // Repository
        RepositoryFilePublisher repositoryCodePublisher = new RepositoryFilePublisher(this);
        RepositoryModels repositoryModels = model.getRepositoryModels();

        while (repositoryModels.getItems().hasNext()) {
            Port repoModel = repositoryModels.getItems().next();

            repositoryCodePublisher.publish(domain, repoModel);
        }

        ConsoleLog.print("");
        ConsoleLog.print("######################################");
        ConsoleLog.print("#");
        ConsoleLog.print("# Domain: "+domain.getName());
        ConsoleLog.print("#");
        ConsoleLog.print("######################################");
        ConsoleLog.print("");

        ConsoleLog.print("[INFO] Code Templates:");
        ConsoleLog.print("--------------------------------------");
        ConsoleLog.printStoredInfoLog("TEMPLATE", "  ");
        ConsoleLog.print("");


        if(ConsoleLog.countStoredLogkey("NO_MODIFIED") >0) {
            ConsoleLog.print("[INFO] No Changes Detected");
            ConsoleLog.print("--------------------------------------");
            ConsoleLog.printStoredLog("NO_MODIFIED", "  ");
            ConsoleLog.print("");
        }

        if(ConsoleLog.countStoredLogkey("USER_MODIFIED") >0) {
            ConsoleLog.print("[WARN] Externally Modified Files Detected:");
            ConsoleLog.print("--------------------------------------");
            ConsoleLog.printStoredWarnLog("USER_MODIFIED", "  ");
            ConsoleLog.print("");
        }


        if(ConsoleLog.countStoredLogkey("PUBLISH") >0) {
            ConsoleLog.print("[INFO] New Changes:");
            ConsoleLog.print("--------------------------------------");
            ConsoleLog.printStoredInfoLog("PUBLISH", "  ");
            ConsoleLog.print("");
        }


        ConsoleLog.clear();

    }

    @Override
    public void errorOnPublish(ModelComponent modelComponent, Throwable e) {

    }
}
