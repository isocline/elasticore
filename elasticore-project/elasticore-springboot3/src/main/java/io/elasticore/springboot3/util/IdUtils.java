package io.elasticore.springboot3.util;


import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.util.Random;

public class IdUtils {

    private static char[] alphabet = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };


    public static String newId() {
        Random random = new Random();
        return getCallerClassName() + NanoIdUtils.randomNanoId(random, alphabet, 9);
    }


    private static String getCallerClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return extractClassName(stackTrace[3].getClassName());
    }

    private static String extractClassName(String className) {
        if (className == null || className.length() < 3) {
            return className;
        }

        int lastDotIndex = className.lastIndexOf('.');
        char firstChar = className.charAt(lastDotIndex+1);
        int middleIndex = ( className.length()-lastDotIndex) / 2;
        char middleChar = className.charAt(middleIndex);
        char lastChar = className.charAt(className.length() - 1);
        return ("" + firstChar + middleChar).toUpperCase() + lastChar;
    }
}
