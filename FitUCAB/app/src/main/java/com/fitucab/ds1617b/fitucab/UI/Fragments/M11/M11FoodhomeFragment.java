package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class M11FoodhomeFragment extends Fragment {

    private ImageButton _btn_m11_diet;
    private ImageButton _btn_m11_suggestion;
    private ImageButton _btn_m11_graphic;
    private ImageButton _btn_m11_food;
    private TextView _tv_m11_cantidadCalorias;
    private View _view;


    public M11FoodhomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        _view = inflater.inflate(R.layout.fragment_m11_foodhome, container, false);

        _btn_m11_diet = (ImageButton)_view.findViewById(R.id.btn_m11_diet);
        _btn_m11_suggestion = (ImageButton)_view.findViewById(R.id.btn_m11_suggestion);
        _btn_m11_graphic = (ImageButton)_view.findViewById(R.id.btn_m11_graphic);
        _btn_m11_food = (ImageButton)_view.findViewById(R.id.btn_m11_food);
        _tv_m11_cantidadCalorias = (TextView)_view.findViewById(R.id.tv_m11_cantidadCalorias);


        return _view;
    }




}
