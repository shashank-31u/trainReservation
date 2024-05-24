package com.casestudy.train.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringConstants {

    private StringConstants() {
        throw new IllegalStateException("Utility Class");
    }

    public static final String MONDAY = "Monday";
    public static final String TUESDAY = "Tuesday";
    public static final String WEDNESDAY = "Wednesday";
    public static final String THURSDAY = "Thursday";
    public static final String FRIDAY = "Friday";
    public static final String SATURDAY = "Saturday";
    public static final String SUNDAY = "Sunday";

    public static final Set<String> weekdays = new HashSet<>(
            Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY));

}
