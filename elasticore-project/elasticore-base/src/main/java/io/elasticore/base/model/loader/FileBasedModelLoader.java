package io.elasticore.base.model.loader;

import io.elasticore.base.ModelLoader;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.core.Items;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.entity.Annotation;
import io.elasticore.base.model.entity.Entity;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.entity.Field;
import io.elasticore.base.model.enums.EnumConstant;
import io.elasticore.base.model.enums.EnumModel;
import io.elasticore.base.model.enums.EnumModels;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.elasticore.base.model.loader.module.DataTransferModelLoader;
import io.elasticore.base.model.loader.module.EntityModelLoader;
import io.elasticore.base.model.loader.module.EnumerationModelLoader;
import io.elasticore.base.model.loader.module.RepositoryModelLoader;
import io.elasticore.base.model.repo.Repository;
import io.elasticore.base.model.repo.RepositoryModels;


import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileBasedModelLoader implements ModelLoader, ConstanParam {

    private static FileBasedModelLoader instance;

    public static FileBasedModelLoader newInstance() {
        return new FileBasedModelLoader();
    }

    private String templateFileDirPath;


    public void setTemplateFileDirPath(String dirPath) {
        this.templateFileDirPath = dirPath;
    }

    @Override
    public List<String> getDomainNameList() {
        List<String> domainNmList = new ArrayList();
        domainNmList.add("default");
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


    @Override
    public ECoreModel load(String domainName) {


        //test();

        List<FileSource> fileSources = new ArrayList<>();



        if (templateFileDirPath == null) {
            throw new IllegalArgumentException("templateFileDirPath is empty");
        }

        File envFile = new File(templateFileDirPath+"/env.yml");
        if(!envFile.exists())
            throw new IllegalArgumentException("env.yaml does not exist.");

        FileSource envFs = getLoadData(envFile);
        Map envMap = (Map) envFs.getInfoMap().get("env");

        Map<String,String> configMap = (Map) envMap.get("config");
        Map<String,String> nsMap = (Map) envMap.get("namespace");


        ModelLoaderContext context = new ModelLoaderContext(configMap, nsMap);

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

            entityModelLoader.loadModel(context, map);
            enumerationModelLoader.loadModel(context, map);
            dataTransferModelLoader.loadModel(context, map);
            repositoryModelLoader.loadModel(context, map);

        }

        EntityModels entityModels = EntityModels.create("entityGrp", null, context.getEntityItems());
        EnumModels enumModels = EnumModels.create("enumGroup", null, context.getEnumModelItems());
        RepositoryModels repositoryModels = RepositoryModels.create("repoGroup", null, context.getRepositoryItems());

        //EnumModels enumModels = getEnumModels();
        //EntityModels entityModels = getEntityModels();

        return ECoreModel.builder()
                .entityModels(entityModels)
                .enumModels(enumModels)
                .repositoryModels(repositoryModels)
                .configMap(configMap)
                .namespaceMap(nsMap)
                .build();
    }

}
