package com.example.calocare;

import java.util.ArrayList;
import java.util.List;

public class FoodList {
    private List<Food> foodList;
    private static final FoodList listInstance = new FoodList();

    public static FoodList getInstance() {
        return listInstance;
    }

    public void add() {
        foodList = new ArrayList<Food>();
        foodList.add(new Food("Beef",200,20,20,20,20,20,20));
        foodList.add(new Food("Pork",200,20,20,20,20,20,20));
        foodList.add(new Food("Chicken",200,20,20,20,20,20,20));
        foodList.add(new Food("Carrot",200,20,20,20,20,20,20));
        foodList.add(new Food("Salmon",200,20,20,20,20,20,20));
        foodList.add(new Food("Tuna",200,20,20,20,20,20,20));
        foodList.add(new Food("Rice",200,20,20,20,20,20,20));
        foodList.add(new Food("Beef",200,20,20,20,20,20,20));
        foodList.add(new Food("Pork",200,20,20,20,20,20,20));
        foodList.add(new Food("Chicken",200,20,20,20,20,20,20));
        foodList.add(new Food("Carrot",200,20,20,20,20,20,20));
        foodList.add(new Food("Salmon",200,20,20,20,20,20,20));
        foodList.add(new Food("Tuna",200,20,20,20,20,20,20));
        foodList.add(new Food("Rice",200,20,20,20,20,20,20));
        foodList.add(new Food("Beef",200,20,20,20,20,20,20));
        foodList.add(new Food("Pork",200,20,20,20,20,20,20));
        foodList.add(new Food("Chicken",200,20,20,20,20,20,20));
        foodList.add(new Food("Carrot",200,20,20,20,20,20,20));
        foodList.add(new Food("Salmon",200,20,20,20,20,20,20));
        foodList.add(new Food("Tuna",200,20,20,20,20,20,20));
        foodList.add(new Food("Rice",200,20,20,20,20,20,20));
    }

    public List<Food> getFoodList() {
        return this.foodList;
    }

    public Food getFood(int index) {
        return this.foodList.get(index);
    }
}
