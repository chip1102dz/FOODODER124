package com.example.foododer.model.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.example.foododer.ControllerApplication;
import com.example.foododer.model.Order;
import com.example.foododer.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class OrderModel {

    private Context mContext;

    public OrderModel(Context context) {
        this.mContext = context;
    }

    public void getListOrders(ObservableList<Order> listOrder) {
        if (mContext == null) {
            return;
        }
        ControllerApplication.get(mContext).getBookingDatabaseReference().child(Utils.getDeviceId(mContext))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Tạo biến tạm để lưu danh sách
                        ObservableList<Order> tempListOrder = new ObservableArrayList<>();

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Order order = dataSnapshot.getValue(Order.class);
                            if (order != null) {
                                tempListOrder.add(0, order);
                            }
                        }

                        // Cập nhật danh sách đơn hàng
                        listOrder.clear();
                        listOrder.addAll(tempListOrder);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Không làm gì trong trường hợp bị hủy
                    }
                });
    }
}
