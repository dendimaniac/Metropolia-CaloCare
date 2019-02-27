package com.example.calocare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import NonActivityClasses.AppControl;
import NonActivityClasses.UserInfo;

public class Goal extends AppCompatActivity {
    private Button next;
    private RadioGroup goal;
    private SharedPreferences pref;
    private SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        this.setTitle(R.string.goal_title);

        goal = findViewById(R.id.goal);
        next = findViewById(R.id.next);

        next.setEnabled(false);
        goal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                next.setEnabled(true);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        int checkId = pref.getInt("userGoal", -1);

        if (checkId == -1) {
            goal.clearCheck();
        } else {
            goal.check(checkId);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();

        int selectedId = goal.getCheckedRadioButtonId();
        int a = 0;


        if (selectedId != -1) {
            RadioButton selectedGoal = findViewById(selectedId);


            if(selectedGoal.getText().toString().equals("Gain weight")){
                a =1;
            }
            if (selectedGoal.getText().toString().equals("Maintain weight")){
                a =2;
            }
            if (selectedGoal.getText().toString().equals("Lose weight")){
                a =3;
            }

            UserInfo.getInstance().setGoalStatus(a);
        }

        prefEditor.putInt("userGoal", selectedId);
        prefEditor.commit();
    }
}
