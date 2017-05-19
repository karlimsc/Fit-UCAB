package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * Created by Alejandro Fernandez on 23/4/2017.
 */

public class M06HomeTrainingFragment extends Fragment implements View.OnClickListener,ListView.OnItemClickListener{

    private View _view;
    private OnFragmentSwap _callBack;
    private Toolbar _toolbar;
    private ListView _listView;
    private ArrayAdapter<String> _adaptador;
    private String[] entrenamientos = {"Entrenamiento CAF 2018", "Entrenamiento lento","Caminata", "Trote"};

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
        _view =  inflater.inflate(R.layout.fragment_m06_home_training, container, false);
        setupViewValues();

        return _view;
    }

    /**
     * Se relacionan los atributos de este fragments a los del Fragment Home Training
     */

    private void setupViewValues() {
        /*  _toolbar=(Toolbar) _view.findViewById(R.id.m06_toolbar_training_6);
        getActivity().setTitle(R.string.M06_nombre_modulo);
        ((AppCompatActivity)getActivity()).setSupportActionBar(_toolbar);
        */
        //Llenando el list View
        _listView=(ListView)_view.findViewById(R.id.m06_listViewEntrenamiento);
        _adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, entrenamientos);
        _listView.setAdapter(_adaptador);
        _listView.setOnItemClickListener(this);
    }

    /**
     * Metodo al presionar un boton realice acciones
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){

        }
    }

    /**
     * Metodo en el cual al presionar un elemento del entrenamiento se abre para compartir
     * @param parent
     * @param view
     * @param position
     * @param id
     */

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        _callBack.onSwap("M06DetailsTrainingFragment",null);
    }
}
