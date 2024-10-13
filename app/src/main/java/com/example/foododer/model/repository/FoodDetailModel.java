package com.example.foododer.model.repository;

import android.app.Activity;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.foododer.R;
import com.example.foododer.constant.Constant;
import com.example.foododer.database.FoodDatabase;
import com.example.foododer.model.Food;
import com.example.foododer.model.Image;

import java.util.List;

public class FoodDetailModel {

    private Activity mActivity;

    public FoodDetailModel(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public List<Food> checkFoodInCart(int foodId) {
        return FoodDatabase.getInstance(mActivity).foodDAO().checkFoodInCart(foodId);
    }

    public void release() {
        mActivity = null;
    }
}
