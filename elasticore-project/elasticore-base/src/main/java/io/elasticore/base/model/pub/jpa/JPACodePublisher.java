package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SourceFileAccessFactory;
import io.elasticore.base.model.ConstanParam;
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
import io.elasticore.base.util.HashUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class JPACodePublisher implements CodePublisher {

    protected ECoreModelContext ctx;

    private SourceFileAccessFactory sourceFileAccessFactory;

    protected JPACodePublisher() {

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
    public boolean deleteSource(ModelDomain domain, String rootDir) {
        String entityNs = domain.getModel().getNamespace(ConstanParam.KEYNAME_ENTITY);

        String dtoNs = domain.getModel().getNamespace(ConstanParam.KEYNAME_DTO);
        String enumNs = domain.getModel().getNamespace(ConstanParam.KEYNAME_ENUMERATION);
        String repoNs = domain.getModel().getNamespace(ConstanParam.KEYNAME_REPOSITORY);
        String svcNs = domain.getModel().getNamespace(ConstanParam.KEYNAME_SERVICE);
        String controlNs = domain.getModel().getNamespace(ConstanParam.KEYNAME_CONTROL);


        deleteFilesWithEcdStart( rootDir + convertToFilePath(entityNs) );
        deleteFilesWithEcdStart( rootDir + convertToFilePath(dtoNs) );
        deleteFilesWithEcdStart( rootDir + convertToFilePath(enumNs) );
        deleteFilesWithEcdStart( rootDir + convertToFilePath(repoNs) );
        deleteFilesWithEcdStart( rootDir + convertToFilePath(svcNs) );
        deleteFilesWithEcdStart( rootDir + convertToFilePath(controlNs) );

        if(ConsoleLog.countStoredLogkey("DELETE_OK")>0) {
            ConsoleLog.print("");
            ConsoleLog.print("[INFO] delete files:");
            ConsoleLog.print("--------------------------------------");
            ConsoleLog.printStoredLog("DELETE_OK" , "  ");
        }



        if(ConsoleLog.countStoredLogkey("DELETE_FAIL")>0) {
            ConsoleLog.print("");
            ConsoleLog.print("[WARN] undelete files:");
            ConsoleLog.print("--------------------------------------");
            ConsoleLog.printStoredWarnLog("DELETE_FAIL" , "  ");
        }

        if(ConsoleLog.countStoredLogkey("DELETE_FAIL2")>0) {
            ConsoleLog.print("");
            ConsoleLog.print("[WARN] modified files:");
            ConsoleLog.print("--------------------------------------");
            ConsoleLog.printStoredWarnLog("DELETE_FAIL2" , "  ");
        }



        return true;
    }


    private static String convertToFilePath(String qualifiedClassName) {
        return "/"+qualifiedClassName.replace('.', '/');
    }


    public static void deleteFilesWithEcdStart(String directoryPath) {

        File directory = new File(directoryPath);

        if (!directory.isDirectory()) {
            return;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isFile()) {

                HashUtils.Response response = null;
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                    response = HashUtils.checkContentModified(reader);

                } catch (IOException e) {

                }

                if(response !=null) {
                    if(response.getStatus() == HashUtils.NO_MODIFIED) {
                        if (file.delete()) {
                            ConsoleLog.storeLog("DELETE_OK",directoryPath +" "+file.getName() );
                        }else{
                            ConsoleLog.storeLog("DELETE_FAIL",directoryPath +" "+file.getName() );
                        }
                    }else if(response.getStatus() == HashUtils.MODIFIED) {
                        ConsoleLog.storeLog("DELETE_FAIL2",directoryPath +" "+file.getName() );
                    }
                }

            }
        }
    }


    @Override
    public ECoreModelContext getECoreModelContext() {
        return this.ctx;
    }

    protected boolean printRuleCheckInfo(ECoreModelContext ctx, ModelDomain domain) {
        if(ConsoleLog.countStoredLogkey("FIELD_NM_ERR")>0) {
            ConsoleLog.print("");
            ConsoleLog.print("[WARN] field naming convention error:");
            ConsoleLog.print("--------------------------------------");
            ConsoleLog.printStoredWarnLog("FIELD_NM_ERR", "  ");
            ConsoleLog.print("");

            ConsoleLog.printWarn("ElatiCORE processing interrupted");

            return false;
        }

        return true;
    }

    public void publish(ECoreModelContext ctx, ModelDomain domain) {

        if(!printRuleCheckInfo(ctx, domain))
            return;

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


        printInfo(ctx, domain);
    }


    protected void printInfo(ECoreModelContext ctx, ModelDomain domain) {
        ConsoleLog.print("");
        ConsoleLog.print("######################################");
        ConsoleLog.print("#");
        ConsoleLog.print("# Domain: " + domain.getName());
        ConsoleLog.print("#");
        ConsoleLog.print("######################################");
        ConsoleLog.print("");

        ConsoleLog.print("[INFO] Code Templates:");
        ConsoleLog.print("--------------------------------------");
        ConsoleLog.printStoredInfoLog("TEMPLATE", "  ");
        ConsoleLog.print("");

        if (ConsoleLog.countStoredLogkey("NO_MODIFIED") > 0) {
            ConsoleLog.print("[INFO] No Changes Detected:");
            ConsoleLog.print("--------------------------------------");
            ConsoleLog.printStoredLog("NO_MODIFIED", "  ");
            ConsoleLog.print("");
        }

        if (ConsoleLog.countStoredLogkey("USER_MODIFIED") > 0) {
            ConsoleLog.print("[WARN] Externally Modified Files Detected:");
            ConsoleLog.print("--------------------------------------");
            ConsoleLog.printStoredWarnLog("USER_MODIFIED", "  ");
            ConsoleLog.print("");
        }

        if (ConsoleLog.countStoredLogkey("PUBLISH") > 0) {
            ConsoleLog.print("[INFO] New Changes:");
            ConsoleLog.print("--------------------------------------");
            ConsoleLog.printStoredInfoLog("PUBLISH", "  ");
            ConsoleLog.print("");
        }


        ConsoleLog.clear();
    }
}
