package io.elasticore.base.util;

import io.elasticore.base.Version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class HashUtils {

    private final static String STX = "== DEVELOPER SECTION START ==";
    private final static String ETX = "== DEVELOPER SECTION END ==";

    private final static boolean SKIP_BLANK_LINE = true;


    public static String getHashCode(String content) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentDate = sdf.format(new Date());


        int hashCode = 0;
        if (content != null) {
            //hashCode = content.hashCode();
            hashCode = generateHashCode(content, SKIP_BLANK_LINE);
        }

        return hashCode + "H" + currentDate;
    }


    public static boolean checkHashCode(String content, String hashcode, String version) {
        if (content == null || hashcode == null)
            return false;

        int sp = hashcode.indexOf("H");
        int hashCode = Integer.valueOf(hashcode.substring(0, sp));

        int cntHashCode =0;
        if(version.indexOf("0.")>=0 ) {
            // for old version
            cntHashCode = generateHashCode(content , false);
        }else {
            cntHashCode = generateHashCode(content , SKIP_BLANK_LINE);
        }

        if (hashCode != cntHashCode)
            return false;

        return true;
    }

    public static int generateHashCode(String input, boolean skipBlankline) {
        if(input==null)
            return -1;

        if(!skipBlankline)
            return input.hashCode();

        StringBuilder processedString = new StringBuilder();

        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                processedString.append(line).append("\n");
            }
        }
        scanner.close();
        return processedString.toString().hashCode();

    }


    public static String makeEcdCode(String content) {
        String ecd = "ecd:" + HashUtils.getHashCode(content) + "_V" + Version.MAJOR;
        return "//" + ecd + "\n";
    }

    public static String getVersion(String lineEcdCode) {
        int sp = lineEcdCode.indexOf("_V");
        return lineEcdCode.substring(sp+2);
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
        String customizedScope = null;
        try (BufferedReader reader = new BufferedReader(fileReader)) {

            String firstLine = reader.readLine();
            if (firstLine == null) {
                return new Response(null, NO_CONTENT, null ,customizedScope);
            }


            originalEcdCode = extractEcdCode(firstLine);

            String version = getVersion(firstLine);


            // 나머지 파일 내용을 읽어서 문자열로 결합
            StringBuilder contentBuilder = new StringBuilder();
            StringBuilder customizedScopeContent = new StringBuilder();
            String line;
            boolean isCustomizedScope = false;
            while ((line = reader.readLine()) != null) {
                if(line.indexOf(STX)>0){
                    isCustomizedScope = true;
                }
                else if(isCustomizedScope && line.indexOf(ETX)>0){
                    isCustomizedScope = false;
                    customizedScopeContent.append(line).append("\n");
                    continue;
                }


                if(isCustomizedScope)
                    customizedScopeContent.append(line).append("\n");
                else
                    contentBuilder.append(line).append("\n");
            }
            content = contentBuilder.toString();

            customizedScope = customizedScopeContent.toString();

            if(originalEcdCode==null) {
                return new Response(content, NO_ECD, null ,customizedScope);
            }

            if( checkHashCode(content, originalEcdCode ,version)) {
                return new Response(content, NO_MODIFIED, originalEcdCode, customizedScope);
            }else {
                return new Response(content, MODIFIED, originalEcdCode ,customizedScope);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response(content, ERROR, originalEcdCode ,customizedScope);
    }

    public final static int ERROR = -99;

    public final static int NO_CONTENT = -1;

    public final static int NO_ECD = 0;

    public final static int NO_MODIFIED = 1;

    public final static int MODIFIED = 10;



    public final static class Response {
        private final String content;
        private final int status;
        private final String ecd;

        private final String customizedScopeContent;

        public Response(String content, int status, String ecd ,String customizedScopeContent) {
            this.content = content;
            this.status = status;
            this.ecd = ecd;
            this.customizedScopeContent = customizedScopeContent;
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

        public String getCustomizedScopeContent() {
            return this.customizedScopeContent;
        }

        public boolean hasCustomizedScopeContent() {
            return this.customizedScopeContent != null && !this.customizedScopeContent.isEmpty();
        }


    }


}
