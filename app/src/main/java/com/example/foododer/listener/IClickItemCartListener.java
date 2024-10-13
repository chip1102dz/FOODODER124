package com.example.foododer.listener;

import android.content.Context;

import com.example.foododer.model.Food;


public interface IClickItemCartListener {
    void clickDeteteFood(Context context, Food food, int position);
    void updateItemFood(Context context, Food food, int position);
}
