package com.treatmentangel;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.treatmentangel.adapter.FavouriteAdapter;
import com.treatmentangel.adapter.HospitalAdapter;
import com.treatmentangel.model.FavouriteModel;
import com.treatmentangel.utils.Config;
import com.treatmentangel.utils.HTTPUrlConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FavouriteActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ArrayList<FavouriteModel> favouriteModels;
    private FavouriteAdapter favouriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.back).setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        favouriteModels = new ArrayList<>();
        favouriteAdapter = new FavouriteAdapter(this, favouriteModels);
        recyclerView.setAdapter(favouriteAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String loginUserId = "";
        try {
            loginUserId = preferences.getUserDataObject().getString("user_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getFavourite(loginUserId);
    }


    private void getFavourite(String user_id) {
        new AsyncTask<String, Void, String>() {

            boolean fav;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProgessDialog();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> map = new HashMap<>();
                map.put("user_id", params[0]);
                return HTTPUrlConnection.getInstance().loadPost(Config.BASE_URL + "api/fav_list", map);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                try {
                    dismissProgressDialog();
                    JSONObject obj = new JSONObject(result);
                    if (obj.getString("status_id").equalsIgnoreCase("1")) {
                        JSONArray array = obj.getJSONArray("fav_data");
                        favouriteModels.clear();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = (JSONObject) array.get(i);
                            FavouriteModel model = new FavouriteModel();
                            model.setCrdt_date_time(object.getString("crdt_date_time"));
                            model.setFav_to_user_id(object.getString("fav_to_user_id"));
                            model.setUser_address_city(object.getString("user_address_city"));
                            model.setUser_address_street1(object.getString("user_address_street1"));
                            model.setUser_cell_phone_no(object.getString("user_cell_phone_no"));
                            model.setUser_screen_name(object.getString("user_screen_name"));
                            model.setUser_home_phone_no(object.getString("user_home_phone_no"));

                            favouriteModels.add(model);
                        }

                        favouriteAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(FavouriteActivity.this, obj.getString("status_msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute(user_id);
    }
}
