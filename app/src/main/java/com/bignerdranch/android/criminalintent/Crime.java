package com.bignerdranch.android.criminalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    //region GETTERS-SETTERS
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
    //endregion

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public void setDay(Date date) {
        Calendar crimeCalendar = Calendar.getInstance();
        crimeCalendar.setTime(mDate);

        Calendar newDate = Calendar.getInstance();
        newDate.setTime(date);

        //Set the TIME on the new date to the same time currently set
        newDate.set(Calendar.HOUR_OF_DAY, crimeCalendar.get(Calendar.HOUR_OF_DAY));
        newDate.set(Calendar.MINUTE, crimeCalendar.get(Calendar.MINUTE));

        mDate = newDate.getTime();
    }

    public void setTime(Date date) {
        Calendar crimeCalendar = Calendar.getInstance();
        crimeCalendar.setTime(mDate);

        Calendar newDate = Calendar.getInstance();
        newDate.setTime(date);

        //Set the DATE on the new date to the same day currently set
        newDate.set(Calendar.MONTH, crimeCalendar.get(Calendar.MONTH));
        newDate.set(Calendar.DAY_OF_MONTH, crimeCalendar.get(Calendar.DAY_OF_MONTH));
        newDate.set(Calendar.YEAR, crimeCalendar.get(Calendar.YEAR));

        mDate = newDate.getTime();
    }



}
