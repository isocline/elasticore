package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SourceFileAccessFactory;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.ModelComponent;
import io.elasticore.base.model.ModelComponentItems;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferModels;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.enums.EnumModels;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.model.repo.RepositoryModels;
import io.elasticore.base.util.ConsoleLog;


public class JPACodePublisher implements CodePublisher {

    private ECoreModelContext ctx;

    private SourceFileAccessFactory sourceFileAccessFactory;

    private JPACodePublisher() {

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

    public static JPACodePublisher newInstance() {
        return new JPACodePublisher();
    }


    @Override
    public ECoreModelContext getECoreModelContext() {
        return this.ctx;
    }

    public void publish(ECoreModelContext ctx, ModelDomain domain) {
        //private void publish(ModelDomain domain) {
        this.ctx = ctx;

        ECoreModel model = domain.getModel();

        EntityModels entityModels = model.getEntityModels();

        ModelComponentItems<Entity> items = entityModels.getItems();

        EntitySrcFilePublisher entityCodePublisher = new EntitySrcFilePublisher(this);
        for (int i = 0; i < items.size(); i++) {
            Entity entity = items.get(i);

            String name = entity.getIdentity().getName();

            entityCodePublisher.publish(domain, entity);
        }


        EnumFilePublisher enumCodePublisher = new EnumFilePublisher(this);
        EnumModels enumModels = model.getEnumModels();

        while (enumModels.getItems().hasNext()) {
            EnumModel enumModel = enumModels.getItems().next();

            enumCodePublisher.publish(domain, enumModel);
        }

        DtoSrcFilePublisher dtoSrcFilePublisher = new DtoSrcFilePublisher(this);
        DataTransferModels dataTransferModels = model.getDataTransferModels();
        ModelComponentItems<DataTransfer> dtoItems = dataTransferModels.getItems();
        for (int i = 0; i < dtoItems.size(); i++) {
            DataTransfer dto = dtoItems.get(i);
            dtoSrcFilePublisher.publish(domain, dto);
        }

        SearchDtoSrcFilePublisher searchDtoSrcFilePublisher = new SearchDtoSrcFilePublisher(this);
        for (int i = 0; i < dtoItems.size(); i++) {
            DataTransfer dto = dtoItems.get(i);
            searchDtoSrcFilePublisher.publish(domain, dto);
        }


        // Repository
        RepositoryFilePublisher repositoryCodePublisher = new RepositoryFilePublisher(this);
        RepositoryModels repositoryModels = model.getRepositoryModels();

        ModelComponentItems<Repository> items1 = repositoryModels.getItems();
        while (items1.hasNext()) {
            Repository repoModel = items1.next();
            repositoryCodePublisher.publish(domain, repoModel);
        }

        RepositoryHelperFilePublisher repositoryHelperFilePublisher = new RepositoryHelperFilePublisher(this);
        repositoryHelperFilePublisher.publish(domain, repositoryModels);


        // mapper
        MapperSrcPublisher mapperSrcPublisher = new MapperSrcPublisher(this);
        mapperSrcPublisher.publish(domain);


        // etc
        EtcSrcFilePublisher etcSrcFilePublisher = new EtcSrcFilePublisher(this);
        etcSrcFilePublisher.publish(domain);


        // for Service
        ServiceSrcPublisher svcPublisher = new ServiceSrcPublisher(this);
        svcPublisher.publish(domain);

        ControlSrcPublisher controlSrcPublisher = new ControlSrcPublisher(this);
        controlSrcPublisher.publish(domain);


        ConsoleLog.print("");
        ConsoleLog.print("# Domain: "+domain.getName());
        ConsoleLog.print("");

        ConsoleLog.print("[INFO] Code Templates:");
        ConsoleLog.print("--------------------------------------");
        ConsoleLog.printStoredInfoLog("TEMPLATE", "  ");
        ConsoleLog.print("");


        ConsoleLog.print("[INFO] Unmodified Changes:");
        ConsoleLog.print("--------------------------------------");
        ConsoleLog.printStoredLog("NO_MODIFIED", "  ");
        ConsoleLog.print("");

        ConsoleLog.print("[WARN] Externally Modified Files Detected:");
        ConsoleLog.print("--------------------------------------");
        ConsoleLog.printStoredWarnLog("USER_MODIFIED", "  ");
        ConsoleLog.print("");

        ConsoleLog.print("[INFO] New Changes:");
        ConsoleLog.print("--------------------------------------");
        ConsoleLog.printStoredInfoLog("PUBLISH", "  ");
        ConsoleLog.print("");

        ConsoleLog.clear();
    }
}
