package com.fitucab.ds1617b.fitucab.UI.Fragments.M01;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class  M01RecoveryFragment extends Fragment {

    private TextView _printTextTV;
    private Button _btnChangeActivity;
    private View _view;
    private OnFragmentSwap _callBack;

    /**
     * constructor vacio
     */
    public M01RecoveryFragment() {
        // Required empty public constructor
    }

    /**
     * Una vez la activity llama a un fragment se ejecuta este metodo
     * @param activity recibe la activity que llamo o instancio al fragment
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            _callBack = (OnFragmentSwap) activity;

        }
        catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }

    /**
     * Metodo encargado de instanciar la vista Recuperación de contraseña.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_m01_recovery, container, false);
       /* setupViewValues();*/
        return _view;
    }

    /*private void setupViewValues() {
        _printTextTV = (TextView) _view.findViewById(R.id.tv_m01_prueba);
        Bundle incomingData = getArguments();
        String textFromView = incomingData.getString("text");
        System.out.println(textFromView);
        _printTextTV.setText(textFromView);
        _btnChangeActivity = (Button) _view.findViewById(R.id.btn_m01_change_activity);

    }*/



}

