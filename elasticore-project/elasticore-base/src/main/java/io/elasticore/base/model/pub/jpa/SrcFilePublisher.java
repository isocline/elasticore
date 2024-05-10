package io.elasticore.base.model.pub.jpa;

import io.elasticore.base.CodePublisher;
import io.elasticore.base.model.ModelComponent;
import lombok.SneakyThrows;

import java.io.*;

public class SrcFilePublisher {


    protected void writeSrcCode(CodePublisher publisher, ModelComponent modelComponent, String qualifiedClassName, String content) {
        try (Writer writer = publisher.getSrcCodeWriterFactory().getWriter(qualifiedClassName)) {
            writer.write(content);
        } catch (Throwable e) {
            if(modelComponent!=null)
                publisher.errorOnPublish(modelComponent, e);
            else
                e.printStackTrace();
        }
    }


    protected String getPath(String packageName) {
        return packageName.replace('.', '/');
    }

}
