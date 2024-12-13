package io.elasticore.base.extract;

import io.elasticore.base.ECoreModelContext;
import io.elasticore.base.ModelDomain;
import io.elasticore.base.SourceFileAccessFactory;
import io.elasticore.base.model.ConstanParam;
import io.elasticore.base.model.ECoreModel;
import io.elasticore.base.model.core.BaseECoreModelContext;
import io.elasticore.base.model.loader.FileBasedModelLoader;
import io.elasticore.base.model.pub.CodePublishManager;
import io.elasticore.base.util.ConsoleLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ModelExtractor {


    private final static String SRC_PATH = "src/main/java";


    private String modelResourcePath;

    private static void log(String msg) {
        ConsoleLog.print(msg);
    }


    public ModelExtractor() {
        this.modelResourcePath = getRootDir() + "/" + ConstanParam.PROPERTY_ELCORE_HOME;
    }

    public ModelExtractor(String modelResourcePath) {
        this.modelResourcePath = modelResourcePath;
    }


    private List<String> findTemplateFilePath() throws FileNotFoundException {

        String specDomainName = System.getProperty("elasticore.domain");
        if (specDomainName == null || specDomainName.trim().length() == 0) {
            specDomainName = null;
        } else {
            log("elasticore.domain = " + specDomainName);
            specDomainName = ","+specDomainName+",";
        }




        String checkDir = this.modelResourcePath;

        log("Base dir: " + checkDir);

        File f = new File(checkDir);

        if (!f.exists()) {
            //throw new FileNotFoundException("Template directory not found:"+checkDir);
            return null;
        }

        List<String> dirList = new ArrayList<>();

        for (File chiild : f.listFiles()) {
            if (chiild.isDirectory()) {
                String envFilePath = chiild.getAbsolutePath() + "/env.yml";
                ConsoleLog.storeLog("CHECK_ENV", envFilePath);
                File envFile = new File(envFilePath);
                if (envFile.exists()) {

                    String envPath = chiild.getAbsolutePath();
                    //if(envPath.indexOf("px")>=0)

                    String envName = envFile.getParentFile().getName();
                    if (specDomainName == null || specDomainName.indexOf(","+envName+",")>=0)
                        dirList.add(envPath);
                }
            }
        }
        ConsoleLog.print("[INFO] Environment files:");
        ConsoleLog.print("--------------------------------------");
        ConsoleLog.printStoredInfoLog("CHECK_ENV", "    ");
        return dirList;
    }

    private String getRootDir() {
        String basePath = System.getProperty("elasticore.base.path");
        if (basePath == null) {
            basePath = System.getProperty("user.dir");
        }

        return basePath;
    }

    public void deleteGenSource(String baseDir) throws FileNotFoundException {
        String rootDir = getRootDir();

        List<String> dirList = findTemplateFilePath();

        if (dirList == null || dirList.size() == 0) {
            return;
        }

        CodePublishManager codePublishManager = CodePublishManager.getInstance();

        for (String path : dirList) {
            FileBasedModelLoader loader = FileBasedModelLoader.newInstance();
            loader.setTemplateFileDirPath(path);

            ECoreModelContext ctx = BaseECoreModelContext.getContext(loader);

            codePublishManager.deleteGenSource(ctx ,baseDir);
        }

    }


    public void extract(SourceFileAccessFactory sourceFileAccessFactory) throws FileNotFoundException {

        String rootDir = getRootDir();

        List<String> dirList = findTemplateFilePath();

        if (dirList == null || dirList.size() == 0) {
            ConsoleLog.printWarn("Not found model DSL file in " + rootDir);
            return;
        }

        CodePublishManager codePublishManager = CodePublishManager.getInstance();

        for (String path : dirList) {
            FileBasedModelLoader loader = FileBasedModelLoader.newInstance();
            loader.setTemplateFileDirPath(path);


            ECoreModelContext ctx = BaseECoreModelContext.getContext(loader);
            ModelDomain defaultDomain = ctx.getDomain();

            //ECoreModel model = defaultDomain.getModel();

            if (sourceFileAccessFactory == null) {
                sourceFileAccessFactory = new FileBasedSourceFileAccessFactory(rootDir + "/" + ConstanParam.PROPERTY_JAVA_SRC_HOME);
            }
            codePublishManager.setSrcCodeWriterFactory(sourceFileAccessFactory);
            codePublishManager.publish(ctx);

        }

    }

    public static void main(String[] args) {

        String mode = System.getProperty("mode");
        if (mode == null) mode = "default";



        ConsoleLog.printInfo(
                        "    ________           __  _ __________  ____  ______\n" +
                        "   / ____/ /___ ______/ /_(_) ____/ __ \\/ __ \\/ ____/\n" +
                        "  / __/ / / __ `/ ___/ __/ / /   / / / / /_/ / __/   \n" +
                        " / /___/ / /_/ (__  ) /_/ / /___/ /_/ / _, _/ /___   \n" +
                        "/_____/_/\\__,_/____/\\__/_/\\____/\\____/_/ |_/_____/   \n" +
                        "                                                     \n");


        ConsoleLog.printInfo("ModelExtractor execution mode:" + mode);

        try {
            ModelExtractor extractor = new ModelExtractor();
            if (args != null && args.length > 0) {
                String srcTargetPath = args[0];
                log("srcTargetPath: " + srcTargetPath);

                if("clean".equals(mode)) {
                    extractor.deleteGenSource(srcTargetPath);
                }
                else {
                    extractor.extract(new FileBasedSourceFileAccessFactory(srcTargetPath));
                }
            } else {
                extractor.extract(null);
            }


            log("ModelExtractor extract 0816-3");


        } catch (Throwable e) {
            e.printStackTrace();
        }


    }

}
