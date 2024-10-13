package com.example.foododer.model.repository;


import android.app.Activity;

import com.example.foododer.database.FoodDatabase;
import com.example.foododer.model.Food;

public class DialogCartModel {

    private Activity mActivity;

    public DialogCartModel(Activity activity) {
        this.mActivity = activity;
    }

    public void addFoodToCart(Food food) {
        FoodDatabase.getInstance(mActivity).foodDAO().insertFood(food);
    }
}

