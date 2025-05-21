package io.elasticore.base.model.loader;

import io.elasticore.base.ModelLoader;
import io.elasticore.base.model.ConstantParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.enums.EnumModels;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.elasticore.base.model.loader.javasrc.JavaSrcFileMainModelLoader;
import io.elasticore.base.model.loader.module.*;
import io.elasticore.base.model.loader.px.PxFileMainModelLoader;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.model.repo.RepositoryModels;
import io.elasticore.base.model.port.Port;
import io.elasticore.base.util.ConsoleLog;


import java.io.File;
import java.util.*;

//import static io.elasticore.base.model.core.BaseECoreModelContext.context;

public class FileBasedModelLoader implements ModelLoader, ConstantParam {

    private static FileBasedModelLoader instance;

    public static FileBasedModelLoader newInstance() {
        return new FileBasedModelLoader();
    }

    private String templateFileDirPath;


    public void setTemplateFileDirPath(String dirPath) {
        this.templateFileDirPath = dirPath;
    }

    List<String> domainNmList = null;

    @Override
    public List<String> getDomainNameList() {
        if(domainNmList ==null) {
            domainNmList = new ArrayList();
            File f = new File(templateFileDirPath);

            domainNmList.add(f.getName());
        }


        return domainNmList;
    }

    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());


    private FileSource getLoadData(File f) {

        if (!f.getName().endsWith(".yml") && !f.getName().endsWith(".yaml")) {
            return null;
        }

        FileSource fileSource = null;

        try {
            Map map = mapper.readValue(f, LinkedHashMap.class);
            return FileSource.builder().filepath(f.getAbsolutePath()).infoMap(map).build();
        } catch (Throwable e) {
            System.err.println(f.getAbsolutePath());
            e.printStackTrace();
            return FileSource.builder().filepath(f.getAbsolutePath()).error(e).build();
        }

    }

    private List<FileSource> fileSources = new ArrayList<>();

    private void loadData(File f, List<FileSource> fileSources) {

        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File chidFile : files) {
                loadData(chidFile, fileSources);
            }
        } else {

            FileSource fs = getLoadData(f);
            if (fs != null)
                fileSources.add(fs);
        }
    }


    private ECoreModel load2(ModelLoaderContext context) {


        File f = new File(templateFileDirPath);
        loadData(f, fileSources);

        io.elasticore.base.model.loader.ModelLoader<Entity> entityModelLoader = new EntityModelLoader();
        io.elasticore.base.model.loader.ModelLoader<EnumModel> enumerationModelLoader = new EnumerationModelLoader();
        io.elasticore.base.model.loader.ModelLoader<DataTransfer> dataTransferModelLoader = new DataTransferModelLoader();
        io.elasticore.base.model.loader.ModelLoader<Repository> repositoryModelLoader = new RepositoryModelLoader();

        io.elasticore.base.model.loader.ModelLoader<Port> portModelLoader = new PortModelLoader();


        for (FileSource fileSource : fileSources) {

            Map<String, Map> map = fileSource.getInfoMap();

            if (map == null) {
                continue;
            }

            entityModelLoader.loadModel(context, fileSource);
            enumerationModelLoader.loadModel(context, fileSource);
            dataTransferModelLoader.loadModel(context, fileSource);
            repositoryModelLoader.loadModel(context, fileSource);
            portModelLoader.loadModel(context, fileSource);
        }

        entityModelLoader.completeLoad();
        enumerationModelLoader.completeLoad();
        dataTransferModelLoader.completeLoad();
        repositoryModelLoader.completeLoad();

        EntityModels entityModels = EntityModels.create(context.getDomainId(), "entityGrp", null, context.getEntityItems());
        EnumModels enumModels = EnumModels.create(context.getDomainId(), "enumGroup", null, context.getEnumModelItems());
        RepositoryModels repositoryModels = RepositoryModels.create(context.getDomainId(), "repoGroup", null, context.getRepositoryItems());

        //EnumModels enumModels = getEnumModels();
        //EntityModels entityModels = getEntityModels();

        return ECoreModel.builder()
                .entityModels(entityModels)
                .enumModels(enumModels)
                .repositoryModels(repositoryModels)
                .configMap(context.getConfigMap())
                .namespaceMap(context.getNsMap())
                .internalDomainName(domainNmList.get(0))
                .build();
    }


    public ModelLoaderContext getModelLoaderContext(String domainName) {
        //test();
        List<FileSource> fileSources = new ArrayList<>();

        if (templateFileDirPath == null) {
            throw new IllegalArgumentException("templateFileDirPath is empty");
        }

        File envFile = new File(templateFileDirPath + "/env.yml");
        if (!envFile.exists())
            throw new IllegalArgumentException("env.yaml does not exist.");

        FileSource envFs = getLoadData(envFile);
        Map envMap = (Map) envFs.getInfoMap().get("env");
        if(envMap==null)
            envMap = (Map) envFs.getInfoMap().get("elasticore");

        if(envMap==null)
            throw new IllegalArgumentException("env.yaml does not have env information.");

        if(Boolean.FALSE.equals(envMap.get("enable"))) {
            ConsoleLog.printWarn("[WARN] Disabled domain: "+domainName);
            return null;
        }


        Map<String, Object> configMap = (Map) envMap.get("config");
        Map<String, String> nsMap = (Map) envMap.get("namespace");


        String userDefinedDomainName = (String) configMap.get("domainName");
        if(userDefinedDomainName!=null)
            domainName = userDefinedDomainName;

        ModelLoaderContext context = new ModelLoaderContext(domainName, configMap, nsMap,templateFileDirPath);

        return context;
    }
    @Override
    public ECoreModel load(ModelLoaderContext modelLoaderContext) {
        MainModelLoader loader = getMainModelLoader(modelLoaderContext, modelLoaderContext.getBaseFilePath());
        return loader.load(modelLoaderContext);
    }


    public ECoreModel load(String domainName) {

        ModelLoaderContext context = getModelLoaderContext(domainName);

        MainModelLoader loader = getMainModelLoader(context, context.getBaseFilePath());

        return loader.load(context);
        //return load(context);
    }

    private MainModelLoader getMainModelLoader(ModelLoaderContext context, String baseFilePath) {
        String mode = context.getConfig("mode", "jpa");
        String[] modes = mode.split(",");
        if(containsMode(modes,"px")) {
            return new PxFileMainModelLoader(baseFilePath);
        }
        else if(containsMode(modes,"java")) {
            return new JavaSrcFileMainModelLoader(baseFilePath);
        }
        return new YamlFileMainModelLoader(baseFilePath);
    }


    private boolean containsMode(String[] modes, String targetMode) {
        for (String m : modes) {
            if (m.trim().equals(targetMode)) {
                return true;
            }
        }
        return false;
    }

}
