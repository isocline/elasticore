package io.elasticore.base.extract;

import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SourceFileAccessFactory;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.core.BaseECoreModelContext;
import io.elasticore.base.model.loader.FileBasedModelLoader;
import io.elasticore.base.model.pub.CodePublishManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ModelExtractor {



    private final static String SRC_PATH = "src/main/java";

    private final static String RESOURCE_PATH = "src/main/resources/blueprint";

    private String modelResourcePath;

    private static void log(String msg) {
        System.err.println(msg);
    }


    public ModelExtractor() {
        this.modelResourcePath = getRootDir() + "/"+RESOURCE_PATH;
    }

    public ModelExtractor(String modelResourcePath) {

        this.modelResourcePath = modelResourcePath;
    }


    private List<String> findTemplateFilePath() throws FileNotFoundException {

        String checkDir = this.modelResourcePath;

        log("CHECK PATH:"+checkDir);

        File f = new File(checkDir);

        if(!f.exists()) {
            //throw new FileNotFoundException("Template directory not found:"+checkDir);
            return null;
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

    private String getRootDir() {
        String basePath = System.getProperty("elasticore.base.path");
        if(basePath ==null ) {
            basePath = System.getProperty("user.dir");
        }

        return basePath;
    }

    public void extract(SourceFileAccessFactory sourceFileAccessFactory) throws FileNotFoundException{

        String rootDir = getRootDir();

        List<String> dirList = findTemplateFilePath();

        if(dirList == null || dirList.size()==0) {
            System.err.println(" NOT FOUND "+rootDir);
            return;
        }

        CodePublishManager codePublishManager = CodePublishManager.getInstance();

        for(String path: dirList) {
            FileBasedModelLoader loader = FileBasedModelLoader.newInstance();
            loader.setTemplateFileDirPath(path);


            ECoreModelContext ctx = BaseECoreModelContext.getContext(loader);
            ModelDomain defaultDomain = ctx.getDomain();

            ECoreModel model = defaultDomain.getModel();



            //publisher.setDestBaseDirPath(rootDir+"/"+SRC_PATH);

            if(sourceFileAccessFactory ==null) {
                sourceFileAccessFactory = new FileBasedSourceFileAccessFactory(rootDir+"/"+SRC_PATH);
            }
            codePublishManager.setSrcCodeWriterFactory(sourceFileAccessFactory);
            codePublishManager.publish(ctx);

        }

    }

    public static void main(String[] args) {

        String mode = System.getProperty("mode");
        if(mode==null) mode = "default";

        log("ModelExtractor start mode:"+mode);


        try {
            ModelExtractor extractor = new ModelExtractor();
            if(args!=null && args.length>0) {
                String srcTargetPath = args[0];
                log("srcTargetPath: "+srcTargetPath);
                extractor.extract(new FileBasedSourceFileAccessFactory(srcTargetPath));
            }else {
                extractor.extract(null);
            }


            log("ModelExtractor extract 0525-3");


        }catch (Throwable e) {
            e.printStackTrace();
        }



    }

}
