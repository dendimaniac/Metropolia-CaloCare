package com.example.calocare;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import NonActivityClasses.AlarmResetFoodAdded;
import NonActivityClasses.AppControl;
import NonActivityClasses.Calories;
import NonActivityClasses.UserInfo;

public class GiaoDienChinh extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    private TextView caloGoal;
    private TextView caloAdded;
    private TextView caloRemain;

    private UserInfo user = UserInfo.getInstance();
    private int a;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chinh);

        pref = getSharedPreferences(AppControl.PREF, Activity.MODE_PRIVATE);
        prefEditor = pref.edit();

        caloGoal = findViewById(R.id.tv_goal);
        caloAdded = findViewById(R.id.tv_food);
        caloRemain = findViewById(R.id.tv_remain);

        if(pref.getString("userName", "").equals("")) {
            toBasicInfo();
        } else {
            setAlarm();
            setUserValue();
            reset(pref.getInt("foodAdded", 0));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        print();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Integer.parseInt(caloGoal.getText().toString()) != 0) {
            prefEditor.putInt("foodGoal", Integer.parseInt(caloGoal.getText().toString()));
            prefEditor.putInt("foodAdded", Integer.parseInt(caloAdded.getText().toString()));
            prefEditor.putInt("foodRemain", Integer.parseInt(caloRemain.getText().toString()));
            prefEditor.commit();
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
        reset(0);
        print();
    }

    public void print(){
        caloGoal.setText(String.valueOf(pref.getInt("foodGoal", Calories.getInstance().maxCalo())));
        caloAdded.setText(String.valueOf(pref.getInt("foodAdded", Calories.getInstance().getAddedCalo())));
        caloRemain.setText(String.valueOf(pref.getInt("foodRemain", Calories.getInstance().calcRemain())));
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
        alarmMgr = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmResetFoodAdded.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 00);

        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    private void reset(int foodAdded) {
        Calories.getInstance().setAddedCalo(foodAdded);

        prefEditor.putInt("foodGoal", Calories.getInstance().maxCalo());
        prefEditor.putInt("foodAdded", foodAdded);
        prefEditor.putInt("foodRemain", Calories.getInstance().calcRemain());
        prefEditor.commit();
    }
}
