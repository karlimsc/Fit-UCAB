package com.fitucab.ds1617b.fitucab.UI.Fragments.M05;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * Created by karo on 13/04/17.
 */

public class M05TrackActivityFragment extends Fragment {

    private OnFragmentSwap _callBack;

    public M05TrackActivityFragment (){
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("TRACKFRAGMENTCLASS","OnCreateView");
        View _view = inflater.inflate(R.layout.fragment_m05_track_activity, container, false);
        //TextView _textView = (TextView) _view.findViewById(R.id.trackText);

        return _view;
    }
}
