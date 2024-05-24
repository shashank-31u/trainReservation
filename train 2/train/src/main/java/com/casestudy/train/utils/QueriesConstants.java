package com.casestudy.train.utils;

public class QueriesConstants {

    private QueriesConstants() {
        throw new IllegalStateException("Utility Class");
    }

    public static final String FETCH_TRAINS_BY_TRAIN_NAME = "select * from trains where train_name=:trainName";

    public static final String FETCH_COUNTER_VALUE = "select tc.* from train_counter tc limit 1";

}
