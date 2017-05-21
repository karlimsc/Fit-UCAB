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
import android.widget.ListView;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * Created by Alejandro Fernandez on 24/4/2017.
 */

public class M06DetailsTrainingFragment extends Fragment {
    private ListView _listView;
    private View _view;
    private OnFragmentSwap _callBack;
    private ArrayAdapter<String> _adaptador;
    private String[] entrenamiento = {"Correr,Trotar"};
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
        _view = inflater.inflate(R.layout.fragment_m06_details_training_fragment, container, false);
        setupViewValues();
        return _view;
    }

    private void setupViewValues() {
        //Llenando el list View
        _listView = (ListView) _view.findViewById(R.id.m06_ListViewActividades);
        _adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, entrenamiento);
        _listView.setAdapter(_adaptador);
    }
}
