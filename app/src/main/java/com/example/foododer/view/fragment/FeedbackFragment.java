package com.example.foododer.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foododer.R;
import com.example.foododer.databinding.FragmentFeedbackBinding;
import com.example.foododer.viewmodel.FeedbackViewModel;
import com.example.foododer.view.BaseFragment;
import com.example.foododer.view.activity.MainActivity;

public class FeedbackFragment extends BaseFragment {

    private FeedbackViewModel feedbackViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentFeedbackBinding fragmentFeedbackBinding = FragmentFeedbackBinding.inflate(inflater, container, false);
        feedbackViewModel = new FeedbackViewModel();
        fragmentFeedbackBinding.setFeedbackModel(feedbackViewModel);

        return fragmentFeedbackBinding.getRoot();
    }

    @Override
    protected void initToolbar() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null)
            mainActivity.setToolBar(true, getString(R.string.feedback));
    }
}