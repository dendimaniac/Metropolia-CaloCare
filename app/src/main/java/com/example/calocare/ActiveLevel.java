package com.example.calocare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class ActiveLevel extends AppCompatActivity {
    RadioButton r_button1;
    RadioButton r_button2;
    RadioButton r_button3;
    Button button4;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_level);

        r_button1= findViewById(R.id.notactive);
        r_button2= findViewById(R.id.slightlyactive);
        r_button3= findViewById(R.id.active);
        button4= findViewById(R.id.button4);

        r_button1.setOnCheckedChangeListener(mListener);
        r_button2.setOnCheckedChangeListener(mListener);
        r_button3.setOnCheckedChangeListener(mListener);
    }

    CompoundButton.OnCheckedChangeListener mListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void

        onCheckedChanged( CompoundButton buttonView, boolean isChecked ) {
            button4.setEnabled(true);
        }
    };
    public void nextActivity( View v) {
        Intent nextActivity = new Intent(this, Goal.class);
        startActivity(nextActivity);
    }
}