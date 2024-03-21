package io.elasticore.base.model.pub.jpa;

import lombok.SneakyThrows;

import java.io.*;

public class FilePublisher {



    @SneakyThrows
    protected void writeFile(String filePath, String content) {

        System.err.println("write file: "+filePath);

        try (InputStream inputStream = new ByteArrayInputStream(content.getBytes("utf-8"));
             OutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] bytes = new byte[1024];
            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected String getPath(String packageName) {
        return packageName.replace('.', '/');
    }

}
