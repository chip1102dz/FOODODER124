package com.example.foododer.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;


import com.example.foododer.utils.GlideUtils;

import java.io.Serializable;

public class Image implements Serializable {
    private String url;

    public Image() {
    }

    public Image(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @BindingAdapter({"url_image"})
    public static void loadImageFromUrl(ImageView imageView, String url) {
        GlideUtils.loadUrl(url, imageView);
    }
}
