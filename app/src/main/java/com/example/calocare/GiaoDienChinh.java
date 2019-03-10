package com.example.calocare;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import NonActivityClasses.AlarmReceiver;
import NonActivityClasses.AppControl;
import NonActivityClasses.Calories;
import NonActivityClasses.UserInfo;

public class GiaoDienChinh extends AppCompatActivity {
    private SharedPreferences userPref, foodPref;
    private SharedPreferences.Editor userPrefEditor, foodPrefEditor;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    private TextView caloGoal;
    private TextView caloAdded;
    private TextView caloRemain;

    private UserInfo user = UserInfo.getInstance();

    private static final int code1 = 1;
    private static final int code2 = 2;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chinh);

        userPref = getSharedPreferences(AppControl.USER_PREF, Activity.MODE_PRIVATE);
        foodPref = getSharedPreferences(AppControl.FOOD_PREF, Activity.MODE_PRIVATE);
        userPrefEditor = userPref.edit();
        foodPrefEditor = foodPref.edit();

        caloGoal = findViewById(R.id.tv_goal);
        caloAdded = findViewById(R.id.tv_food);
        caloRemain = findViewById(R.id.tv_remain);

        if(userPref.getInt("userGoalVal", 0) == 0) {
            toBasicInfo();
        } else {
            boolean alarmUp1 = (PendingIntent.getBroadcast(this, code1,
                    new Intent(this, AlarmReceiver.class),
                    PendingIntent.FLAG_NO_CREATE) != null);
            boolean alarmUp2 = (PendingIntent.getBroadcast(this, code2,
                    new Intent(this, AlarmReceiver.class),
                    PendingIntent.FLAG_NO_CREATE) != null);
            if (!(alarmUp1 || alarmUp2)) {
                setAlarm(true);
                setAlarm(false);
            }
            setUserValue();
            print();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Integer.parseInt(caloGoal.getText().toString()) != 0) {
            foodPrefEditor.putInt("foodGoal", Integer.parseInt(caloGoal.getText().toString()));
            foodPrefEditor.putInt("foodAdded", Integer.parseInt(caloAdded.getText().toString()));
            foodPrefEditor.putInt("foodRemain", Integer.parseInt(caloRemain.getText().toString()));
            foodPrefEditor.commit();
        }
    }
    
    public void addFood(View v) {
        Intent nextActivity = new Intent(this, FoodChoice.class);
        startActivity(nextActivity);
    }

    public void editInfo(View v) {
        toBasicInfo();
    }

    public void print(){
        int foodAdded = foodPref.getInt("foodAdded", 0);
        Calories.getInstance().setAddedCalo(foodAdded);

        caloGoal.setText(String.valueOf(Calories.getInstance().maxCalo()));
        caloAdded.setText(String.valueOf(foodAdded));
        caloRemain.setText(String.valueOf(Calories.getInstance().calcRemain()));
    }

    private void setUserValue() {
        user.setAge(userPref.getInt("userAge", 0));
        user.setGender(userPref.getString("userGenderText", ""));
        user.setHeight(userPref.getInt("userHeight", 0));
        user.setWeight(userPref.getInt("userWeight", 0));
        user.setActiveStatus(userPref.getFloat("userActiveVal", 0));
        user.setGoalStatus(userPref.getInt("userGoalVal", 0));
    }

    private void toBasicInfo() {
        Intent nextActivity = new Intent(this, BasicInfo.class);
        startActivity(nextActivity);
    }

    public void setAlarm(boolean isMidnight) {
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("isMidnight", isMidnight);
        alarmIntent = PendingIntent.getBroadcast(this, isMidnight ? code1 : code2, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DATE, isMidnight ? 1 : 0);
        calendar.set(Calendar.HOUR_OF_DAY, isMidnight ? 0 : 22);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        alarmMgr = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
    }
}
