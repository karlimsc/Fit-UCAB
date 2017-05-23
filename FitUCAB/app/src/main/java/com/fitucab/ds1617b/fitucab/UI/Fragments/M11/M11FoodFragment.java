package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
* A simple {@link Fragment} subclass.
 **/
public class M11FoodFragment extends Fragment {

    private ImageButton _btn_m11_agregar;
    private ImageButton _btn_m11_borrar;
    private GridLayout _gl_m11_listaAlimento;
    private View _view;
    private OnFragmentSwap _callBack;

    public M11FoodFragment() {
        // Required empty public constructor
    }

    /**
     * Metodo que se llama automaticamente cuando la la actividad anfitriona usa el fragmento.
     * @param activity Recibe la actividad anfitriona en la que va a mostrarse.
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            _callBack = (OnFragmentSwap) activity;
        } catch (ClassCastException e) {


            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _view = inflater.inflate(R.layout.fragment_m11_food, container, false);

        _btn_m11_agregar = (ImageButton)_view.findViewById(R.id.btn_m11_agregar);
        _btn_m11_borrar = (ImageButton)_view.findViewById(R.id.btn_m11_borrar);
        _gl_m11_listaAlimento = (GridLayout) _view.findViewById(R.id.gl_m11_listaAlimento);


        // Inflate the layout for this fragment
        return _view;
    }

}
