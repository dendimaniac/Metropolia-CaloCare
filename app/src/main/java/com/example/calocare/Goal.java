package com.example.calocare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class Goal extends AppCompatActivity {
    private Button next;
    private RadioGroup goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
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


    public void nextActivity(View v) {
        Intent nextActivity = new Intent(this, ActiveLevel.class);
        startActivity(nextActivity);
    }
}
