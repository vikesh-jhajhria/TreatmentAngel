package com.treatmentangel.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treatmentangel.R;

/**
 * Created by Vikesh on 10-Feb-18.
 */

public class ProfileOverviewFragment extends Fragment {
    // newInstance constructor for creating fragment with arguments
    public static ProfileOverviewFragment newInstance() {
        ProfileOverviewFragment fragmentFirst = new ProfileOverviewFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_overview, container, false);

        return view;
    }
}
