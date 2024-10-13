package com.example.foododer.model.repository;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.foododer.ControllerApplication;
import com.example.foododer.R;
import com.example.foododer.constant.GlobalFuntion;
import com.example.foododer.model.Feedback;
import com.example.foododer.utils.StringUtil;

public class FeedbackModel {

    public void sendFeedback(Context context, Feedback feedback) {
        long id = System.currentTimeMillis();
        ControllerApplication.get(context).getFeedbackDatabaseReference()
                .child(String.valueOf(id))
                .setValue(feedback, (error, ref) -> {
                    GlobalFuntion.hideSoftKeyboard((Activity) context);
                    GlobalFuntion.showToastMessage(context, context.getString(R.string.send_feedback_success));
                });
    }

    public boolean isValidFeedback(Feedback feedback, Context context) {
        if (StringUtil.isEmpty(feedback.getName())) {
            GlobalFuntion.showToastMessage(context, context.getString(R.string.name_require));
            return false;
        } else if (StringUtil.isEmpty(feedback.getComment())) {
            GlobalFuntion.showToastMessage(context, context.getString(R.string.comment_require));
            return false;
        }
        return true;
    }
}