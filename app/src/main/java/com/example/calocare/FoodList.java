package com.example.calocare;

import java.util.ArrayList;

public class FoodList {
    private static ArrayList<Food> foodList = new ArrayList<Food>();

    public static void add() {
        foodList.add(new Food("Beef",200,20,20,20,20,20,20));
        foodList.add(new Food("Pork",200,20,20,20,20,20,20));
        foodList.add(new Food("Chicken",200,20,20,20,20,20,20));
        foodList.add(new Food("Carrot",200,20,20,20,20,20,20));
        foodList.add(new Food("Salmon",200,20,20,20,20,20,20));
        foodList.add(new Food("Tuna",200,20,20,20,20,20,20));
        foodList.add(new Food("Rice",200,20,20,20,20,20,20));
    }
}
