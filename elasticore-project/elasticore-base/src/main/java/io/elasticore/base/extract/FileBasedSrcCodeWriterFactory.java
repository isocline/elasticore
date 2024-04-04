package io.elasticore.base.extract;

import io.elasticore.base.SrcCodeWriterFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;



public class FileBasedSrcCodeWriterFactory implements SrcCodeWriterFactory {

    private String baseDir;

    public FileBasedSrcCodeWriterFactory(String baseDir) {
        this.baseDir = baseDir;
    }


    private static String convertToFilePath(String qualifiedClassName, String extName) {
        return qualifiedClassName.replace('.', '/') + "." + extName;
    }

    @Override
    public Writer getWriter(String qualifiedClassName) throws IOException {

        String srcFileName = convertToFilePath(qualifiedClassName, "java");

        String srcPath = baseDir + "/" + srcFileName;

        File f = new File(srcPath);
        File dir = f.getParentFile(); //
        if (!dir.exists()) {
            dir.mkdirs();
        }

        System.err.println(">> "+f.getAbsolutePath());

        return new FileWriter(f);
    }
}
