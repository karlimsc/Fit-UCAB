package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * Created by Alejandro Fernandez on 24/4/2017.
 */

public class M06AddTrainingTypePersonalizedFragment extends Fragment implements View.OnClickListener{
    private Button _agregar;
    private View _view;
    private OnFragmentSwap _callBack;
    private Toolbar _toolbar;
    private Spinner _spinnerCaminar,_spinnerCorrer;
    private ArrayAdapter<CharSequence> _adaptador;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view =  inflater.inflate(R.layout.fragment_m06_add_training_personalized, container, false);
        setupViewValues();
        return _view;
    }

    private void setupViewValues() {
        //Le paso sus respectivos Id a los botones de para volver a la pantalla principal
        _agregar=(Button)_view.findViewById(R.id.m06_botonAgregarEntrenamiento);
        _agregar.setOnClickListener(this);

        //Agregando a los spinners las listas
        _spinnerCaminar= (Spinner) _view.findViewById(R.id.m06_spinnerCaminar);
        _spinnerCorrer= (Spinner) _view.findViewById(R.id.m06_spinnerCorrer);
        _adaptador = ArrayAdapter.createFromResource(getContext(), R.array.M06_tipoTiempos, android.R.layout.simple_spinner_item);
        _adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerCaminar.setAdapter(_adaptador);
        _spinnerCorrer.setAdapter(_adaptador);

        //Aqui ando agregando el titulo a la toolbar y un boton para regresarme al anterior fragment
        _toolbar=(Toolbar) _view.findViewById(R.id.m06_toolbar_training_2);
        getActivity().setTitle(R.string.M06_title_activity_opcion_agregar_entrenamiento_personalizado);
        ((AppCompatActivity)getActivity()).setSupportActionBar(_toolbar);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.m06_botonAgregarEntrenamiento:
                Toast.makeText(getContext(), R.string.M06_entrenamiento_creado_exito, Toast.LENGTH_SHORT).show();
                _callBack.onSwap("M06HomeTrainingFragment",null);
                break;
        }
    }
}
