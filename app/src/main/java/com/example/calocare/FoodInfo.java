package com.example.calocare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import NonActivityClasses.Calories;
import NonActivityClasses.Food;
import NonActivityClasses.FoodList;
import NonActivityClasses.UserInfo;

public class FoodInfo extends AppCompatActivity {
    private Spinner spin;
    private String nutrionUnit = "mg";
    private Food selectedFood;
    private int a ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
        this.setTitle(R.string.food_info_title);

        Bundle b = getIntent().getExtras();
        int index = b.getInt("foodIndex", 0);
        selectedFood = FoodList.getInstance().getFood(index);

        ((TextView)findViewById(R.id.tv_foodName)).setText(selectedFood.toString());
        ((TextView)findViewById(R.id.tv_servSize)).setText(selectedFood.getServingSize());
        ((TextView)findViewById(R.id.tv_calories)).setText(selectedFood.getCalories() * a  + nutrionUnit);
        ((TextView)findViewById(R.id.tv_carbs)).setText(selectedFood.getCarbs() + nutrionUnit);
        ((TextView)findViewById(R.id.tv_protein)).setText(selectedFood.getProtein() + nutrionUnit);
        ((TextView)findViewById(R.id.tv_fat)).setText(selectedFood.getFat() + nutrionUnit);
        ((TextView)findViewById(R.id.tv_fiber)).setText(selectedFood.getFiber() + nutrionUnit);
        ((TextView)findViewById(R.id.tv_cholesterol)).setText(selectedFood.getCholesterol() + nutrionUnit);
        ((TextView)findViewById(R.id.tv_calcium)).setText(selectedFood.getCalcium() + nutrionUnit);
        //Lấy đối tượng Spinner ra
        spin = findViewById(R.id.numOfServ);
        //Gán Data source (arr) vào Adapter

        List<String> list = new ArrayList<>();
        for (int x = 1; x <= 100; x++) {
            list.add(String.valueOf(x));

        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);


        // Optional: Sau khi click xong thi lam gi tiep theo?  SaveReferrence or whatever
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Code is here;
                selectedFood.setNumOfServ(Integer.parseInt(adapterView.getItemAtPosition(i).toString()));
         }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Code is here
            }
        });
    }

    public void addFood(View v) {
        Calories.getInstance().calcServingCalo(selectedFood);

        Intent nextActivity = new Intent(this, GiaoDienChinh.class);
        startActivity(nextActivity);
    }
}
