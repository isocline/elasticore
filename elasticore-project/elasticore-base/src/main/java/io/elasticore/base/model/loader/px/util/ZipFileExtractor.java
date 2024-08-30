package io.elasticore.base.model.loader.px.util;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileExtractor {
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private static final String PREVIOUS_EXTRACTION_TIME_FILE = "px.dat";


    /**
     *
     * @param zipFilePath
     * @return
     * @throws IOException
     */
    public static String extractMainDxdp(String zipFilePath) throws IOException {
        Path zipPath = Paths.get(zipFilePath);
        FileTime lastModifiedTime = Files.getLastModifiedTime(zipPath);

        String timestampFilePath = getTimestampFilePath(zipFilePath);
        if (Files.exists(Paths.get(timestampFilePath))) {
            FileTime previousModifiedTime = FileTime.fromMillis(Long.parseLong(readFileAsString(timestampFilePath)));
            if (lastModifiedTime.equals(previousModifiedTime)) {
                return getExtractedFilePath(zipFilePath).replace(".pxdp", ".xml");
            }
        }

        String extractedFilePath = unzipMainDxdp(zipFilePath);
        writeFileAsString(timestampFilePath, String.valueOf(lastModifiedTime.toMillis()));

        return extractedFilePath;
    }

    private static String unzipMainDxdp(String zipFilePath) throws IOException {
        String extractedFilePath = getExtractedFilePath(zipFilePath);

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if ("Main.pxdp".equals(entry.getName())) {
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(extractedFilePath))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zis.read(buffer)) != -1) {
                            bos.write(buffer, 0, bytesRead);
                        }
                    }
                    break;
                }
            }
        }

        Path xmlPath = Paths.get(extractedFilePath);
        Path xmlRenamedPath = xmlPath.resolveSibling(xmlPath.getFileName().toString().replace(".dxdp", ".xml"));
        Files.move(xmlPath, xmlRenamedPath, StandardCopyOption.REPLACE_EXISTING);

        return xmlRenamedPath.toString();
    }

    private static String getExtractedFilePath(String zipFilePath) {
        String fileNameWithoutExt = Paths.get(zipFilePath).getFileName().toString().replaceFirst("[.][^.]+$", "");
        return Paths.get(TEMP_DIR, fileNameWithoutExt + ".dxdp").toString();
    }

    private static String getTimestampFilePath(String zipFilePath) {
        String fileNameWithoutExt = Paths.get(zipFilePath).getFileName().toString().replaceFirst("[.][^.]+$", "");
        return Paths.get(TEMP_DIR, fileNameWithoutExt + "_" + PREVIOUS_EXTRACTION_TIME_FILE).toString();
    }

    private static String readFileAsString(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                contentBuilder.append(currentLine);
            }
        }
        return contentBuilder.toString();
    }

    private static void writeFileAsString(String filePath, String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
        }
    }
}
