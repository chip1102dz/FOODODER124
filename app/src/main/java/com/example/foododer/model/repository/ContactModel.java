package com.example.foododer.model.repository;


import android.content.Context;

import com.example.foododer.R;
import com.example.foododer.model.Contact;
import com.example.foododer.constant.AboutUsConfig;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

public class ContactModel {

    private Context mContext;

    public ContactModel(Context context) {
        this.mContext = context;
    }

    public ObservableList<Contact> getContacts() {
        ObservableList<Contact> listContacts = new ObservableArrayList<>();
        listContacts.add(new Contact(Contact.FACEBOOK, R.drawable.ic_facebook, mContext.getString(R.string.label_facebook)));
        listContacts.add(new Contact(Contact.HOTLINE, R.drawable.ic_hotline, mContext.getString(R.string.label_call)));
        listContacts.add(new Contact(Contact.GMAIL, R.drawable.ic_gmail, mContext.getString(R.string.label_gmail)));
        listContacts.add(new Contact(Contact.SKYPE, R.drawable.ic_skype, mContext.getString(R.string.label_skype)));
        listContacts.add(new Contact(Contact.YOUTUBE, R.drawable.ic_youtube, mContext.getString(R.string.label_youtube)));
        listContacts.add(new Contact(Contact.ZALO, R.drawable.ic_zalo, mContext.getString(R.string.label_zalo)));
        return listContacts;
    }
}
