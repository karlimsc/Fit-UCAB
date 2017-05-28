package com.fitucab.ds1617b.fitucab.UI.Fragments.M02;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class M02HomeFragment extends Fragment {

    private View _view;
    private TextView _tv_m02_home_calorias;
    private TextView _tv_m02_home_water;
    private TextView _tv_m02_home_peso;
    private ImageView _water;
    private ImageView _fitness;
    private ImageView _Account;
    private ImageView _Activitys;

    public M02HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view= inflater.inflate(R.layout.fragment_m02_home, container, false);
        initComponentes(_view);
        return _view;
    }

    private void initComponentes(View view) {
        _tv_m02_home_calorias = (TextView) view.findViewById(R.id.tv_m02_home_calorias);
        _tv_m02_home_water = (TextView) view.findViewById(R.id.tv_m02_home_water);
        _tv_m02_home_peso = (TextView) view.findViewById(R.id.tv_m02_home_peso);
        _water= (ImageView) view.findViewById(R.id.water);
        _fitness= (ImageView) view.findViewById(R.id.fitness);
        _Account = (ImageView) view.findViewById(R.id.Account);
        _Activitys= (ImageView) view.findViewById(R.id.Activitys);


        toAskWebService();


    }

    private void toAskWebService() {
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());

    }

}
