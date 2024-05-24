package com.casestudy.train.utils;

public class StringUtils {

    private StringUtils() {
        /**
         * 
         */
    }

    public static String checkNullString(String str) {
        return str == null ? "" : str.trim();
    }

    public static String checkNullString(Object obj) {
        return obj == null ? "" : obj.toString().trim();
    }

}
