package com.example.calocare;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import NonActivityClasses.AlarmReceiver;
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
    private int foodAdded;

    private static final int code1 = 1;
    private static final int code2 = 2;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chinh);

        pref = getSharedPreferences(AppControl.PREF, Activity.MODE_PRIVATE);
        prefEditor = pref.edit();

        caloGoal = findViewById(R.id.tv_goal);
        caloAdded = findViewById(R.id.tv_food);
        caloRemain = findViewById(R.id.tv_remain);

        if(pref.getInt("userGoalVal", 0) == 0) {
            toBasicInfo();
        } else {
            boolean alarmUp1 = (PendingIntent.getBroadcast(this, code1,
                    new Intent(this, AlarmReceiver.class),
                    PendingIntent.FLAG_NO_CREATE) != null);
            boolean alarmUp2 = (PendingIntent.getBroadcast(this, code2,
                    new Intent(this, AlarmReceiver.class),
                    PendingIntent.FLAG_NO_CREATE) != null);
            Log.d("myTag", alarmUp1 + " + " + alarmUp2);
            if (!(alarmUp1 || alarmUp2)) {
                setAlarm(true);
                setAlarm(false);
            }
            setUserValue();
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
        prefEditor.putInt("foodGoal", Integer.parseInt(caloGoal.getText().toString()));
        prefEditor.putInt("foodAdded", 0);
        prefEditor.putInt("foodRemain", Calories.getInstance().calcRemain());
        prefEditor.commit();
        print();
    }

    public void print(){
        foodAdded = pref.getInt("foodAdded", 0);
        Calories.getInstance().setAddedCalo(foodAdded);

        caloGoal.setText(String.valueOf(Calories.getInstance().maxCalo()));
        caloAdded.setText(String.valueOf(foodAdded));
        caloRemain.setText(String.valueOf(Calories.getInstance().calcRemain()));
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
