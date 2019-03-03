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

public class GiaoDienChinh extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    private TextView caloGoal;
    private TextView caloAdded;
    private TextView caloRemain;

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
}
