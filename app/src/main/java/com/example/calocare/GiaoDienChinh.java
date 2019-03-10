package com.example.calocare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.app.AlarmManager;
import android.app.PendingIntent;





import NonActivityClasses.AppControl;
import NonActivityClasses.Calories;
import NonActivityClasses.UserInfo;

public class GiaoDienChinh extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    private SharedPreferences pref1;
    private SharedPreferences.Editor prefEditor1;

    private TextView caloGoal;
    private TextView caloAdded;
    private TextView caloRemain;
    private String formatdate;

    private UserInfo user = UserInfo.getInstance();
    private int a;


    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chinh);

        pref = getSharedPreferences(AppControl.PREF, Activity.MODE_PRIVATE);
        prefEditor = pref.edit();

        pref1 = getSharedPreferences(AppControl.PREF1, Activity.MODE_PRIVATE);
        prefEditor1 = pref1.edit();

        if(pref.getString("userName", "").equals("")) {
            toBasicInfo();
        }

        caloGoal = findViewById(R.id.tv_goal);
        caloAdded = findViewById(R.id.tv_food);
        caloRemain = findViewById(R.id.tv_remain);


        setUserValue();
        print();

        TextClock textClock = findViewById(R.id.textClock);

        formatdate = "HH:mm:ss";
        textClock.setFormat12Hour(null);
        textClock.setFormat24Hour(formatdate);



        setAlarm();



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Integer.parseInt(caloGoal.getText().toString()) != 0) {
            prefEditor1.putInt("foodGoal", Integer.parseInt(caloGoal.getText().toString()));
            prefEditor1.putInt("foodAdded", Integer.parseInt(caloAdded.getText().toString()));
            prefEditor1.putInt("foodRemain", Integer.parseInt(caloRemain.getText().toString()));
            prefEditor1.commit();
        }
    }
    
    public void addFood(View v) {
        Intent nextActivity = new Intent(this, FoodChoice.class);
        startActivity(nextActivity);
    }

    public void editInfo(View v) {
        toBasicInfo();
    }

    public void toReset(View v) {
        Calories.getInstance().reset();
        prefEditor1.putInt("foodGoal", Integer.parseInt(caloGoal.getText().toString()));
        prefEditor1.putInt("foodAdded", 0);
        prefEditor1.putInt("foodRemain", Calories.getInstance().calcRemain());
        prefEditor1.commit();
        print();
    }

    public void print(){
        a = pref1.getInt("foodAdded", 0);
        Calories.getInstance().setAddedCalo(a);

        caloGoal.setText(String.valueOf(/*pref.getInt("foodGoal",*/ Calories.getInstance().maxCalo()));
        caloAdded.setText(String.valueOf(pref1.getInt("foodAdded", 0)));
        caloRemain.setText(String.valueOf(/*pref.getInt("foodRemain",*/ Calories.getInstance().calcRemain()));
    }

    private void setUserValue() {
        user.setAge(pref.getInt("userAge", 0));
        user.setGender(pref.getString("userGenderText", ""));
        user.setHeight(pref.getInt("userHeight", 0));
        user.setWeight(pref.getInt("userWeight", 0));
        user.setActiveStatus(pref.getFloat("userActiveVal", 0));
        user.setGoalStatus(pref.getInt("userGoalVal", 0));
    }

    private void toBasicInfo() {
        Intent nextActivity = new Intent(this, BasicInfo.class);
        startActivity(nextActivity);
    }


    private void setAlarm() {





    }

}