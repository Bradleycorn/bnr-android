package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class CrimeListFragment extends Fragment {
    private static final String TAG = "CrimeListFragment";

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private int mAdapterEditIndex = -1;

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Crime mCrime;

        private CheckBox mSolvedCheckBox;
        private TextView mTitleTextView;
        private TextView mDateTextView;

        public CrimeHolder(View itemView) {
            super(itemView);

            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_checkbox);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);

            itemView.setOnClickListener(this);
        }

        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(DateFormat.format("EEEE, MMMM d, yyyyy @ h:mma", mCrime.getDate()));
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View v) {
            mAdapterEditIndex = getAdapterPosition();
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }
    }


    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }


        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Log.d(TAG, "Crime List Fragment, OnCreateView. Calling updateUI()");
        //updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Crime List Fragment, onResume. Calling updateUI");

        updateUI();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Crime List Fragment Paused");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Crime List Fragment Destroyed");
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mAdapter == null) {
            Log.d(TAG, "In updateUI: Adapter is null, creating it now...");
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            if (mAdapterEditIndex >= 0) {
                Log.d(TAG, "In updateUI: Adapter exists, and an edit item index is set. Calling adapter.notifyItemChanged");
                mAdapter.notifyItemChanged(mAdapterEditIndex);
                mAdapterEditIndex = -1;
            } else {
                Log.d(TAG, "In updateUI: Adapter exists, but edit item index is -1. Calling adapter.notifyDataSetChanged");
                mAdapter.notifyDataSetChanged();
            }
        }

    }
}
