package com.example.calocare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FoodChoice extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_choice);
        listView = findViewById(R.id.list_food);

        FoodList.getInstance().add();
        listView.setAdapter(new ArrayAdapter<Food>(this, R.layout.listview_food, FoodList.getInstance().getFoodList()));
    }
}
