package com.treatmentangel.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treatmentangel.R;
import com.treatmentangel.adapter.HospitalAdapter;
import com.treatmentangel.model.HospitalModel;
import com.treatmentangel.utils.Config;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Vikesh on 10-Feb-18.
 */

public class ListFragment extends Fragment {
    // newInstance constructor for creating fragment with arguments
    private RecyclerView recyclerView;
    private ArrayList<HospitalModel> hospitalList;
    private HospitalAdapter hospitalAdapter;
    public static ListFragment newInstance() {
        ListFragment fragmentFirst = new ListFragment();
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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        hospitalList = new ArrayList<>();
        hospitalAdapter = new HospitalAdapter(getActivity(), hospitalList);
        recyclerView.setAdapter(hospitalAdapter);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        for (int i = 0; i < Config.searchResultArray.length(); i++) {
            try {
                HospitalModel model = new HospitalModel();
                model.setId(((JSONObject)Config.searchResultArray.get(i)).getString("npiid"));
                model.setName(((JSONObject)Config.searchResultArray.get(i)).getString("org_name"));
                model.setDetail(((JSONObject)Config.searchResultArray.get(i)).getString("address1"));
                model.setRating("" + i);
                hospitalList.add(model);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
