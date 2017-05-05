package com.fitucab.ds1617b.fitucab.UI.Fragments.M05;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by karo on 13/04/17.
 */

public class M05TrackActivityFragment extends Fragment implements OnMapReadyCallback {

    private OnFragmentSwap _callBack;
    private GoogleMap mMap;
    private static View _view;

    public M05TrackActivityFragment (){
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (_view != null) {
            ViewGroup parent = (ViewGroup) _view.getParent();
            if (parent != null)
                parent.removeView(_view);
        }
        try {
            _view = inflater.inflate(R.layout.fragment_m05_track_exercise, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        return _view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
