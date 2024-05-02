package io.elasticore.base.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final Pattern SQL_VAR_CHK_PATTERN = Pattern.compile(":(\\w+)");

    /**
     *
     * @param inputString
     * @return
     */
    public static String capitalize(String inputString) {
        char firstLetter = inputString.charAt(0);

        char capitalFirstLetter = Character.toUpperCase(firstLetter);

        return capitalFirstLetter + inputString.substring(1);

        //return inputString.replace(inputString.charAt(0), capitalFirstLetter);
    }

    public static String uncapitalize(String inputString) {
        char firstLetter = inputString.charAt(0);

        char capitalFirstLetter = Character.toLowerCase(firstLetter);

        return capitalFirstLetter + inputString.substring(1);

        //return inputString.replace(inputString.charAt(0), capitalFirstLetter);
    }

    /**
     * @param line
     * @return
     */
    public static String splitByDoubleQuotation(String line) {
        String[] parts = line.split("\n");

        StringBuilder formattedString = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            if (i == 0)
                formattedString.append("\" ");
            else
                formattedString.append(" \" ");
            formattedString.append(parts[i]).append("\"");

            if (i < parts.length - 1) {
                formattedString.append("\n \t\t + ");
            }
        }
        return formattedString.toString();
    }

    public static String camelToSnake(String name) {
        return name.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }


    public static String snakeToCamel(String name) {
        String[] parts = name.split("_");
        StringBuilder camelCaseName = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            camelCaseName.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1).toLowerCase());
        }
        return camelCaseName.toString();
    }


    public static List<String> extractVariables(String sql) {
        List<String> variables = new ArrayList<>();
        Matcher matcher = SQL_VAR_CHK_PATTERN.matcher(sql);

        while (matcher.find()) {
            variables.add(matcher.group(1));
        }

        return variables;
    }

    public static Set<String> extractVariablesSet(String sql) {
        Set<String> variableSet = new HashSet<>();
        Matcher matcher = SQL_VAR_CHK_PATTERN.matcher(sql);

        while (matcher.find()) {
            variableSet.add(matcher.group(1));
        }

        return variableSet;
    }


    // REGX for FIRST SELECT columns
    private static final Pattern FIRST_SELECT_COLMN_PATTERN = Pattern.compile(
            "(?i)SELECT\\s+(.*?)\\s+FROM",
            Pattern.DOTALL
    );

    // regx for column name
    private static final Pattern COLUMN_PATTERN = Pattern.compile(
            "\\s*(?:\\w+\\s*\\(.*?\\)|\\w+)\\s*(?:AS\\s+(\\w+))?\\s*,?",
            Pattern.CASE_INSENSITIVE
    );

    public static List<String> extractFirstSelectColumnNames(String sql) {
        List<String> columnNames = new ArrayList<>();
        Matcher matcher = FIRST_SELECT_COLMN_PATTERN.matcher(sql);

        if (matcher.find()) {
            String columnsPart = matcher.group(1) + ",";
            Matcher columnMatcher = COLUMN_PATTERN.matcher(columnsPart);

            while (columnMatcher.find()) {
                if (columnMatcher.group(1) != null) {
                    columnNames.add(columnMatcher.group(1));
                } else {
                    String column = columnMatcher.group().trim();
                    if (column.endsWith(",")) {
                        column = column.substring(0, column.length() - 1).trim();
                    }
                    columnNames.add(column.replaceAll(".*\\s+(\\w+)$", "$1"));
                }
            }
        }

        return columnNames;
    }

    public static void main(String[] args) {
        String sql = "SELECT test, test2 AS ZZZ FROM (SELECT test1, test2, test3 FROM XXX)";
        List<String> columnNames = extractFirstSelectColumnNames(sql);
        System.out.println("Extracted column names: " + columnNames);
    }
}
