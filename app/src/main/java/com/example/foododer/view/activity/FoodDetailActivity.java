package com.example.foododer.view.activity;

import android.os.Bundle;

import com.example.foododer.constant.Constant;
import com.example.foododer.databinding.ActivityFoodDetailBinding;
import com.example.foododer.model.Food;
import com.example.foododer.view.BaseActivity;
import com.example.foododer.viewmodel.FoodDetailViewModel;


public class FoodDetailActivity extends BaseActivity {

    private FoodDetailViewModel mFoodDetailViewModel;
    private Food mFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFoodDetailBinding activityFoodDetailBinding = ActivityFoodDetailBinding.inflate(getLayoutInflater());
        setContentView(activityFoodDetailBinding.getRoot());

        getDataIntent();
        mFoodDetailViewModel = new FoodDetailViewModel(this, mFood);
        activityFoodDetailBinding.setFoodDetailViewModel(mFoodDetailViewModel);
    }

    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mFood = (Food) bundle.get(Constant.KEY_INTENT_FOOD_OBJECT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mFoodDetailViewModel != null) {
            mFoodDetailViewModel.release();
        }
    }
}