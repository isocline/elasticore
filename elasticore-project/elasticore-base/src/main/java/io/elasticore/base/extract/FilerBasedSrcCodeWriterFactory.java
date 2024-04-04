package io.elasticore.base.extract;

import io.elasticore.base.SrcCodeWriterFactory;

import javax.annotation.processing.Filer;
import java.io.IOException;
import java.io.Writer;

public class FilerBasedSrcCodeWriterFactory implements SrcCodeWriterFactory {

    private Filer filer;

    public FilerBasedSrcCodeWriterFactory(Filer filer) {
        this.filer = filer;
    }

    @Override
    public Writer getWriter(String qualifiedClassName) throws IOException {

        return this.filer.createSourceFile(qualifiedClassName).openWriter();
    }
}
