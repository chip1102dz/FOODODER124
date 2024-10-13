package com.example.foodorder.repository;

import android.content.Context;

import com.example.foododer.database.FoodDatabase;
import com.example.foododer.model.Food;

import java.util.List;


public class CartModel {
    private FoodDatabase foodDatabase;

    public CartModel(Context context) {
        foodDatabase = FoodDatabase.getInstance(context);
    }

    public List<Food> getListFoodInCart() {
        return foodDatabase.foodDAO().getListFoodCart();
    }

    public void deleteAllFood() {
        foodDatabase.foodDAO().deleteAllFood();
    }
}
