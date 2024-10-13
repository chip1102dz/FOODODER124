package com.example.foododer.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foododer.R;
import com.example.foododer.adapter.OrderAdapter;
import com.example.foododer.constant.GlobalFuntion;
import com.example.foododer.model.Order;
import com.example.foododer.model.repository.OrderModel;

public class OrderViewModel {

    private Context mContext;
    public ObservableList<Order> listOrder = new ObservableArrayList<>();
    private OrderModel orderModel;

    public OrderViewModel(Context mContext) {
        this.mContext = mContext;
        orderModel = new OrderModel(mContext);
        getListOrders();
    }

    private void getListOrders() {
        orderModel.getListOrders(listOrder);
    }

    @BindingAdapter({"list_data"})
    public static void loadListOrder(RecyclerView recyclerView, ObservableList<Order> list) {
        if (list == null) {
            GlobalFuntion.showToastMessage(recyclerView.getContext(),
                    recyclerView.getContext().getString(R.string.msg_get_date_error));
            return;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        OrderAdapter orderAdapter = new OrderAdapter(list);
        recyclerView.setAdapter(orderAdapter);
    }

    public void release() {
        mContext = null;
    }
}
