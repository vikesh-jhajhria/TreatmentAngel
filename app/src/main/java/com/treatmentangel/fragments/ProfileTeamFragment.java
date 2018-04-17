package com.treatmentangel.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treatmentangel.R;
import com.treatmentangel.adapter.ProfileTeamAdapter;
import com.treatmentangel.model.HospitalModel;

import java.util.ArrayList;

    /**
     * Created by Vikesh on 10-Feb-18.
     */

    public class ProfileTeamFragment extends Fragment {
        private RecyclerView recyclerView;
        private ArrayList<HospitalModel> teamList;
        private ProfileTeamAdapter teamAdapter;
        // newInstance constructor for creating fragment with arguments
        public static ProfileTeamFragment newInstance() {
            ProfileTeamFragment fragmentFirst = new ProfileTeamFragment();
            Bundle args = new Bundle();
            fragmentFirst.setArguments(args);
            return fragmentFirst;
        }

        // Store instance variables based on arguments passed
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public void onResume() {
            super.onResume();
            for (int i = 0; i < 20; i++) {
                HospitalModel model = new HospitalModel();
                model.setName("name_" + i);
                model.setDetail("Detail_" + i);
                model.setRating("" + i);
                teamList.add(model);
            }
        }

        // Inflate the view for the fragment based on layout XML
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_profile_team, container, false);
            recyclerView = view.findViewById(R.id.recyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            teamList = new ArrayList<>();
            teamAdapter = new ProfileTeamAdapter(getActivity(), teamList);
            recyclerView.setAdapter(teamAdapter);
            return view;
        }
    }

