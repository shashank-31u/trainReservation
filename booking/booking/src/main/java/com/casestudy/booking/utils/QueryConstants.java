package com.casestudy.booking.utils;

public class QueryConstants {

    private QueryConstants() {
        throw new IllegalStateException("Utility Class");
    }

    public static final String FETCH_TRAIN_SCHEDULE = "select * from train_schedule where train_number=:trainNumber and source_city=:sourceCity and destination_city=:destinationCity and DATE(date_of_journey)=:dateOfJourney";
    
    public static final String FETCH_TRAIN_SEAT = "select * from seat_booking where train_number=:trainNumber and date_of_journey=:dateOfJourney and train_coach_type=:trainCoachType and train_reserved='false'";

    public static final String FETCH_BOOKING_BY_PNR = "select * from train_booking where pnr=:pnr and booking_id=:bookingId";

    public static final String FETCH_WAITING_LIST_BY_TRAIN = "select * from train_booking where train_number=:trainNumber and date_of_journey=:dateOfJourney and booking_status not in ('CONFIRMED','AWAITING PAYMENT')";

}
