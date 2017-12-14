package com.dannextech.apps.busbooking;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmDetails extends Fragment {

    private TextView tvName,tvTime,tvDate,tvOrigin,tvDestination,tvSeatNo,tvAmount;
    private Button btConfirm;
    private String curTimeDate;



    public ConfirmDetails() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_confirm_details, container, false);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final BusDbQueries query = new BusDbQueries(getContext());

        tvAmount = (TextView) v.findViewById(R.id.tvcfAmount);
        tvTime = (TextView) v.findViewById(R.id.tvcfTravelTime);
        tvDate = (TextView) v.findViewById(R.id.tvcfTravelDate);
        tvOrigin = (TextView) v.findViewById(R.id.tvcfOrigin);
        tvDestination = (TextView) v.findViewById(R.id.tvcfDestination);
        tvSeatNo = (TextView) v.findViewById(R.id.tvcfSeat);
        tvName = (TextView) v.findViewById(R.id.tvcfNames);
        btConfirm = (Button) v.findViewById(R.id.btcfSubmit);

        tvName.setText(preferences.getString(BusDbContractor.MyDatabase.COL_B_PASS_NAMES,""));
        tvTime.setText(preferences.getString(BusDbContractor.MyDatabase.COL_B_TIME_TRAVEL,""));
        tvDate.setText(preferences.getString(BusDbContractor.MyDatabase.COL_B_DATE_TRAVEL,""));
        tvDestination.setText(preferences.getString(BusDbContractor.MyDatabase.COL_B_DESTINATION,""));
        tvOrigin.setText(preferences.getString(BusDbContractor.MyDatabase.COL_B_ORIGIN,""));
        tvSeatNo.setText(preferences.getString(BusDbContractor.MyDatabase.COL_B_SEAT_NO,""));
        tvAmount.setText(preferences.getString(BusDbContractor.MyDatabase.COL_B_AMOUNT,""));


        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                String timeNow = sdf.format(currentTime);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd:mm:yy");
                String dateToday = dateFormat.format(currentTime);

                curTimeDate = timeNow + " " + dateToday;
                query.saveBookings(tvName.getText().toString(),tvTime.getText().toString(),tvSeatNo.getText().toString(),tvDate.getText().toString(),tvDestination.getText().toString(),tvOrigin.getText().toString(),Integer.parseInt(tvAmount.getText().toString()),curTimeDate);
            }
        });

        return v;
    }

}
