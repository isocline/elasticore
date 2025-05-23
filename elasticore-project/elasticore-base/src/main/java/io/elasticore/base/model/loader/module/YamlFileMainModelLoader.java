package io.elasticore.base.model.loader.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.core.BaseModelDomain;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferModels;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.enums.EnumModels;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.MainModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.model.port.Port;
import io.elasticore.base.model.port.PortModels;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.model.repo.RepositoryModels;
import io.elasticore.base.util.ConsoleLog;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class YamlFileMainModelLoader implements MainModelLoader {

    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    private String templateFileDirPath;
    private List<FileSource> fileSources = new ArrayList<>();

    public YamlFileMainModelLoader(String filePath) {
        this.templateFileDirPath = filePath;
    }


    @Override
    public ECoreModel load(ModelLoaderContext context) {


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
        portModelLoader.completeLoad();


        EntityModels entityModels = EntityModels.create(context.getDomainId(), "entityGrp", null, context.getEntityItems());
        EnumModels enumModels = EnumModels.create(context.getDomainId(), "enumGroup", null, context.getEnumModelItems());
        DataTransferModels dataTransferModels = DataTransferModels.create(context.getDomainId(), "dto", null, context.getDataTransferItems());
        PortModels portModels = PortModels.create(context.getDomainId(), "port", null, context.getPortItems());


        ECoreModel tmpModel = ECoreModel.builder()
                .entityModels(entityModels)
                .enumModels(enumModels)
                .internalDomainName(context.getDomainId())
                .dataTransferModels(dataTransferModels).build();
        BaseModelDomain.setCurrentModel(tmpModel);



        repositoryModelLoader.completeLoad();
        RepositoryModels repositoryModels = RepositoryModels.create(context.getDomainId(), "repoGroup", null, context.getRepositoryItems());

        //EnumModels enumModels = getEnumModels();
        //EntityModels entityModels = getEntityModels();

        return ECoreModel.builder()
                .entityModels(entityModels)
                .enumModels(enumModels)
                .dataTransferModels(dataTransferModels)
                .repositoryModels(repositoryModels)
                .portModels(portModels)
                .configMap(context.getConfigMap())
                .namespaceMap(context.getNsMap())
                .internalDomainName(context.getDomainId())
                .build();
    }


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


    private FileSource getLoadData(File f) {

        if (!f.getName().endsWith(".yml") && !f.getName().endsWith(".yaml")) {
            return null;
        }

        if(f.length()==0) {
            ConsoleLog.printWarn(" * EMPTY: "+f.getAbsolutePath());
            return null;
        }

        FileSource fileSource = null;

        try {
            Map map = mapper.readValue(f, LinkedHashMap.class);
            return FileSource.builder().filepath(f.getAbsolutePath()).infoMap(map).build();
        } catch (Throwable e) {
            ConsoleLog.printWarn(" * LOAD ERROR: "+f.getAbsolutePath());
            e.printStackTrace();
            return FileSource.builder().filepath(f.getAbsolutePath()).error(e).build();
        }

    }
}
