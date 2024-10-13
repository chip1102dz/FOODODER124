package com.example.foododer.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foododer.R;
import com.example.foododer.adapter.ContactAdapter;
import com.example.foododer.constant.AboutUsConfig;
import com.example.foododer.model.Contact;
import com.example.foododer.model.repository.ContactModel;

public class ContactViewModel {

    private Context mContext;
    public ObservableList<Contact> listContacts = new ObservableArrayList<>();
    public ObservableField<String> aboutUsTitle = new ObservableField<>();
    public ObservableField<String> aboutUsContent = new ObservableField<>();
    public ObservableField<String> aboutUsWebsite = new ObservableField<>();
    private ContactModel contactModel;

    public ContactViewModel(Context mContext) {
        this.mContext = mContext;
        aboutUsTitle.set(AboutUsConfig.ABOUT_US_TITLE);
        aboutUsContent.set(AboutUsConfig.ABOUT_US_CONTENT);
        aboutUsWebsite.set(AboutUsConfig.ABOUT_US_WEBSITE_TITLE);
        contactModel = new ContactModel(mContext);
        getListContacts();
    }

    public void getListContacts() {
        listContacts = contactModel.getContacts();
    }

    @BindingAdapter({"list_data"})
    public static void loadListContacts(RecyclerView recyclerView, ObservableList<Contact> list) {
        GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        ContactAdapter contactAdapter = new ContactAdapter(list);
        recyclerView.setAdapter(contactAdapter);
    }

    public void onClickWebsite() {
        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(AboutUsConfig.WEBSITE)));
    }

    public void release() {
        mContext = null;
    }
}
