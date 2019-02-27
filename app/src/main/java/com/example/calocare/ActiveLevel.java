package com.example.calocare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import NonActivityClasses.AppControl;
import NonActivityClasses.UserInfo;

public class ActiveLevel extends AppCompatActivity {
    private RadioButton r_button1;
    private RadioButton r_button2;
    private RadioButton r_button3;
    private Button button4;
    private RadioGroup level;
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_level);

        r_button1 = findViewById(R.id.notactive);
        r_button2 = findViewById(R.id.slightlyactive);
        r_button3 = findViewById(R.id.active);
        button4 = findViewById(R.id.button4);

        r_button1.setOnCheckedChangeListener(mListener);
        r_button2.setOnCheckedChangeListener(mListener);
        r_button3.setOnCheckedChangeListener(mListener);

        pref = getSharedPreferences(AppControl.PREF, Activity.MODE_PRIVATE);
        prefEditor = pref.edit();
        level = findViewById(R.id.level);

    }
    CompoundButton.OnCheckedChangeListener mListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked){
                button4.setEnabled(true);
            }else {
                button4.setEnabled(false);
            }
        }
    };

    public void nextActivity(View v) {
        Intent nextActivity = new Intent(this, Goal.class);
        startActivity(nextActivity);
    }


    @Override
    protected void onResume() {
        super.onResume();
        int checkId = pref.getInt("userActiveNha", -1);

        if (checkId == -1) {
            level.clearCheck();
        } else {
            level.check(checkId);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        int selectedId = level.getCheckedRadioButtonId();
        int a = 0;


        if (selectedId != -1) {


            if (selectedId == R.id.notactive) {
                a = 1;
            }
            if (selectedId == R.id.slightlyactive) {
                a = 2;
            }
            if (selectedId == R.id.active) {
                a = 3;
            }

            UserInfo.getInstance().setActiveStatus(a);
        }

        prefEditor.putInt("userActive", selectedId);
        prefEditor.commit();
    }
}