package io.elasticore.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HashUtils {


    public static String getHashCode(String content) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentDate = sdf.format(new Date());


        int hashCode = 0;
        if(content!=null) {
            hashCode = content.hashCode();
        }

        return hashCode+"H"+currentDate;
    }


    public static boolean checkHashCode(String content, String hashcode) {
        if(content ==null || hashcode == null)
            return false;

        int sp = hashcode.indexOf("H");
        int hashCode = Integer.valueOf(hashcode.substring(0, sp));

        int cntHashCode = content.hashCode();

        if(hashCode != cntHashCode)
            return false;

        return true;
    }
}
