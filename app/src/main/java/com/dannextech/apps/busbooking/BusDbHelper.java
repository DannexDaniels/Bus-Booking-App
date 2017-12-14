package com.dannextech.apps.busbooking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amoh on 12/12/2017.
 */

public class BusDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bus_booking";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_BOOKINGS = "CREATE TABLE " +
            BusDbContractor.MyDatabase.TABLE_NAME_B + "(" +
            BusDbContractor.MyDatabase._ID + " INTEGER PRIMARY KEY," +
            BusDbContractor.MyDatabase.COL_B_PASS_NAMES + " TEXT," +
            BusDbContractor.MyDatabase.COL_B_TIME_TRAVEL + " TEXT," +
            BusDbContractor.MyDatabase.COL_B_SEAT_NO + " TEXT," +
            BusDbContractor.MyDatabase.COL_B_DATE_TRAVEL + " TEXT," +
            BusDbContractor.MyDatabase.COL_B_DESTINATION + " TEXT," +
            BusDbContractor.MyDatabase.COL_B_ORIGIN + " TEXT," +
            BusDbContractor.MyDatabase.COL_B_AMOUNT + " INTEGER," +
            BusDbContractor.MyDatabase.COL_B_CURRENT_DATE_TIME + " TEXT)";

    private static final String DELETE_BOOKINGS = "DROP TABLE IF EXISTS " + BusDbContractor.MyDatabase.TABLE_NAME_B;

    public BusDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOKINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_BOOKINGS);
    }
}
