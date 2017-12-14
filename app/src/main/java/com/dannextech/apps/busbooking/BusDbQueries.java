package com.dannextech.apps.busbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by amoh on 12/13/2017.
 */

public class BusDbQueries {
    SQLiteDatabase db;
    BusDbHelper dbHelper;
    public BusDbQueries(Context context) {
        dbHelper = new BusDbHelper(context);
    }

    public void saveBookings(String names, String tTime, String seatNo, String tDate, String destination, String origin, int amount, String curTimeDay){
        //getting permision to write to the the database
        db = dbHelper.getWritableDatabase();
        //putting data to be stored in a ContentValue
        ContentValues values = new ContentValues();
        values.put(BusDbContractor.MyDatabase.COL_B_PASS_NAMES,names);
        values.put(BusDbContractor.MyDatabase.COL_B_TIME_TRAVEL,tTime);
        values.put(BusDbContractor.MyDatabase.COL_B_SEAT_NO,seatNo);
        values.put(BusDbContractor.MyDatabase.COL_B_DATE_TRAVEL,tDate);
        values.put(BusDbContractor.MyDatabase.COL_B_DESTINATION,destination);
        values.put(BusDbContractor.MyDatabase.COL_B_ORIGIN,origin);
        values.put(BusDbContractor.MyDatabase.COL_B_AMOUNT,amount);
        values.put(BusDbContractor.MyDatabase.COL_B_CURRENT_DATE_TIME,curTimeDay);
        //inserting the the data to the database
        db.insert(BusDbContractor.MyDatabase.TABLE_NAME_B,null,values);
    }
}

