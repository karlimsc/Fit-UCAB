package com.fitucab.ds1617b.fitucab.UI.Fragments.M01;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fitucab.ds1617b.fitucab.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class  M01LoginFragment extends Fragment {

    private Button _btnRegistro;
    private Button _btnIniciaSesion;
    private View _view;
    public M01LoginFragment() {
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
        _view = inflater.inflate(R.layout.fragment_m01_home, container, false);
        instanciarComponentes();
        return _view;
    }

    /**
     * Metodo encargado para instanciar los componentes de esta vista
     */
    private void instanciarComponentes (){
        _btnRegistro=(Button) _view.findViewById(R.id.btn_m01_comienza);
        _btnIniciaSesion=(Button) _view.findViewById(R.id.btn_m01_iniciarSesion);


        //llamar a ese metodo
    }
}
