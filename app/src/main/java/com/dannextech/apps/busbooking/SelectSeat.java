package com.dannextech.apps.busbooking;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectSeat extends Fragment implements View.OnClickListener {

    private String selectedSeat;
    int i;
    private Button btDriver, bt1X, bt1Y, bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,btSubmit,selected=null;
    private TextView tvAmount;
    public SelectSeat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_select_seat, container, false);

        i =0;
        selectedSeat = null;

        btDriver = (Button) v.findViewById(R.id.btDriver);
        bt1X = (Button) v.findViewById(R.id.bt1X);
        bt1Y = (Button) v.findViewById(R.id.bt1Y);
        bt2 = (Button) v.findViewById(R.id.bt2);
        bt3 = (Button) v.findViewById(R.id.bt3);
        bt4 = (Button) v.findViewById(R.id.bt4);
        bt5 = (Button) v.findViewById(R.id.bt5);
        bt6 = (Button) v.findViewById(R.id.bt6);
        bt7 = (Button) v.findViewById(R.id.bt7);
        bt8 = (Button) v.findViewById(R.id.bt8);
        bt9 = (Button) v.findViewById(R.id.bt9);
        bt10 = (Button) v.findViewById(R.id.bt10);
        btSubmit = (Button) v.findViewById(R.id.btSubmit);
        tvAmount = (TextView) v.findViewById(R.id.tvAmount);

        bt1Y.setOnClickListener(this);
        bt1X.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt10.setOnClickListener(this);
        btSubmit.setOnClickListener(this);



        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1Y:
                selected(bt1Y);
                break;
            case R.id.bt1X:
                selected(bt1X);
                break;
            case R.id.bt2:
                selected(bt2);
                break;
            case R.id.bt3:
                selected(bt3);
                break;
            case R.id.bt4:
                selected(bt4);
                break;
            case R.id.bt5:
                selected(bt5);
                break;
            case R.id.bt6:
                selected(bt6);
                break;
            case R.id.bt7:
                selected(bt7);
                break;
            case R.id.bt8:
                selected(bt8);
                break;
            case R.id.bt9:
                selected(bt9);
                break;
            case R.id.bt10:
                selected(bt10);
                break;
            case R.id.btSubmit:
                if (selectedSeat!=null)
                    saveDetails();
                else
                    Toast.makeText(getContext(),"Please select a seat",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void saveDetails() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(BusDbContractor.MyDatabase.COL_B_AMOUNT,tvAmount.getText().toString());
        editor.putString(BusDbContractor.MyDatabase.COL_B_SEAT_NO,selectedSeat);
        editor.apply();

        Fragment fragment = new ConfirmDetails();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.myFragment,fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }


    private void selected(Button b) {

        if (i == 0){
            selected = b;
            selected.setBackgroundColor(Color.GREEN);
            selectedSeat = selected.getText().toString();
        }else {
            selected.setBackgroundColor(Color.rgb(214,215,215));
            selected = b;
            selected.setBackgroundColor(Color.GREEN);
            selectedSeat = selected.getText().toString();

        }
        i++;
    }
}
