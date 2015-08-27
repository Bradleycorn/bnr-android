package com.bignerdranch.android.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.criminalintent.Crime;
import com.bignerdranch.android.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(getString(getColumnIndex(CrimeTable.Cols.TITLE)));
        crime.setDate(new Date(getLong(getColumnIndex(CrimeTable.Cols.DATE))));
        crime.setSolved((getInt(getColumnIndex(CrimeTable.Cols.SOLVED)) == 1));

        return crime;
    }

}
