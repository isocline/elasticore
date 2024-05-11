package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlFieldAndQueryExtractor {
    public static void main(String[] args) {
        String sqlQuery = "select * from T_CNCT_LST\n" +
                "where 1=1\n" +
                "AND CONTACT_NAME like CONCAT('5',:contractName, '%') /* if:contractName */\n" +
                "AND contractTel like CONCAT('5',:contractTel, '%') /* if:contractTel */";

        // Regular expression to match lines with an SQL part and a comment containing field name
        Pattern pattern = Pattern.compile("(.*?)(/\\*\\s*if:(\\w+)\\s*\\*/)");
        Matcher matcher = pattern.matcher(sqlQuery);

        while (matcher.find()) {
            // SQL part of the line
            String sqlPart = matcher.group(1).trim();
            // Field name extracted from the comment
            String fieldName = matcher.group(3);
            System.out.println("Field name extracted: " + fieldName);
            System.out.println("SQL part: " + sqlPart);
            System.out.println(); // Print a newline for better separation in output
        }
    }
}
