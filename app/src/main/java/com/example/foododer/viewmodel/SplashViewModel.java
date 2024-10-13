package com.example.foododer.viewmodel;

import androidx.databinding.ObservableField;

import com.example.foododer.constant.AboutUsConfig;


public class SplashViewModel {

    public ObservableField<String> aboutUsTitle = new ObservableField<>();
    public ObservableField<String> aboutUsSlogan = new ObservableField<>();

    public SplashViewModel() {
        aboutUsTitle.set(AboutUsConfig.ABOUT_US_TITLE);
        aboutUsSlogan.set(AboutUsConfig.ABOUT_US_SLOGAN);
    }
}
