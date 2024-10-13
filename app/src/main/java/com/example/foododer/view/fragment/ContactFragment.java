package com.example.foododer.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foododer.databinding.FragmentContactBinding;
import com.example.foododer.view.BaseFragment;
import com.example.foododer.view.activity.MainActivity;
import com.example.foododer.viewmodel.ContactViewModel;
import com.example.foododer.R;

public class ContactFragment extends BaseFragment {

    private ContactViewModel mContactViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentContactBinding fragmentContactBinding = FragmentContactBinding.inflate(inflater, container, false);

        mContactViewModel = new ContactViewModel(getActivity());
        fragmentContactBinding.setContactViewModel(mContactViewModel);

        return fragmentContactBinding.getRoot();
    }

    @Override
    protected void initToolbar() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null)
            mainActivity.setToolBar(true, getString(R.string.contact));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mContactViewModel != null) {
            mContactViewModel.release();
        }
    }
}
