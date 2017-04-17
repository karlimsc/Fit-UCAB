package com.fitucab.ds1617b.fitucab.UI.Fragments.M01;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class M01SignUpFragment extends Fragment {

    private OnFragmentSwap _callBack;
    private Button _clickHerebtn;
    private EditText _inputET;
    private View _view;

    public M01SignUpFragment() {
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
        } catch (ClassCastException e) {


            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        _view =  inflater.inflate(R.layout.fragment_m01_sign_up, container, false);
        setupViewValues();
        manageClickHereBtn();
        return _view;
    }

    private void manageClickHereBtn() {
        _clickHerebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtenemos en la variable local el valor del EditText
                //String textoEscritoEnPantalla = _inputET.getText().toString();
                //Declaramos un contenedor para enviar la info al otro fragment
                Bundle bundle = new Bundle();
                //bundle.putString("text",textoEscritoEnPantalla);
                _callBack.onSwap("M01HomeFragment",bundle);
            }
        });
    }

    private void setupViewValues() {
        _clickHerebtn = (Button) _view.findViewById(R.id.btn_m01_aceptar);


    }

}
