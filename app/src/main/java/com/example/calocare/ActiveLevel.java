package com.example.calocare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActiveLevel extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    boolean onClick= false;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_level);
        this.setTitle(R.string.active_level_title);

        button1= findViewById(R.id.button1);
        button2= findViewById(R.id.button2);
        button3= findViewById(R.id.button3);
        button4= findViewById(R.id.button4);


        button1.setOnClickListener(mListener);
        button2.setOnClickListener(mListener);
        button3.setOnClickListener(mListener);
        button4.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick( View view ) {
            int id = view.getId();
            if( id == R.id.button1 || id == R.id.button2 ||id == R.id.button3){
                if(!onClick) {
                    onClick=true;
                    boolean e = button4.isEnabled();
                    button4.setEnabled(!e);
                }
            }
        }
    };

    public void nextActivity(View v) {
        Intent nextActivity = new Intent(this, Goal.class);
        startActivity(nextActivity);
    }
}