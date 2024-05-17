package io.elasticore.base.model.loader.px;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.dto.DataTransfer;
import io.elasticore.base.model.dto.DataTransferModels;
import io.elasticore.base.model.entity.EntityModels;
import io.elasticore.base.model.enums.EnumModels;
import io.elasticore.base.model.loader.FileSource;
import io.elasticore.base.model.loader.MainModelLoader;
import io.elasticore.base.model.loader.ModelLoaderContext;
import io.elasticore.base.model.repo.RepositoryModels;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PxFileMainModelLoader implements MainModelLoader {

    private DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

    private DocumentBuilder documentBuilder;

    private Document doc = null;

    private XPath xPath;


    private static ObjectMapper mapper = new ObjectMapper(new XmlFactory());

    private String templateFileDirPath;
    private List<FileSource> fileSources = new ArrayList<>();

    public PxFileMainModelLoader(String filePath) {
        this.templateFileDirPath = filePath;
    }


    @Override
    public ECoreModel load(ModelLoaderContext context) {
        File f = new File(templateFileDirPath);
        loadData(f, fileSources);

        io.elasticore.base.model.loader.ModelLoader<DataTransfer> dataTransferModelLoader = new PxDataTransferModelLoader();


        for (FileSource fileSource : fileSources) {

            Element e = fileSource.getElement();


            dataTransferModelLoader.loadModel(context, fileSource);


        }


        dataTransferModelLoader.completeLoad();


        EntityModels entityModels = EntityModels.create(context.getDomainId(),"entityGrp", null, context.getEntityItems());
        EnumModels enumModels = EnumModels.create(context.getDomainId(),"enumGroup", null, context.getEnumModelItems());
        DataTransferModels dataTransferModels = DataTransferModels.create(context.getDomainId(),"dto", null, context.getDataTransferItems());
        RepositoryModels repositoryModels = RepositoryModels.create(context.getDomainId(),"repoGroup", null, context.getRepositoryItems());

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

        if (!f.getName().endsWith(".xml") && !f.getName().endsWith(".pxdp")) {
            return null;
        }

        FileSource fileSource = null;


        try {
            documentBuilder = dbFactory.newDocumentBuilder();
            doc = documentBuilder.parse(f);

            Map map = mapper.readValue(f, LinkedHashMap.class);
            return FileSource.builder().filepath(f.getAbsolutePath()).element(doc.getDocumentElement()).build();
        } catch (Throwable e) {
            System.err.println(f.getAbsolutePath());
            e.printStackTrace();
            return FileSource.builder().filepath(f.getAbsolutePath()).error(e).build();
        }

    }
}
