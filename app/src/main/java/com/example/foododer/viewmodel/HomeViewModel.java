package com.example.foododer.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foododer.R;
import com.example.foododer.adapter.FoodGridAdapter;
import com.example.foododer.adapter.FoodPopularAdapter;
import com.example.foododer.constant.GlobalFuntion;
import com.example.foododer.model.Food;
import com.example.foododer.repository.FoodModel;
import com.example.foododer.utils.StringUtil;

import me.relex.circleindicator.CircleIndicator3;

public class HomeViewModel extends BaseObservable {

    private Context mContext;
    public ObservableList<Food> listFood = new ObservableArrayList<>();
    public ObservableList<Food> listFoodPopular = new ObservableArrayList<>();
    public ObservableBoolean isSuccess = new ObservableBoolean();
    public ObservableField<String> stringHint = new ObservableField<>();
    private FoodModel foodModel;

    public HomeViewModel(Context mContext) {
        this.mContext = mContext;
        this.foodModel = new FoodModel(mContext);
        getListFoodFromFirebase("");
    }

    public void getListFoodFromFirebase(String key) {
        foodModel.getListFoodFromFirebase(key, listFood, listFoodPopular, new FoodModel.OnDataLoadCallback() {
            @Override
            public void onDataLoaded() {
                isSuccess.set(true);
            }
        });
    }

    public ObservableField<String> getStringHint(EditText editText) {
        stringHint.set(mContext.getString(R.string.hint_search_name));

        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String keyword = editText.getText().toString();
                searchFood(keyword);
                return true;
            }
            return false;
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String strKey = s.toString().trim();
                if (StringUtil.isEmpty(strKey)) {
                    searchFood("");
                }
            }
        });
        return stringHint;
    }

    public void onClickButtonSearch(EditText editText) {
        String keyword = editText.getText().toString();
        searchFood(keyword);
    }

    private void searchFood(String key) {
        if (listFood != null) {
            listFood.clear();
        }
        getListFoodFromFirebase(key);
    }

    @BindingAdapter({"list_data"})
    public static void loadListFood(RecyclerView recyclerView, ObservableList<Food> list) {
        GlobalFuntion.hideSoftKeyboard((Activity) recyclerView.getContext());

        if (list == null) {
            GlobalFuntion.showToastMessage(recyclerView.getContext(),
                    recyclerView.getContext().getString(R.string.msg_get_date_error));
            return;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        FoodGridAdapter foodGridAdapter = new FoodGridAdapter(list);
        recyclerView.setAdapter(foodGridAdapter);
    }

    @BindingAdapter(value = {"list_data_popular", "indicator_viewpager"})
    public static void loadListFoodPopular(ViewPager2 viewPager2, ObservableList<Food> list, CircleIndicator3 indicator3) {
        FoodPopularAdapter foodPopularAdapter = new FoodPopularAdapter(list);
        viewPager2.setAdapter(foodPopularAdapter);

        Handler handlerBanner = new Handler(Looper.getMainLooper());
        Runnable runnableBanner = () -> {
            if (list == null || list.isEmpty()) {
                return;
            }
            if (viewPager2.getCurrentItem() == list.size() - 1) {
                viewPager2.setCurrentItem(0);
                return;
            }
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        };
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handlerBanner.removeCallbacks(runnableBanner);
                handlerBanner.postDelayed(runnableBanner, 3000);
            }
        });

        indicator3.setViewPager(viewPager2);
    }

    public void release() {
        mContext = null;
    }
}
