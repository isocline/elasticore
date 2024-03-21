package io.elasticore.base.extract;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.ModelLoader;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.core.BaseECoreModelContext;
import io.elasticore.base.model.loader.FileBasedModelLoader;
import io.elasticore.base.model.pub.JPACodePublisher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ModelExtractor {

    private final static String SRC_PATH = "src/main/java";

    private final static String RESOURCE_PATH = "src/main/resources/blueprint";

    private static void log(String msg) {
        System.err.println(msg);
    }


    private List<String> findTemplateFilePath(String rootDir) throws FileNotFoundException {

        String checkDir = rootDir +"/"+RESOURCE_PATH;

        log("CHECK PATH:"+checkDir);

        File f = new File(checkDir);

        if(!f.exists()) {
            throw new FileNotFoundException("Template directory not found:"+checkDir);
        }

        List<String> dirList = new ArrayList<>();

        for(File chiild:f.listFiles()) {
            if(chiild.isDirectory()) {
                String envFilePath = chiild.getAbsolutePath()+"/env.yml";
                log("check >>> "+envFilePath);
                File envFile = new File(envFilePath);
                if(envFile.exists()) {
                    dirList.add(chiild.getAbsolutePath());
                }

            }
        }
        return dirList;
    }

    public void extract() throws FileNotFoundException{

        String rootDir = System.getProperty("user.dir");

        List<String> dirList = findTemplateFilePath(rootDir);

        for(String path: dirList) {
            FileBasedModelLoader loader = FileBasedModelLoader.newInstance();
            loader.setTemplateFileDirPath(path);


            ECoreModelContext ctx = BaseECoreModelContext.getContext(loader);
            ModelDomain defaultDomain = ctx.getDomain();

            ECoreModel model = defaultDomain.getModel();


            JPACodePublisher publisher = JPACodePublisher.newInstance();
            publisher.setDestBaseDirPath(rootDir+"/"+SRC_PATH);

            ctx.publish(publisher);
        }


    }

    public static void main(String[] args) {

        log("ModelExtractor start");


        try {
            ModelExtractor extractor = new ModelExtractor();
            extractor.extract();
            log("ModelExtractor extract");
        }catch (Throwable e) {
            e.printStackTrace();
        }



    }

}
