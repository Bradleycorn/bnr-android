package com.bignerdranch.android.criminalintent;

import java.util.UUID;

public class Crime {

    private UUID mId;
    private String mTitle;

    //region GETTERS-SETTERS

    public Crime() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    //endregion

    public void setTitle(String title) {
        mTitle = title;
    }



}
