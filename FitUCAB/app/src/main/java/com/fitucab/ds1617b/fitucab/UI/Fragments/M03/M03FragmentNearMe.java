package com.fitucab.ds1617b.fitucab.UI.Fragments.M03;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Helper.GPSTracker;
import com.fitucab.ds1617b.fitucab.R;

public class M03FragmentNearMe extends Fragment{

    TextView latitud;
    TextView longitud;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_m03_near_me, container, false);

        GPSTracker gps = new GPSTracker(rootView.getContext());
        if(gps.canGetLocation()){


            double dlat = gps.getLatitude();
            double dlong = gps.getLongitude();

            latitud = (TextView) rootView.findViewById(R.id.nearMeName);
            longitud = (TextView) rootView.findViewById(R.id.nearMePoints);
            latitud.setText(String.valueOf(dlat));
            longitud.setText(String.valueOf(dlong));
        }

        return rootView;
    }


}