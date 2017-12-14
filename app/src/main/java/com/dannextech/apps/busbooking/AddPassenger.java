package com.dannextech.apps.busbooking;


import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPassenger extends Fragment implements View.OnClickListener{

    private EditText etNames;
    private TextView tvTravelDate;
    private Spinner sTimeTravel, sOrigin, sDestination;
    private Button btSelectSeat;

    private static final String [] towns = {"Kakamega","Nakuru","Nairobi"};
    private static final String [] time = {"Morning","Evening"};

    public AddPassenger() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_passenger, container, false);

        etNames = (EditText) v.findViewById(R.id.etNames);
        tvTravelDate = (TextView) v.findViewById(R.id.tvTravelDate);
        sTimeTravel = (Spinner) v.findViewById(R.id.sTravelTime);
        sOrigin = (Spinner) v.findViewById(R.id.sOrigin);
        sDestination = (Spinner) v.findViewById(R.id.sDestination);
        btSelectSeat = (Button) v.findViewById(R.id.btSelectSeat);

        ArrayAdapter<String> adTowns = new ArrayAdapter<String>(getContext(),android.R.layout.simple_gallery_item,towns);
        ArrayAdapter<String> adTime = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,time);

        sTimeTravel.setAdapter(adTime);
        sOrigin.setAdapter(adTowns);
        sDestination.setAdapter(adTowns);

        tvTravelDate.setOnClickListener(this);
        btSelectSeat.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTravelDate:
                showDatePicker();
                break;
            case R.id.btSelectSeat:
                saveData();

        }
    }

    private void saveData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        if (etNames.getText().toString().isEmpty())
            etNames.setError("Name is required!");
        else if (tvTravelDate.getText().toString().isEmpty())
            tvTravelDate.setError("Date is required!");
        else{
            editor.putString(BusDbContractor.MyDatabase.COL_B_PASS_NAMES,etNames.getText().toString());
            editor.putString(BusDbContractor.MyDatabase.COL_B_TIME_TRAVEL,sTimeTravel.getSelectedItem().toString());
            editor.putString(BusDbContractor.MyDatabase.COL_B_DATE_TRAVEL,tvTravelDate.getText().toString());
            editor.putString(BusDbContractor.MyDatabase.COL_B_DESTINATION,sDestination.getSelectedItem().toString());
            editor.putString(BusDbContractor.MyDatabase.COL_B_ORIGIN,sOrigin.getSelectedItem().toString());
            editor.apply();

            Fragment fragment = new SelectSeat();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.myFragment,fragment);
            fragmentTransaction.commitAllowingStateLoss();
        }

    }

    private void showDatePicker() {
        int mYear,mMonth,mDay;
        Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(getContext(),year+"/"+(month+1)+"/"+dayOfMonth,Toast.LENGTH_SHORT).show();
                setText(dayOfMonth,month,year);
            }
        },mYear,mMonth,mDay);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        //datePickerDialog.getDatePicker().setMaxDate(mDay + 7);
        datePickerDialog.show();
    }

    private void setText(int dayOfMonth, int month, int year) {
        tvTravelDate.setText(dayOfMonth + "/" + month + "/" +year);
    }
}
