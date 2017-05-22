package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;
import android.widget.ArrayAdapter;

/**
 * A simple {@link Fragment} subclass.
 */

public class M11DietFragment extends Fragment {


    private TextView _tv_m11_cantidadCalorias;
    private TextView _tv_m11_nombreAlimento;
    private TextView _tv_m11_peso;
    private ImageButton _btn_m11_agregar;
    private Spinner _spinner_comida;
    private ImageButton _btn_m11_aceptar;
    private ImageButton _btn_m11_rechazar;
    private TableLayout _tl_m11_listaDieta;
    private View _view;
    private OnFragmentSwap _callBack;

    public M11DietFragment() {
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

        // Inflate the layout for this fragment

        _view = inflater.inflate(R.layout.fragment_m11_diet, container, false);


        _tv_m11_cantidadCalorias = (TextView) _view.findViewById(R.id.tv_m11_cantidadCalorias);
        _tv_m11_nombreAlimento = (TextView) _view.findViewById(R.id.tv_m11_nombreAlimento);
        _tv_m11_peso = (TextView) _view.findViewById(R.id.tv_m11_peso);
        _btn_m11_agregar = (ImageButton) _view.findViewById(R.id.btn_m11_agregar);
        _spinner_comida = (Spinner) _view.findViewById(R.id.spinner_comida);
        _btn_m11_aceptar = (ImageButton) _view.findViewById(R.id.btn_m11_aceptar);
        _btn_m11_rechazar = (ImageButton) _view.findViewById(R.id.btn_m11_rechazar);
        _tl_m11_listaDieta = (TableLayout) _view.findViewById(R.id.tl_m11_listaDieta);



        return _view;
    }
}
