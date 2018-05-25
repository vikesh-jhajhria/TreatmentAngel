package com.treatmentangel;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.treatmentangel.model.SearchModel;
import com.treatmentangel.utils.APIClient;
import com.treatmentangel.utils.ApiInterface;
import com.treatmentangel.utils.AppPreferences;
import com.treatmentangel.utils.Config;
import com.treatmentangel.utils.HTTPUrlConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_spinner_item;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    Spinner spinner;
    double lat = 0, lng = 0;
    AutoCompleteTextView autocompleteView;
    private static final String API_KEY = "AIzaSyCs7y9plmgR49eaBqe1D7hj5ezYzEk08rs";

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.navigation_home:

                return true;
            case R.id.navigation_login:
                if (AppPreferences.getAppPreferences(getApplicationContext()).getUserData().isEmpty()) {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                } else {
                    logout();
                }
                return true;
            case R.id.navigation_notifications:
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
                return true;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        autocompleteView = (AutoCompleteTextView) findViewById(R.id.location);
        autocompleteView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.layout_autocomplete));
        autocompleteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get data associated with the specified position
                // in the list (AdapterView)
                String description = (String) parent.getItemAtPosition(position);
                getLocationInfo(description);
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        if (AppPreferences.getAppPreferences(getApplicationContext()).getUserData().isEmpty()) {
            ((MenuItem) navigationView.getMenu().findItem(R.id.navigation_login)).setTitle("Login");
        } else {
            ((MenuItem) navigationView.getMenu().findItem(R.id.navigation_login)).setTitle("Logout");
        }

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //startActivity(new Intent(HomeActivity.this,DoctorsActivity.class));
                String treatment = "", speciality;

                if (spinner != null) {
                    switch (spinner.getSelectedItemPosition()) {
                        case 1:
                            treatment = "psychology";
                            break;
                        case 2:
                            treatment = "dual_diagnosis";
                            break;
                        default:
                            treatment = "addiction";
                    }
                }
                speciality = ((EditText) findViewById(R.id.speciality)).getText().toString().trim();
                if (treatment != null && !treatment.isEmpty()) {
                    if (speciality != null && !speciality.isEmpty()) {
                        if (lat != 0 && lng != 0) {
                            //search(treatment, speciality, 34.009009, -118.271954);
                            search(treatment, speciality, lat, lng);
                        } else {
                            Toast.makeText(HomeActivity.this, "Please select a valid location", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(HomeActivity.this, "Please enter speciality", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(HomeActivity.this, "Please select treatment type", Toast.LENGTH_SHORT).show();
                }

            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Addiction Treatment");
        list.add("Mental Health Counselor");
        list.add("Dual Diagnosis Center");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (this, simple_spinner_item, list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }


    private void logout() {
        AppPreferences.getAppPreferences(getApplicationContext()).setUserData("");

        ((MenuItem) navigationView.getMenu().findItem(R.id.navigation_login)).setTitle("Login");
    }

    private void search(final String treatment, final String speciality, final double lat, final double lng) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProgessDialog();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> map = new HashMap<>();
                map.put("treatment_type", treatment);
                map.put("specialty", speciality);
                map.put("latitute", lat + "");
                map.put("longitute", lng + "");
                return HTTPUrlConnection.getInstance().loadPost(Config.BASE_URL + "api", map);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                dismissProgressDialog();
                try {
                    Config.searchResultArray = new JSONArray(result);
                    startActivity(new Intent(HomeActivity.this, DoctorsActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute();
    }


    ////////////////////

    public Void getLocationInfo(String address) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProgessDialog();
            }

            @Override
            protected Void doInBackground(String... add) {
                String str = add[0];
                String address = str.replaceAll(" ", "%20");
                HttpURLConnection conn = null;
                StringBuilder jsonResults = new StringBuilder();
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    StringBuilder sb = new StringBuilder("https://maps.google.com/maps/api/geocode/json");
                    sb.append("?address=" + URLEncoder.encode(address, "utf8"));
                    sb.append("&key="+API_KEY);
                    sb.append("&sensor=false");

                    URL url = new URL(sb.toString());
                    conn = (HttpURLConnection) url.openConnection();
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());

                    // Load the results into a StringBuilder
                    int read;
                    char[] buff = new char[1024];
                    while ((read = in.read(buff)) != -1) {
                        jsonResults.append(buff, 0, read);
                    }
                    JSONObject jsonObj = new JSONObject(jsonResults.toString());


                    try {

                        double longitude = ((JSONArray) jsonObj.get("results")).getJSONObject(0)
                                .getJSONObject("geometry").getJSONObject("location")
                                .getDouble("lng");

                        double latitude = ((JSONArray) jsonObj.get("results")).getJSONObject(0)
                                .getJSONObject("geometry").getJSONObject("location")
                                .getDouble("lat");

                        Log.v("LATLONG", latitude + "," + longitude);

                        lat = latitude;
                        lng = longitude;
                        //return new LatLng(latitude, longitude);
                    } catch (JSONException e) {
                        return null;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        dismissProgressDialog();
                        conn.disconnect();
                    }
                }
                return null;
            }
        }.execute(address);


        return null;
    }


    public class PlaceAPI {

        private final String TAG = PlaceAPI.class.getSimpleName();

        private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
        private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
        private static final String OUT_JSON = "/json";



        public ArrayList<String> autocomplete(String input) {
            ArrayList<String> resultList = null;

            HttpURLConnection conn = null;
            StringBuilder jsonResults = new StringBuilder();

            try {
                StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
                sb.append("?key=" + API_KEY);
                sb.append("&input=" + URLEncoder.encode(input, "utf8"));
                //StringBuilder sb = new StringBuilder("https://maps.google.com/maps/api/geocode/json?address=Redeye%20Grill,%207th%20Avenue,%20New%20York,%20NY,%20USA&sensor=false");

                URL url = new URL(sb.toString());
                conn = (HttpURLConnection) url.openConnection();
                InputStreamReader in = new InputStreamReader(conn.getInputStream());

                // Load the results into a StringBuilder
                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
                in.close();
            } catch (MalformedURLException e) {
                Log.e(TAG, "Error processing Places API URL", e);
                return resultList;
            } catch (IOException e) {
                Log.e(TAG, "Error connecting to Places API", e);
                return resultList;
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }

            try {
                // Log.d(TAG, jsonResults.toString());

                // Create a JSON object hierarchy from the results
                JSONObject jsonObj = new JSONObject(jsonResults.toString());
                JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

                // Extract the Place descriptions from the results
                resultList = new ArrayList<String>(predsJsonArray.length());
                for (int i = 0; i < predsJsonArray.length(); i++) {
                    resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
                }
            } catch (JSONException e) {
                Log.e(TAG, "Cannot process JSON results", e);
            }

            return resultList;
        }


    }


    class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

        ArrayList<String> resultList;

        Context mContext;
        int mResource;

        PlaceAPI mPlaceAPI = new PlaceAPI();

        public PlacesAutoCompleteAdapter(Context context, int resource) {
            super(context, resource);

            mContext = context;
            mResource = resource;
        }

        @Override
        public int getCount() {
            // Last item will be the footer
            return resultList.size();
        }

        @Override
        public String getItem(int position) {
            return resultList.get(position);
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        resultList = mPlaceAPI.autocomplete(constraint.toString());

                        filterResults.values = resultList;
                        filterResults.count = resultList.size();
                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };

            return filter;
        }
    }
}
