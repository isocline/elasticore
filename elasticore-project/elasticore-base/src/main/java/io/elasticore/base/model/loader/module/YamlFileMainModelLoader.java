package io.elasticore.base.model.loader.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferModels;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.enums.EnumModels;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.MainModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.model.repo.RepositoryModels;

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


        for (FileSource fileSource : fileSources) {

            Map<String, Map> map = fileSource.getInfoMap();

            if (map == null) {
                continue;
            }

            entityModelLoader.loadModel(context, fileSource);
            enumerationModelLoader.loadModel(context, fileSource);
            dataTransferModelLoader.loadModel(context, fileSource);
            repositoryModelLoader.loadModel(context, fileSource);

        }

        entityModelLoader.completeLoad();
        enumerationModelLoader.completeLoad();
        dataTransferModelLoader.completeLoad();


        EntityModels entityModels = EntityModels.create(context.getDomainId(), "entityGrp", null, context.getEntityItems());
        EnumModels enumModels = EnumModels.create(context.getDomainId(), "enumGroup", null, context.getEnumModelItems());
        DataTransferModels dataTransferModels = DataTransferModels.create(context.getDomainId(), "dto", null, context.getDataTransferItems());


        repositoryModelLoader.completeLoad();
        RepositoryModels repositoryModels = RepositoryModels.create(context.getDomainId(), "repoGroup", null, context.getRepositoryItems());

        //EnumModels enumModels = getEnumModels();
        //EntityModels entityModels = getEntityModels();

        return ECoreModel.builder()
                .entityModels(entityModels)
                .enumModels(enumModels)
                .dataTransferModels(dataTransferModels)
                .repositoryModels(repositoryModels)
                .configMap(context.getConfigMap())
                .namespaceMap(context.getNsMap())
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

        FileSource fileSource = null;

        try {
            Map map = mapper.readValue(f, LinkedHashMap.class);
            return FileSource.builder().filepath(f.getAbsolutePath()).infoMap(map).build();
        } catch (Throwable e) {
            System.err.println(" * LOAD ERROR: "+f.getAbsolutePath());
            e.printStackTrace();
            return FileSource.builder().filepath(f.getAbsolutePath()).error(e).build();
        }

    }
}
