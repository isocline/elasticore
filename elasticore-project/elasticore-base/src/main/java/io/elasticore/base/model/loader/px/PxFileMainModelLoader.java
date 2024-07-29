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
import io.elasticore.base.model.loader.px.util.ZipFileExtractor;
import io.elasticore.base.model.repo.RepositoryModels;
import lombok.SneakyThrows;
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


    @SneakyThrows
    @Override
    public ECoreModel load(ModelLoaderContext context) {

        List<String> pxdpzFilePathList = context.getConfigList("pxdpz");

        if(pxdpzFilePathList==null || pxdpzFilePathList.size()==0) {
            throw new IllegalArgumentException("pxdpz config does not exist");
        }

        for(String pxdpzFilePath: pxdpzFilePathList) {

            String newXmlPath = ZipFileExtractor.extractMainDxdp(pxdpzFilePath);
            System.out.println("[PXDPZ] LOAD path: "+pxdpzFilePath);
            System.out.println("[PXDPZ] temp file path: "+newXmlPath);

            File f = new File(newXmlPath);
            FileSource fileSource = null;


            try {
                documentBuilder = dbFactory.newDocumentBuilder();
                doc = documentBuilder.parse(f);

                Map map = mapper.readValue(f, LinkedHashMap.class);
                fileSource =  FileSource.builder().filepath(f.getAbsolutePath()).element(doc.getDocumentElement()).build();

                fileSources.add(fileSource);
            } catch (Throwable e) {
                System.err.println(f.getAbsolutePath());
                e.printStackTrace();

            }
        }

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
