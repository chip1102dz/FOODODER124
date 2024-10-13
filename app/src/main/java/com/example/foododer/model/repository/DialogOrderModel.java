package com.example.foododer.model.repository;
import android.content.Context;

import androidx.databinding.ObservableList;

import com.example.foododer.ControllerApplication;
import com.example.foododer.R;
import com.example.foododer.constant.Constant;
import com.example.foododer.constant.GlobalFuntion;
import com.example.foododer.listener.ISendOrderSuccessListener;
import com.example.foododer.model.Food;
import com.example.foododer.model.Order;
import com.example.foododer.utils.StringUtil;
import com.example.foododer.utils.Utils;

public class DialogOrderModel {

    private Context mContext;

    public DialogOrderModel(Context context) {
        this.mContext = context;
    }

    public String getStringListFoodsOrder(ObservableList<Food> listFoodInCart) {
        if (listFoodInCart == null || listFoodInCart.isEmpty()) {
            return "";
        }
        String result = "";
        for (Food food : listFoodInCart) {
            if (StringUtil.isEmpty(result)) {
                result = "- " + food.getName() + " (" + food.getRealPrice() + Constant.CURRENCY + ") "
                        + "- " + mContext.getString(R.string.quantity) + " " + food.getCount();
            } else {
                result = result + "\n" + "- " + food.getName() + " (" + food.getRealPrice()
                        + Constant.CURRENCY + ") "
                        + "- " + mContext.getString(R.string.quantity) + " " + food.getCount();
            }
        }
        return result;
    }

    public void sendOrder(Context context,long id, Order order, ISendOrderSuccessListener iSendOrderSuccessListener) {
        ControllerApplication.get(mContext).getBookingDatabaseReference()
                .child(Utils.getDeviceId(mContext))
                .child(String.valueOf(id))
                .setValue(order, (error1, ref1) -> iSendOrderSuccessListener.sendOrderSuccess());
    }
}