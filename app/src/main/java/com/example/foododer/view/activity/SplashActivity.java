package com.example.foododer.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;

import com.example.foododer.constant.GlobalFuntion;
import com.example.foododer.databinding.ActivitySplashBinding;
import com.example.foododer.view.BaseActivity;
import com.example.foododer.viewmodel.SplashViewModel;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding activitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        activitySplashBinding.setSplashViewModel(new SplashViewModel());
        setContentView(activitySplashBinding.getRoot());

        startMainActivity();
    }

    private void startMainActivity() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            GlobalFuntion.startActivity(SplashActivity.this, MainActivity.class);
            finish();
        }, 2000);
    }
}