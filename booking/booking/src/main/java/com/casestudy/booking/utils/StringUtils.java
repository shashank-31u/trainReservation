package com.casestudy.booking.utils;

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

    public static String generatePnr(String str, int maxStringLength) {

        String temp = "";
        if (!checkNullString(str).isEmpty() && str.length() < maxStringLength) {
            for (int i = 0; i < maxStringLength - str.length(); i++) {
                temp = temp + "0";
            }
            str = temp + str;
        }
        return str;

    }

}