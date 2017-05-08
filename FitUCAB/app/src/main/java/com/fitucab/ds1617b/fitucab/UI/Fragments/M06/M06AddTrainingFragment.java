package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * Created by Alejandro Fernandez on 23/4/2017.
 */

public class M06AddTrainingFragment extends Fragment implements View.OnClickListener {
    private Button _personalizado,_preterminado;
    private View _view;
    private OnFragmentSwap _callBack;
    private Toolbar _toolbar;

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
        _view =  inflater.inflate(R.layout.fragment_m06_add_training, container, false);
        setupViewValues();
        return _view;
    }

    private void setupViewValues() {
        //Le paso sus respectivos Id a los botones de personalizado y predefinido
        _personalizado = (Button) _view.findViewById(R.id.m06_botonEntrenamientoPersonalizado);
        _preterminado = (Button) _view.findViewById(R.id.m06_botonEntrenamientoPredefinido);
        //Aqui ando agregando el titulo a la toolbar y un boton para regresarme al anterior fragment
        _toolbar=(Toolbar) _view.findViewById(R.id.m06_toolbar_training_1);
        getActivity().setTitle(R.string.M06_title_activity_opcion_agregar_entrenamiento);
        _preterminado.setOnClickListener(this);
        _personalizado.setOnClickListener(this);
        ((AppCompatActivity)getActivity()).setSupportActionBar(_toolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.m06_toolbar_training_1:
                _callBack.onSwap("M06HomeTrainingFragment",null);
                break;
            case R.id.m06_botonEntrenamientoPersonalizado:
                _callBack.onSwap("M06AddTrainingTypePersonalizedFragment",null);
                break;
            case R.id.m06_botonEntrenamientoPredefinido:
                _callBack.onSwap("M06AddTrainingTypePredefinedFragment",null);
                break;
        }
    }
}
