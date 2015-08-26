package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;


    //region GETTERS & SETTERS

    public List<Crime> getCrimes() {
        return mCrimes;
    }
    //endregion


    //region STATIC METHODS
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }
    //endregion


    //CONSTRUCTOR
    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();

    }


    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id))
                return crime;
        }
        return null;
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }

}
