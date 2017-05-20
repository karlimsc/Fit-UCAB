package com.fitucab.ds1617b.fitucab.UI.Fragments.M01;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class  M01LoginFragment extends Fragment {

    private TextView _tvOlvidoClave;
    private Button _btnEntrarLogin;
    private View _view;
    private OnFragmentSwap _callBack;
    private EditText _etUserLogin;
    private EditText _etPasswordLogin;

    public M01LoginFragment() {
    }

    /**
     * Una vez la activity llama a un fragment se ejecuta este metodo
     * @param activity recibe la activity que llamo o instancio al fragment
     */
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            _callBack = (OnFragmentSwap) activity;
        } catch (ClassCastException e) {


            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }

    /**
     * Metodo encargado de instanciar la vista
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _view = inflater.inflate(R.layout.fragment_m01_login, container, false);
        instanciarComponentes();
        manageChangeFragmentRecovery();
        manageButtonEntrar();
        return _view;
    }

    /**
     * metodo de listener de hipervinculo, que realiza el cambio a la vista recuperar contraseña.
     */
    private void manageChangeFragmentRecovery() {
        _tvOlvidoClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M01RecoveryFragment",null);
            }
        });

    }

    /**
     * metodo de listener del boton entrar, para realizar el cambio de actividad.
     */
    private void manageButtonEntrar(){
        _btnEntrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _callBack.onSwapActivity("M02HomeActivity",null);
            }
        });
    }

    /**
     * Metodo encargado para instanciar los componentes de esta vista
     */
    private void instanciarComponentes (){
        _tvOlvidoClave=(TextView) _view.findViewById(R.id.tv_m01_olvidoClave);
        _btnEntrarLogin=(Button) _view.findViewById(R.id.btn_m01_aceptarLogin);
        _etUserLogin=(EditText) _view.findViewById(R.id.et_m01_passwordLogin);
        _etPasswordLogin=(EditText) _view.findViewById(R.id.et_m01_usuarioCorreo);

        //llamar a ese metodo
    }
}
