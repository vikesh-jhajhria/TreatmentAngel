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
import com.treatmentangel.utils.AppPreferences;
import com.treatmentangel.utils.Config;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Vikesh on 10-Feb-18.
 */

public class BaseFragment extends Fragment {

    protected AppPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = AppPreferences.getAppPreferences(getActivity());
    }


}
