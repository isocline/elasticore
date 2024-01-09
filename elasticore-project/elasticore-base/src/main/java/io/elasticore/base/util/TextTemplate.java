package io.elasticore.base.util;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextTemplate {

    private StringBuilder sb = new StringBuilder();

    private Map<String, Object> variableMap = new HashMap<>();

    public TextTemplate() {

    }

    /**
     * @param initMsg
     */
    public TextTemplate(String initMsg) {
        sb.append(initMsg);
    }

    /**
     * append new line
     *
     * @return
     */
    public TextTemplate line() {
        sb.append(System.lineSeparator());

        return this;
    }

    /**
     * append text
     *
     * @param msg
     * @return
     */
    public TextTemplate add(String msg) {
        sb.append(msg);

        return this;
    }


    /**
     * append text after new line
     *
     * @param msg
     * @return
     */
    public TextTemplate line(String msg) {
        line();
        sb.append(msg);

        return this;
    }

    /**
     * set replace information
     *
     * @param key
     * @param value
     * @return
     */
    public TextTemplate set(String key, Object value) {
        String keyName = key;
        if(key.indexOf("$")<0) {
            keyName = "${"+key+"}";
        }
        variableMap.put(keyName, value);

        return this;

    }

    /**
     * get full conversion text
     *
     * @return
     */
    public String toString() {
        String tmpMsg = sb.toString();

        Set<String> set = variableMap.keySet();

        for (String key : set) {
            Object val = variableMap.get(key);

            String valTxt = "";
            if (val != null) valTxt = val.toString();

            StringBuffer sb = replaceText(tmpMsg, key, valTxt);

            tmpMsg = sb.toString();
        }

        return tmpMsg;
    }


    /**
     * replace text
     *
     * @param strTarget
     * @param strSearch
     * @param strReplace
     * @return
     */
    static StringBuffer replaceText(String strTarget, String strSearch, String strReplace) {
        String strCheck = new String(strTarget);
        StringBuffer strBuf = new StringBuffer();
        while (strCheck.length() != 0) {
            int begin = strCheck.indexOf(strSearch);
            if (begin == -1) {
                strBuf.append(strCheck);
                break;
            } else {
                int end = begin + strSearch.length();
                strBuf.append(strCheck.substring(0, begin));
                strBuf.append(strReplace);
                strCheck = strCheck.substring(end);
            }
        }
        return strBuf;
    }

    private static class LineInfo {
        private final static String regex = "\\$\\{(.+?)\\}";

        private Pattern pattern;
        private Matcher matcher;


        private String lineText;

        private String[] keyNames;
        private boolean isMultiLine = false;

        LineInfo(String lineText, boolean isMultiLine) {
            this.lineText = lineText;
            this.isMultiLine = isMultiLine;

            this.pattern = Pattern.compile(regex);
            this.matcher = pattern.matcher(lineText);

        }

        public String toString(Map<String,Value> params) {
            matcher.reset();

            while (matcher.find()) {
                Value replacement = params.get(matcher.group(1));
                if (replacement != null) {
                    // Matcher.quoteReplacement는 정규식 메타문자를 처리하기 위해 필요합니다.
                    matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
                }
            }


        }



    }

    private static class Value {
        private List<Object> valueList = new ArrayList<>();

        public Value(Object val) {
            valueList.add(val);
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
            }catch (NullPointerException npe) {
                return null;
            }
        }

    }


}