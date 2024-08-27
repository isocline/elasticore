package io.elasticore.base.extract;

import io.elasticore.base.SourceFileAccessFactory;

import javax.annotation.processing.Filer;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class FilerBasedSourceFileAccessFactory implements SourceFileAccessFactory {

    private Filer filer;

    public FilerBasedSourceFileAccessFactory(Filer filer) {
        this.filer = filer;
    }

    @Override
    public Writer getWriter(String qualifiedClassName) throws IOException {

        return this.filer.createSourceFile(qualifiedClassName).openWriter();
    }

    @Override
    public Reader getReader(String qualifiedClassName) throws IOException {
        boolean ignoreEncodingErrors = true;
        return this.filer.createSourceFile(qualifiedClassName).openReader(ignoreEncodingErrors);
    }

    @Override
    public boolean delete(String qualifiedClassName) {
        try {
            return this.filer.createSourceFile(qualifiedClassName).delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getFilePath(String qualifiedClassName) {
        return qualifiedClassName;
    }
}
