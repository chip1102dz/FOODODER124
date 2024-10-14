package com.example.foododer.viewmodel;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.example.foododer.ControllerApplication;
import com.example.foododer.R;
import com.example.foododer.constant.Constant;
import com.example.foododer.constant.GlobalFuntion;
import com.example.foododer.listener.ISendOrderSuccessListener;
import com.example.foododer.model.Food;
import com.example.foododer.model.Order;
import com.example.foododer.model.repository.DialogOrderModel;
import com.example.foododer.utils.StringUtil;
import com.example.foododer.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DialogOrderViewModel {

    private Context mContext;
    private BottomSheetDialog mBottomSheetDialog;
    private final ObservableList<Food> listFoodInCart;
    public String strTotalPrice;
    public ObservableField<String> strName = new ObservableField<>();
    public ObservableField<String> strAddress = new ObservableField<>();
    public ObservableField<String> strPhone = new ObservableField<>();
    private final int mAmount;
    private final ISendOrderSuccessListener iSendOrderSuccessListener;
    private DialogOrderModel dialogOrderModel;

    public DialogOrderViewModel(Context context, BottomSheetDialog mBottomSheetDialog,
                                ObservableList<Food> list, String total,
                                int amount, ISendOrderSuccessListener listener) {
        this.mContext = context;
        this.mBottomSheetDialog = mBottomSheetDialog;
        this.listFoodInCart = list;
        this.strTotalPrice = total;
        this.mAmount = amount;
        this.iSendOrderSuccessListener = listener;
        this.dialogOrderModel = new DialogOrderModel(context);
    }

    public void release() {
        mContext = null;
        mBottomSheetDialog = null;
    }

    public String getStringListFoodsOrder() {
        return dialogOrderModel.getStringListFoodsOrder(listFoodInCart);
    }

    public void onClickCancel() {
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }
    }

    public void onClickSendOrder() {
        String name = strName.get();
        String phone = strPhone.get();
        String address = strAddress.get();

        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(phone) || StringUtil.isEmpty(address)) {
            GlobalFuntion.showToastMessage(mContext, mContext.getString(R.string.message_enter_infor_order));
        } else {
            long id = System.currentTimeMillis();
            Order order = new Order(id, name, phone, address,
                    mAmount, getStringListFoodsOrder(), Constant.TYPE_PAYMENT_CASH);
            GlobalFuntion.showToastMessage(mContext, mContext.getString(R.string.msg_order_success));
            dialogOrderModel.sendOrder(mContext,id, order, iSendOrderSuccessListener);
        }
    }
}
