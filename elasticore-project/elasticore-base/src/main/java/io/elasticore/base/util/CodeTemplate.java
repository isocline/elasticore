package io.elasticore.base.util;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CodeTemplate {

    private StringBuilder sb = new StringBuilder();

    private Map<String, Object> variableMap = new HashMap<>();

    public CodeTemplate() {

    }

    /**
     * @param initMsg
     */
    public CodeTemplate(String initMsg) {
        sb.append(initMsg);
    }

    /**
     * append new line
     *
     * @return
     */
    public CodeTemplate line() {
        sb.append(System.lineSeparator());

        return this;
    }

    /**
     * append text
     *
     * @param msg
     * @return
     */
    public CodeTemplate add(String msg) {
        sb.append(msg);

        return this;
    }


    /**
     * append text after new line
     *
     * @param msg
     * @return
     */
    public CodeTemplate line(String msg) {
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
    public CodeTemplate set(String key, Object value) {
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


    private final class LineInfo {
        private boolean isMultiLine = false;
        private String text;
        private List<String> keyNameList;

        private final String regex = "\\$\\{(.+?)\\}";

        LineInfo(String text, boolean isMultiLine) {
            this.text = text;
            this.isMultiLine = isMultiLine;
        }

        private void parse() {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            keyNameList = new ArrayList<>();

            while (matcher.find()){
                keyNameList.add(matcher.group(1));
            }
        }

        public String getText(String text) {

        }


    }


}