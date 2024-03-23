package io.elasticore.base.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class CodeTemplate {
    private static final String NEWLINE = "\n";

    private List<LineInfo> lineInfos = new ArrayList<>();

    private CodeTemplate() {
    }

    public static CodeTemplate newInstance() {
        return new CodeTemplate();
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
     *
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
                if(paragraph ==null) continue;
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
                    if(paragraph ==null) continue;
                    String valueTxt = paragraph.toString(i);
                    regTmpTxt = regTmpTxt.replace("${" + keyNm + "}", valueTxt);
                }
                lineList.add(regTmpTxt);
            }

            StringBuilder sb = new StringBuilder();
            for (String line : lineList) {
                sb.append(line).append("\n");
                if(lineSuffix!=null)
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
            paramMap.put(keyName, new Paragraph(true,value));
            return this;
        }

        public Parameters set(String keyName, Paragraph paragraph) {
            paramMap.put(keyName, paragraph);
            return this;
        }
    }

    public static class Paragraph {

        private List<Object> valueList = new ArrayList<>();

        private Map<Object,Object> checkMap;

        private Paragraph(boolean isUnique) {
            if(isUnique)
                checkMap = new HashMap<>();
        }

        private Paragraph(boolean isUnique, Object val) {
            this(isUnique);
            valueList.add(val);
        }

        public Paragraph add(Object val) {
            if(checkMap!=null) {
                if(checkMap.containsKey(val))
                    return this;

                checkMap.put(val, val);
            }
            valueList.add(val);
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
                    return valueList.get(0).toString();
                }
                return null;
            }
        }

    }
}