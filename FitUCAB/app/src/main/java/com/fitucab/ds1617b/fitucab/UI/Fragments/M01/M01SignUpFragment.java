package com.fitucab.ds1617b.fitucab.UI.Fragments.M01;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class M01SignUpFragment extends Fragment {

    private OnFragmentSwap _callBack;
    private Button _btnRegistrar;
    private EditText _etfechaNac;
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


    /**
     * Metodo encargado de instanciar la vista, hacer los llamados a los metodos de listener de los componentes de la vista.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        _view =  inflater.inflate(R.layout.fragment_m01_sign_up, container, false);
        setupViewValues();
        manageBtnRegistrar();
        activarCalendario();
        return _view;
    }

    /**
     * Metodo encargado de cambiar de actividad al realizar la accion de seleccionar el boton Registar.
     */
    private void manageBtnRegistrar() {
        _btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtenemos en la variable local el valor del EditText
                //String textoEscritoEnPantalla = _inputET.getText().toString();
                //Declaramos un contenedor para enviar la info al otro fragment
                Bundle bundle = new Bundle();
                //bundle.putString("text",textoEscritoEnPantalla);
                _callBack.onSwapActivity("M02HomeActivity",null);
            }
        });
    }

    /**
     * Metodo que activa el calendario.
     */
    private void activarCalendario(){
        _etfechaNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instanciarCalendario();
            }
        });
    }

    /**
     * Metodo que prepara los componentes de la vista
     */
    private void setupViewValues() {
        _btnRegistrar = (Button) _view.findViewById(R.id.btn_m01_entrar);
        _etfechaNac = (EditText) _view.findViewById(R.id.et_m01_fechanac);

    }

    Calendar cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date

    /**
     * Metodo encargado de instancias el calendario.
     */
    public void instanciarCalendario(){
        // Create the DatePickerDialog instance
        DatePickerDialog datePicker = new DatePickerDialog(getContext(), R.style.AppTheme,
        datePickerListener,cal.get(Calendar.YEAR), cal.get(Calendar.DAY_OF_MONTH),
        cal.get(Calendar.MONTH));

        datePicker.setCancelable(false);
        datePicker.setTitle("Select the date");
        datePicker.show();
    }

    /**
     *
     */
    // Listener
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            _etfechaNac.setText(day1 + "/" + month1 + "/" + year1);
        }
    };
}
