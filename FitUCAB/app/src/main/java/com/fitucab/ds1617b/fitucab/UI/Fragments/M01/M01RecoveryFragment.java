package com.fitucab.ds1617b.fitucab.UI.Fragments.M01;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiClient;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiEndPointInterface;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fitucab.ds1617b.fitucab.Helper.ManagePreferences.getIdUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class  M01RecoveryFragment extends Fragment {
/*
    private TextView _printTextTV;
    private Button _btnChangeActivity;
    private View _view;
    private OnFragmentSwap _callBack;
    private EditText _etEmailRecovery;

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




    /**
     * Metodo para hacer las llamadas a los SW y hacer la recuperación de contraseña
     * @param emailRecovery
     */

    /*private void setupViewValues() {
        _printTextTV = (TextView) _view.findViewById(R.id.tv_m01_prueba);
        Bundle incomingData = getArguments();
        String textFromView = incomingData.getString("text");
        System.out.println(textFromView);
        _printTextTV.setText(textFromView);
        _btnChangeActivity = (Button) _view.findViewById(R.id.btn_m01_change_activity);

    }*/



}

