package io.elasticore.base.extract;

import io.elasticore.base.SourceFileAccessFactory;
import io.elasticore.base.util.ConsoleLog;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Deprecated
public class FileBasedSourceFileAccessFactory implements SourceFileAccessFactory {

    protected String baseDir;

    public FileBasedSourceFileAccessFactory(String baseDir) {
        this.baseDir = baseDir;
    }


    protected static String convertToFilePath(String qualifiedClassName, String extName) {
        return qualifiedClassName.replace('.', '/') + "." + extName;
    }

    protected File getFile(String qualifiedClassName, boolean makeDirs) {
        String srcFileName = convertToFilePath(qualifiedClassName, "java");

        String srcPath = baseDir + "/" + srcFileName;

        File f = new File(srcPath);
        if(makeDirs) {
            File dir = f.getParentFile(); //
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }

        return f;
    }

    @Override
    public String getFilePath(String qualifiedClassName) {
        File f = getFile(qualifiedClassName ,true);
        return f.getAbsolutePath();
    }

    @Override
    public Writer getWriter(String qualifiedClassName) throws IOException {

        File f = getFile(qualifiedClassName ,true);
        //System.err.println("published: "+f.getAbsolutePath() +" "+f.exists());
        ConsoleLog.storeLog("PUBLISH", getFilePathInfo(f.getAbsolutePath())+" "+(f.exists()?"!":""));

        return new FileWriter(f);
    }

    protected static String userHome = System.getProperty("user.dir");
    protected String getFilePathInfo(String path) {


        Path input = Paths.get(path);
        Path home = Paths.get(userHome);
        if (input.startsWith(home)) {
            Path relativePath = home.relativize(input);
            return "." + File.separator + relativePath.toString();
        } else {
            return path;
        }
    }


    @Override
    public Reader getReader(String qualifiedClassName) throws IOException {
        File f = getFile(qualifiedClassName ,true);
        return new FileReader(f);
    }

    @Override
    public boolean delete(String qualifiedClassName) {
        File f = getFile(qualifiedClassName ,false);
        if(f.exists()) {
            return f.delete();
        }
        return false;
    }
}
