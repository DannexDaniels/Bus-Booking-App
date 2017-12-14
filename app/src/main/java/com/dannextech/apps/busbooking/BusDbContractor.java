package com.dannextech.apps.busbooking;

import android.provider.BaseColumns;

/**
 * Created by amoh on 12/12/2017.
 */

public class BusDbContractor {
    public BusDbContractor() {
    }

    public static class MyDatabase implements BaseColumns {
        //Attributies for table bookings
        public static final String TABLE_NAME_B = "bookings";
        public static final String COL_B_PASS_NAMES = "names";
        public static final String COL_B_TIME_TRAVEL = "time";
        public static final String COL_B_SEAT_NO = "seat";
        public static final String COL_B_DATE_TRAVEL = "date";
        public static final String COL_B_DESTINATION = "destination";
        public static final String COL_B_ORIGIN = "origin";
        public static final String COL_B_AMOUNT = "amount";
        public static final String COL_B_CURRENT_DATE_TIME = "cur_date_time";

    }
}
