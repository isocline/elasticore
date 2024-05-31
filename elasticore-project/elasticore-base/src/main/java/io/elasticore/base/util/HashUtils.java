package io.elasticore.base.util;

import io.elasticore.base.Version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HashUtils {


    public static String getHashCode(String content) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentDate = sdf.format(new Date());


        int hashCode = 0;
        if (content != null) {
            hashCode = content.hashCode();
        }

        return hashCode + "H" + currentDate;
    }


    public static boolean checkHashCode(String content, String hashcode) {
        if (content == null || hashcode == null)
            return false;

        int sp = hashcode.indexOf("H");
        int hashCode = Integer.valueOf(hashcode.substring(0, sp));

        int cntHashCode = content.hashCode();

        if (hashCode != cntHashCode)
            return false;

        return true;
    }


    public static String makeEcdCode(String content) {
        String ecd = "ecd:" + HashUtils.getHashCode(content) + "_V" + Version.MAJOR;
        return "//" + ecd + "\n";
    }


    public static String extractEcdCode(String lineEcdCode) {
        String startToken = "//ecd:";
        String endToken = "_V";

        int startIndex = lineEcdCode.indexOf(startToken);
        int endIndex = lineEcdCode.indexOf(endToken);

        if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
            startIndex += startToken.length();
            return lineEcdCode.substring(startIndex, endIndex);
        }

        return null;
    }


    /**
     *
     * @param fileReader
     * @return
     */
    public static Response checkContentModified(Reader fileReader) {
        String originalEcdCode = null;
        String content = null;
        try (BufferedReader reader = new BufferedReader(fileReader)) {

            String firstLine = reader.readLine();
            if (firstLine == null) {
                return new Response(null, NO_CONTENT, null);
            }


            originalEcdCode = extractEcdCode(firstLine);


            // 나머지 파일 내용을 읽어서 문자열로 결합
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
            content = contentBuilder.toString();

            if(originalEcdCode==null) {
                return new Response(content, NO_ECD, null);
            }

            if( checkHashCode(content, originalEcdCode)) {
                return new Response(content, NO_MODIFIED, originalEcdCode);
            }else {
                return new Response(content, MODIFIED, originalEcdCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response(content, ERROR, originalEcdCode);
    }

    public final static int ERROR = -99;

    public final static int NO_CONTENT = -1;

    public final static int NO_ECD = 0;

    public final static int NO_MODIFIED = 1;

    public final static int MODIFIED = 10;



    public final static class Response {
        private String content;
        private int status;
        private String ecd;

        public Response(String content, int status, String ecd) {
            this.content = content;
            this.status = status;
            this.ecd = ecd;
        }

        public String getContent() {
            return this.content;
        }

        public String getEcd() {
            return this.ecd;
        }

        public int getStatus() {
            return this.status;
        }


    }


}
