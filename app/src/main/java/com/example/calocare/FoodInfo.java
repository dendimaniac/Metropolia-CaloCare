package com.example.calocare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FoodInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
        this.setTitle(R.string.food_info_title);
    }
}
