package com.treatmentangel.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.treatmentangel.BaseActivity;
import com.treatmentangel.FullProfileActivity;
import com.treatmentangel.LoginActivity;
import com.treatmentangel.R;
import com.treatmentangel.utils.AppPreferences;
import com.treatmentangel.utils.Config;
import com.treatmentangel.utils.HTTPUrlConnection;
import com.treatmentangel.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Vikesh on 10-Feb-18.
 */

public class ProfileOverviewFragment extends BaseFragment {
    // newInstance constructor for creating fragment with arguments
    boolean isFav = false;
    View view;

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
        view = inflater.inflate(R.layout.fragment_profile_overview, container, false);

        view.findViewById(R.id.img_fav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!AppPreferences.getAppPreferences(getActivity()).getUserData().isEmpty()) {
                    if (!isFav) {
                        markFavourite(true);
                    } else {
                        markFavourite(false);
                    }
                } else {
                    Utils.showDecisionDialog(getActivity(), "Alert!", "Please login first", new Utils.AlertCallback() {
                        @Override
                        public void callback() {
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }
                    });
                }
            }
        });

        return view;
    }


    private void markFavourite(boolean fav) {
        String user_id = "";
        String listing_id = ((FullProfileActivity) getActivity()).userId;
        String status = fav ? "1" : "0";
        try {
            user_id = preferences.getUserDataObject().getString("user_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new AsyncTask<String, Void, String>() {

            boolean fav;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> map = new HashMap<>();
                fav = params[2].equals("1") ? true : false;
                map.put("user_id", params[0]);
                map.put("listing_id", params[1]);
                map.put("fav_status", params[2]);
                return HTTPUrlConnection.getInstance().loadPost(Config.BASE_URL + "api/add_fav_list", map);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getString("status_id").equalsIgnoreCase("1")) {
                        isFav = fav;
                        if (fav) {
                            ((ImageView) view.findViewById(R.id.img_fav)).setImageResource(R.drawable.ic_favorite_selected);
                        } else {
                            ((ImageView) view.findViewById(R.id.img_fav)).setImageResource(R.drawable.ic_favorite_unselected);
                        }
                    } else {
                        Toast.makeText(getActivity(), obj.getString("status_msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute(user_id, listing_id, status);
    }
}
