package com.example.foododer.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.foododer.BR;
import com.example.foododer.model.Feedback;
import com.example.foododer.model.repository.FeedbackModel;

public class FeedbackViewModel extends BaseObservable {

    // Sử dụng ObservableField thay cho Feedback
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> comment = new ObservableField<>();

    private FeedbackModel feedbackModel;

    public FeedbackViewModel() {
        this.feedbackModel = new FeedbackModel();
    }

    @Bindable
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
        notifyPropertyChanged(BR.comment);
    }

    public void clickSendFeedback(View view) {
        Context context = view.getContext();
        // Tạo đối tượng Feedback trực tiếp từ các ObservableField
        Feedback feedback = new Feedback();
        feedback.setName(getName());
        feedback.setPhone(getPhone());
        feedback.setEmail(getEmail());
        feedback.setComment(getComment());

        if (feedbackModel.isValidFeedback(feedback, context)) {
            feedbackModel.sendFeedback(context, feedback);
            // Reset fields after sending
            setName("");
            setPhone("");
            setEmail("");
            setComment("");
        }
    }
}
