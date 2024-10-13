package com.example.foododer.viewmodel;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.example.foododer.constant.Constant;
import com.example.foododer.listener.IAddToCartSuccessListener;
import com.example.foododer.model.Food;
import com.example.foododer.model.repository.DialogCartModel;
import com.example.foododer.utils.GlideUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DialogCartViewModel {

    private Activity mActivity;
    private BottomSheetDialog mBottomSheetDialog;
    public Food mFood;
    public ObservableField<String> strTotalPrice = new ObservableField<>();
    private final IAddToCartSuccessListener iAddToCartSuccessListener;
    private DialogCartModel dialogCartModel;

    public DialogCartViewModel(Activity mActivity, BottomSheetDialog mBottomSheetDialog,
                               Food mFood, IAddToCartSuccessListener listener) {
        this.mActivity = mActivity;
        this.mBottomSheetDialog = mBottomSheetDialog;
        this.mFood = mFood;
        this.iAddToCartSuccessListener = listener;
        this.dialogCartModel = new DialogCartModel(mActivity);

        initData();
    }

    private void initData() {
        int totalPrice = mFood.getRealPrice();
        strTotalPrice.set(totalPrice + Constant.CURRENCY);

        mFood.setCount(1);
        mFood.setTotalPrice(totalPrice);
    }

    public void onClickSubtractCount(TextView tvCount) {
        int count = Integer.parseInt(tvCount.getText().toString());
        if (count <= 1) {
            return;
        }
        int newCount = Integer.parseInt(tvCount.getText().toString()) - 1;
        tvCount.setText(String.valueOf(newCount));

        int totalPrice1 = mFood.getRealPrice() * newCount;
        String strTotalPrice1 = totalPrice1 + Constant.CURRENCY;
        strTotalPrice.set(strTotalPrice1);

        mFood.setCount(newCount);
        mFood.setTotalPrice(totalPrice1);
    }

    public void onClickAddCount(TextView tvCount) {
        int newCount = Integer.parseInt(tvCount.getText().toString()) + 1;
        tvCount.setText(String.valueOf(newCount));

        int totalPrice2 = mFood.getRealPrice() * newCount;
        String strTotalPrice2 = totalPrice2 + Constant.CURRENCY;
        strTotalPrice.set(strTotalPrice2);

        mFood.setCount(newCount);
        mFood.setTotalPrice(totalPrice2);
    }

    public void onClickCancel() {
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }
    }

    public void onClickAddToCart() {
        dialogCartModel.addFoodToCart(mFood);
        iAddToCartSuccessListener.addToCartSuccess();
    }

    @BindingAdapter({"url_image"})
    public static void loadImageFromUrl(ImageView imageView, String url) {
        GlideUtils.loadUrl(url, imageView);
    }

    public void release() {
        mActivity = null;
        mBottomSheetDialog = null;
    }
}
