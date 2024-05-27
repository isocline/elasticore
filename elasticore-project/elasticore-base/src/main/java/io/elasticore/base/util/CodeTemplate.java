package io.elasticore.base.util;



import lombok.SneakyThrows;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 */

public class CodeTemplate {



    private static final String NEWLINE = "\n";

    private List<LineInfo> lineInfos = new ArrayList<>();

    private CodeTemplate() {
    }

    private static String getRootDir() {
        String basePath = System.getProperty("elasticore.base.path");
        if (basePath == null) {
            basePath = System.getProperty("user.dir");
        }

        return basePath;
    }


    private static InputStream getTemplateInputStream(String resourcePath) throws FileNotFoundException {
        InputStream inputStream = null;
        if (resourcePath.indexOf("resource://") == 0) {

            String path = getRootDir() + "/src/main/resources/" + resourcePath.substring(11);




            System.out.println("template: "+path);
            inputStream = new FileInputStream(path);
        } else {
            System.out.println("template[resource]: "+resourcePath);
            inputStream = CodeTemplate.class.getClassLoader().getResourceAsStream(resourcePath);
        }

        return inputStream;
    }

    public static CodeTemplate newInstance() {
        return new CodeTemplate();
    }


    @SneakyThrows
    public static CodeTemplate newInstance(String resourcePath) {
        CodeTemplate codeTemplate = new CodeTemplate();
        InputStream inputStream = getTemplateInputStream(resourcePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Stream<String> lines = reader.lines();
            lines.forEach(line -> {
                if (line.contains("List}")) {
                    codeTemplate.line(line, true);
                } else {
                    codeTemplate.line(line);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Resource parse error.", e);
        }
        return codeTemplate;
    }


    /**
     * append new line
     *
     * @return CodeTemplate
     */
    public CodeTemplate line() {

        this.lineInfos.add(new NewLineInfo());
        return this;
    }

    /**
     * get full conversion text
     *
     * @return String
     */
    public String toString(Parameters parameters) {
        StringBuilder sb = new StringBuilder();

        for (LineInfo lineInfo : lineInfos) {
            String lineTxt = lineInfo.toString(parameters);
            sb.append(lineTxt).append(NEWLINE);
        }

        return sb.toString();
    }

    /**
     * @param line
     * @param isMultiLine
     * @return
     */
    public CodeTemplate line(String line, boolean isMultiLine) {
        return line(line, isMultiLine, null);
    }


    public CodeTemplate line(String line, boolean isMultiLine, String lineSuffix) {
        LineInfo lineInfo = new RegxLineInfo(line, isMultiLine, lineSuffix);
        this.lineInfos.add(lineInfo);

        return this;
    }

    public CodeTemplate line(String line) {
        return line(line, false);
    }

    public static Paragraph newParagraph() {
        return new Paragraph(false);
    }

    public static Parameters newParameters() {
        return new Parameters();
    }

    /**
     *
     */
    private interface LineInfo {
        String toString(Parameters parameters);
    }


    /**
     *
     */
    private static class NewLineInfo implements LineInfo {

        @Override
        public String toString(Parameters parameters) {
            return "";
        }
    }

    /**
     *
     */
    private static class RegxLineInfo implements LineInfo {
        private final static String regex = "\\$\\{(.+?)\\}";

        private Pattern pattern;
        private Matcher matcher;

        private String lineText;
        private String lineSuffix;

        private String[] keyNames;
        private boolean isMultiLine = false;

        private List<String> keyNameList = new ArrayList<>();

        RegxLineInfo(String lineText, boolean isMultiLine, String lineSuffix) {
            this.lineText = lineText;
            this.isMultiLine = isMultiLine;
            this.lineSuffix = lineSuffix;

            this.pattern = Pattern.compile(regex);
            this.matcher = pattern.matcher(lineText);

            while (matcher.find()) {
                String keyNm = matcher.group(1);
                keyNameList.add(keyNm);
            }
        }

        private int getMaxCount(Parameters parameters) {
            int maxCount = 0;
            for (String keyNm : keyNameList) {
                Paragraph paragraph = parameters.getParamMap().get(keyNm);
                if (paragraph == null) continue;
                int valueCount = paragraph.size();
                if (valueCount > maxCount) {
                    maxCount = valueCount;
                }
            }

            return maxCount;
        }

        public String toString(Parameters parameters) {
            if (keyNameList.size() == 0) {
                return this.lineText;
            }

            int maxCount = getMaxCount(parameters);

            List<String> lineList = new ArrayList<>();

            String replaceTxt = lineText;
            for (int i = 0; i < maxCount; i++) {
                String regTmpTxt = replaceTxt;
                for (String keyNm : keyNameList) {
                    Paragraph paragraph = parameters.getParamMap().get(keyNm);
                    if (paragraph == null) continue;
                    String valueTxt = paragraph.toString(i);
                    regTmpTxt = regTmpTxt.replace("${" + keyNm + "}", valueTxt);
                }
                lineList.add(regTmpTxt);
            }

            boolean isFirst= true;
            StringBuilder sb = new StringBuilder();
            for (String line : lineList) {
                if(!isFirst)
                    sb.append("\n");
                isFirst = false;
                sb.append(line);
                if (lineSuffix != null)
                    sb.append(lineSuffix);
            }
            return sb.toString();
        }
    }

    public static class Parameters {

        private Map<String, Paragraph> paramMap = new HashMap<>();

        private Parameters() {
        }

        private Map<String, Paragraph> getParamMap() {
            return this.paramMap;
        }

        public Parameters set(String keyName, String value) {
            paramMap.put(keyName, new Paragraph(true, value));
            return this;
        }

        public Parameters set(String keyName, Paragraph paragraph) {
            paramMap.put(keyName, paragraph);
            return this;
        }
    }

    public static class Paragraph {

        private List<Object> valueList = new ArrayList<>();

        private Map<Object, Object> checkMap;

        private Paragraph(boolean isUnique) {
            if (isUnique)
                checkMap = new HashMap<>();
        }

        private Paragraph(boolean isUnique, Object val) {
            this(isUnique);
            valueList.add(val);
        }

        public Paragraph add(String template, Object... parameter) {
            String code = String.format(template, parameter);
            return add(code);
        }

        public boolean contains(String content) {
            for(Object t:valueList) {
                if(t!=null && t.toString().contains(content))
                    return true;
            }
            return false;
        }


        public Paragraph add(Object val) {

            if (val != null) {

                if (checkMap != null) {
                    if (checkMap.containsKey(val))
                        return this;

                    checkMap.put(val, val);
                }
                String txt = val.toString();
                String[] linesArray = txt.split("\n");
                for (String line : linesArray) {
                    valueList.add(line);
                }


            }
            return this;
        }

        public int size() {
            return this.valueList.size();
        }

        public String toString() {
            return toString(0);
        }

        public String toString(int idx) {
            try {
                return this.valueList.get(idx).toString();
            } catch (NullPointerException npe) {
                if (valueList.size() == 1) {
                    try {
                        return valueList.get(0).toString();
                    } catch (NullPointerException noe2) {

                    }
                }
                return "";
            }
        }

    }
}