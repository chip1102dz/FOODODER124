package com.example.foododer.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.example.foododer.ControllerApplication;
import com.example.foododer.constant.GlobalFuntion;
import com.example.foododer.model.Food;
import com.example.foododer.utils.StringUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class FoodModel {

    private Context mContext;

    public FoodModel(Context context) {
        this.mContext = context;
    }

    public void getListFoodFromFirebase(String key, ObservableList<Food> listFood, ObservableList<Food> listFoodPopular, OnDataLoadCallback callback) {
        if (mContext == null) {
            return;
        }

        ControllerApplication.get(mContext).getFoodDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Tạo một biến tạm thời cho danh sách thực phẩm
                        ObservableList<Food> tempListFood = new ObservableArrayList<>();

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Food food = dataSnapshot.getValue(Food.class);
                            if (food == null) {
                                return;
                            }

                            if (StringUtil.isEmpty(key)) {
                                tempListFood.add(0, food);
                            } else {
                                if (GlobalFuntion.getTextSearch(food.getName()).toLowerCase().trim()
                                        .contains(GlobalFuntion.getTextSearch(key).toLowerCase().trim())) {
                                    tempListFood.add(0, food);
                                }
                            }
                        }

                        // Cập nhật danh sách thực phẩm
                        final ObservableList<Food> finalListFood = listFood; // Biến tạm final
                        if (finalListFood != null) {
                            finalListFood.clear();
                            finalListFood.addAll(tempListFood);
                        }

                        getListFoodPopular(tempListFood, listFoodPopular);
                        callback.onDataLoaded();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Không làm gì trong trường hợp bị hủy
                    }
                });
    }

    private void getListFoodPopular(List<Food> listFood, ObservableList<Food> listFoodPopular) {
        if (listFoodPopular != null) {
            listFoodPopular.clear();
        } else {
            listFoodPopular = new ObservableArrayList<>();
        }
        if (listFood == null || listFood.isEmpty()) {
            return;
        }
        for (Food food : listFood) {
            if (food.isPopular()) {
                listFoodPopular.add(food);
            }
        }
    }

    public interface OnDataLoadCallback {
        void onDataLoaded();
    }
}
