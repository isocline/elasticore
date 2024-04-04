package io.elasticore.base;

import java.io.IOException;
import java.io.Writer;

public interface SrcCodeWriterFactory {

    Writer getWriter(String qualifiedClassName) throws IOException;
}
