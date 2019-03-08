package com.example.calocare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import NonActivityClasses.AppControl;
import NonActivityClasses.Calories;
import NonActivityClasses.UserInfo;

public class GiaoDienChinh extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

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

        if(pref.getString("userName", "").equals("")) {
            toBasicInfo();
        }

        caloGoal = findViewById(R.id.tv_goal);
        caloAdded = findViewById(R.id.tv_food);
        caloRemain = findViewById(R.id.tv_remain);

        setUserValue();
        print();

    }

    @Override
    protected void onResume() {
        super.onResume();
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
        a = pref.getInt("foodAdded", 0);
        Calories.getInstance().setAddedCalo(a);

        caloGoal.setText(String.valueOf(/*pref.getInt("foodGoal",*/ Calories.getInstance().maxCalo()));
        caloAdded.setText(String.valueOf(pref.getInt("foodAdded", 0)));
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
}
