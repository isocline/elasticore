package io.elasticore.base.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsoleLog {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";


    private static Map<String, List<String>> consoleLogMap = new HashMap<>();


    public static void storeLog(String key, String message) {
        consoleLogMap.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(message);
    }

    public static void clear() {
        consoleLogMap.clear();
    }

    public static void printStoredLog(String key, String blankGap) {
        try {
            consoleLogMap.get(key).forEach(message -> {
                print(blankGap + message);
            });
        }catch (NullPointerException npe) {}
    }

    public static void printStoredInfoLog(String key, String blankGap) {
        try {
            consoleLogMap.get(key).forEach(message -> {
                printInfo(blankGap + message);
            });
        }catch (NullPointerException npe) {}
    }

    public static void printStoredWarnLog(String key, String blankGap) {
        try {
            consoleLogMap.get(key).forEach(message -> {
                printWarn(blankGap + message);
            });
        }catch (NullPointerException npe) {}
    }

    public static int countStoredLogkey(String key) {
        try {
            return consoleLogMap.get(key).size();
        }catch (NullPointerException npe){}

        return 0;
    }


    public static void print(Object message) {
        System.out.println(message);
    }

    public static void printInfo(Object message) {
        System.out.println(GREEN + message + RESET);
    }

    public static void printWarn(Object message) {
        System.out.println(RED + message + RESET);
    }

}
