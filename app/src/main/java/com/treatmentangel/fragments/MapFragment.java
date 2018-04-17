package com.treatmentangel.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.treatmentangel.R;
import com.treatmentangel.utils.Config;

import org.json.JSONObject;

/**
 * Created by Vikesh on 10-Feb-18.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {
    // newInstance constructor for creating fragment with arguments
    private GoogleMap googleMap;
    MapView mMapView;

    public static MapFragment newInstance() {
        MapFragment fragmentFirst = new MapFragment();
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
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        mMapView = view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = null;
                for (int i = 0; i < Config.searchResultArray.length(); i++) {
                    try {
                        sydney = new LatLng(
                                Double.parseDouble(((JSONObject) Config.searchResultArray.get(i)).getString("latitude")),
                                Double.parseDouble(((JSONObject) Config.searchResultArray.get(i)).getString("longitude")));
                        googleMap.addMarker(new MarkerOptions().position(sydney).title(((JSONObject) Config.searchResultArray.get(i)).getString("org_name")));
                        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (sydney != null) {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                }
            }
        });
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.googleMap.setMyLocationEnabled(true);
        LatLng sydney = null;
        for (int i = 0; i < Config.searchResultArray.length(); i++) {
            try {
                sydney = new LatLng(
                        Double.parseDouble(((JSONObject) Config.searchResultArray.get(i)).getString("latitude")),
                        Double.parseDouble(((JSONObject) Config.searchResultArray.get(i)).getString("longitude")));
                googleMap.addMarker(new MarkerOptions().position(sydney).title(((JSONObject) Config.searchResultArray.get(i)).getString("org_name")));
                //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (sydney != null) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}
