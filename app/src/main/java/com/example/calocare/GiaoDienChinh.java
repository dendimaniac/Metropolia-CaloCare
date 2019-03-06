package com.example.calocare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        caloGoal = findViewById(R.id.tv_goal);
        caloAdded = findViewById(R.id.tv_food);
        caloRemain = findViewById(R.id.tv_remain);

        setUserValue();
    }

    @Override
    protected void onResume() {
        super.onResume();
        print();
    }

    @Override
    protected void onPause() {
        super.onPause();
        prefEditor.putInt("foodGoal", Integer.parseInt(caloGoal.getText().toString()));
        prefEditor.putInt("foodAdded", Integer.parseInt(caloAdded.getText().toString()));
        prefEditor.putInt("foodRemain", Integer.parseInt(caloRemain.getText().toString()));
        prefEditor.commit();
    }

    public void toAdd(View v) {
        Intent nextActivity = new Intent(this, FoodChoice.class);
        startActivity(nextActivity);
    }
    public void toReset(View v) {
        Calories.getInstance().reset();
        print();
    }
    public void print(){
        caloGoal.setText(String.valueOf(Calories.getInstance().maxCalo()));
        caloAdded.setText(String.valueOf(Calories.getInstance().getAddedCalo()));
        caloRemain.setText(String.valueOf(Calories.getInstance().calcRemain()));
    }

    private void setUserValue() {
        String getText1, getText2;
        double active = 0;
        int goal = 0;

        user.setAge(pref.getInt("userAge", 0));
        user.setGender(pref.getString("userGenderText", ""));
        user.setHeight(pref.getInt("userHeight", 0));
        user.setWeight(pref.getInt("userWeight", 0));


        user.setActiveStatus(pref.getFloat("userActiveVal", 0));

        /*getText2 = pref.getString("userGoalText", "");
        if (getText2.equals(R.string.txt_lose)) {
            goal = 1;
        } else if (getText2.equals(R.string.txt_maintain)) {
            goal = 2;
        } else if (getText2.equals(R.string.txt_gain)) {
            goal = 3;
        }
        getText1 = pref.getString("userActiveText", "");
        if (getText1.equals(R.string.rDbtn_active)) {
            active = 1.9;
        } else if (getText1.equals(R.string.rDbtn_slightlyActive)) {
            active = 1.55;
        } else if (getText1.equals(R.string.rDbtn_notActive)) {
            active = 1.2;
        }*/
        user.setGoalStatus(pref.getInt("userGoalVal", 0));
    }
}
