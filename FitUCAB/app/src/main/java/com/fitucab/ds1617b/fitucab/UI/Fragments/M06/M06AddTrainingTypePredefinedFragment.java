package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;

/**
 * Created by Alejandro Fernandez on 24/4/2017.
 */

public class M06AddTrainingTypePredefinedFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    private Button _agregar;
    private Spinner _spinnerEntrenamiento , _spinnerTipo , _spinnerTipoEntrenamiento;
    private String[] _calorias;
    private TextView _caloriasView;
    private EditText _periodicidad;
    private ArrayAdapter<CharSequence> _adaptador;
    private View _view;
    private Toolbar _toolbar;
    private OnFragmentSwap _callBack;
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
        _view =  inflater.inflate(R.layout.fragment_m06_add_training_predefined, container, false);
        setupViewValues();
        return _view;
    }

    /**
     * Prepara los componentes de la vista.
     */
    private void setupViewValues() {
        //Se intancia la periodicidad
        _periodicidad= (EditText) _view.findViewById(R.id.m06_editTextPeriodicidad);

        //Se instancian los 3 spinners, tanto de entrenamiento,tipo y tipo de entrenamiento
        _spinnerEntrenamiento=(Spinner)_view.findViewById(R.id.m06_spinnerEntrenamientoPredefinido);
        _spinnerTipo=(Spinner)_view.findViewById(R.id.m06_spinnerTipo);
        _spinnerTipoEntrenamiento=(Spinner) _view.findViewById(R.id.m06_spinnerTipoEntrenamiento);

        //Se crean adaptadores para cada tipo de spinner y luego se rellenan con la infromacion
        _adaptador= ArrayAdapter.createFromResource(getContext(),R.array.M06_tiposEntrenamientosPredefinidos,android.R.layout.simple_spinner_item);
        _adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerEntrenamiento.setAdapter(_adaptador);
        _adaptador= ArrayAdapter.createFromResource(getContext(),R.array.M06_dificultad,android.R.layout.simple_spinner_item);
        _adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerTipo.setAdapter(_adaptador);
        _adaptador= ArrayAdapter.createFromResource(getContext(),R.array.M06_array_tipo_de_entrenamiento,android.R.layout.simple_spinner_item);
        _adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinnerTipoEntrenamiento.setAdapter(_adaptador);

        //Agregando funcionalidad a los botones
        _agregar=(Button) _view.findViewById(R.id.m06_botonAgregarEntrenamientoPredefinido);
        _agregar.setOnClickListener(this);

        //Agregando funcionalidad a los spinners
        _spinnerEntrenamiento.setOnItemSelectedListener(this);
        _spinnerTipo.setOnItemSelectedListener(this);

        //Agregando items al array de string
        _calorias=getResources().getStringArray(R.array.M06_calorias);

        //Agregando id al text view de calorias
        _caloriasView=(TextView) _view.findViewById(R.id.m06_textViewCalorias);

    }

    /**
     * Metodo por el cual recibe como parametro que boton fue presionado y realiza una accion
     * @param v Boton presionado
     */
    @Override
    public void onClick(View v) {
        String periodicidad;
        if ( v.getId() == R.id.m06_botonAgregarEntrenamientoPredefinido){

            /*Se valida si lo que trae el TextEdit de numero está vació, si no está vacío no
                le informa al usuario que debe rellanar el campo*/
            periodicidad = _periodicidad.getText().toString();

            if( periodicidad == null || periodicidad.isEmpty() ){

                Toast.makeText( getContext() , R.string.M06_toast_periodicidad, Toast.LENGTH_SHORT).show();

            }else{

                //Aqui tengo que invocar un metodo con el servicio web para agregar el entrenamiento
                //Y llenar la BDD
                Toast.makeText( getContext() , R.string.M06_entrenamiento_creado_exito, Toast.LENGTH_SHORT).show();
                _callBack.onSwap( "M06HomeTrainingFragment" , null );

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String entrenamiento, tipo;
        entrenamiento= _spinnerEntrenamiento.getSelectedItem().toString();
        tipo = _spinnerTipo.getSelectedItem().toString();
        if(entrenamiento.equals("Caminata")) {
            if (tipo.equals("Fácil")) {
                _caloriasView.setText(_calorias[0]);
            } else if (tipo.equals("Medio")) {
                _caloriasView.setText(_calorias[1]);
            } else if (tipo.equals("Profesional")) {
                _caloriasView.setText(_calorias[2]);
            }
        }else if(entrenamiento.equals("Trote")){
            if (tipo.equals("Fácil")) {
                _caloriasView.setText(_calorias[3]);
            } else if (tipo.equals("Medio")) {
                _caloriasView.setText(_calorias[4]);
            } else if (tipo.equals("Profesional")) {
                _caloriasView.setText(_calorias[5]);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
