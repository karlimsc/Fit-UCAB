package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Training;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        Training entrenamiento = new Training();
        String periodicidad;

        if ( v.getId() == R.id.m06_botonAgregarEntrenamientoPredefinido){

            /*Se valida si lo que trae el TextEdit de numero está vació, si no está vacío no
                le informa al usuario que debe rellanar el campo*/
            periodicidad = _periodicidad.getText().toString();

            if( periodicidad == null || periodicidad.isEmpty() ){

                Toast.makeText( getContext() , R.string.M06_toast_periodicidad, Toast.LENGTH_SHORT).show();

            }else{
                entrenamiento.setTrainingName( _spinnerEntrenamiento.getSelectedItem().toString() );
                entrenamiento.setTrainingCalories( Integer.valueOf( _caloriasView.getText().toString() ) );
                entrenamiento.setTrainingPeriod( Integer.valueOf( Integer.valueOf( periodicidad ) ) );
                String nivelEntrenamiento = _spinnerTipo.getSelectedItem().toString();
                String tipoEntrenamiento = _spinnerTipoEntrenamiento.getSelectedItem().toString();
                createTrainingPredefined( entrenamiento , nivelEntrenamiento , tipoEntrenamiento );

            }
        }
    }

    private void createTrainingPredefined(Training entrenamiento, String nivelEntrenamiento, String tipoEntrenamiento) {

        switch (entrenamiento.getTrainingName()){
            case "Caminata":
                if( nivelEntrenamiento.equals( "Fácil" ) ) {

                    if(nivelEntrenamiento.equals("Por Kilometros")) {

                        makeRequestKilometros( entrenamiento , nivelEntrenamiento , tipoEntrenamiento, 3 , 1);

                    } //Fin deli if en caso de que sea kilometros
                        else
                    {
                        makeRequestTiempo(  entrenamiento , nivelEntrenamiento , tipoEntrenamiento, 10 , 1);
                    } //Fin del else en caso de que sea por tiempo

                } //Fin del if en caso de que se fácil

                else if (nivelEntrenamiento.equals( "Medio" ) ) {

                    if(nivelEntrenamiento.equals("Por Kilometros")) {

                        makeRequestKilometros( entrenamiento , nivelEntrenamiento , tipoEntrenamiento, 5 , 1);

                    } //Fin deli if en caso de que sea kilometros
                    else {
                        makeRequestTiempo(  entrenamiento , nivelEntrenamiento , tipoEntrenamiento, 15 , 1);
                    } //Fin del else en caso de que sea por tiempo

                } //Fin del else en caso de que sea medio
                    else {

                    if(nivelEntrenamiento.equals("Por Kilometros")) {

                        makeRequestKilometros( entrenamiento , nivelEntrenamiento , tipoEntrenamiento, 10 , 1);

                    } //Fin deli if en caso de que sea kilometros
                    else{
                        makeRequestTiempo(  entrenamiento , nivelEntrenamiento , tipoEntrenamiento, 20 , 1);
                    } //Fin del else en caso de que sea por tiempo

                } //Fin del else en caso de que sea profesional
            break;

            case "Trote":
                if( nivelEntrenamiento.equals( "Fácil" ) ) {

                    if(nivelEntrenamiento.equals("Por Kilometros")) {

                        makeRequestKilometros( entrenamiento , nivelEntrenamiento , tipoEntrenamiento, 4, 2);

                    } //Fin deli if en caso de que sea kilometros
                    else
                    {
                        makeRequestTiempo(  entrenamiento , nivelEntrenamiento , tipoEntrenamiento, 10,2 );
                    } //Fin del else en caso de que sea por tiempo

                } //Fin del if en caso de que se fácil

                else if (nivelEntrenamiento.equals( "Medio" ) ) {

                    if(nivelEntrenamiento.equals("Por Kilometros")) {

                        makeRequestKilometros( entrenamiento , nivelEntrenamiento , tipoEntrenamiento, 7,2 );

                    } //Fin deli if en caso de que sea kilometros
                    else {
                        makeRequestTiempo(  entrenamiento , nivelEntrenamiento , tipoEntrenamiento, 15, 2);
                    } //Fin del else en caso de que sea por tiempo

                } //Fin del else en caso de que sea medio
                else {

                    if(nivelEntrenamiento.equals("Por Kilometros")) {

                        makeRequestKilometros( entrenamiento , nivelEntrenamiento , tipoEntrenamiento,  10, 2);

                    } //Fin deli if en caso de que sea kilometros
                    else{

                        makeRequestTiempo(  entrenamiento , nivelEntrenamiento , tipoEntrenamiento, 20, 2);

                    } //Fin del else en caso de que sea por tiempo

                } //Fin del else en caso de que sea profesional
                break;

        } //Fin del Switch
    }


    /**
     * Recibe el niveldel entrenamiento y el tipo de entrenanmiento el nivel y el tiempo
     * @param entrenamiento
     * @param nivelEntrenamiento
     * @param tipoEntrenamiento
     * @param tiempo
     * @param tipo Si es 1 significa que será del tipo caminata si es 2 significa que será del tipo correr
     */
    private void makeRequestTiempo(Training entrenamiento , String nivelEntrenamiento, String tipoEntrenamiento,
                                                                int tiempo, int tipo) {

        IpStringConnection ip = new IpStringConnection();
        String url = ip.getIp() + "M06_ServicesTraining/createTrainingPredefined";
        url = url + "?nombreEntrenamiento=" + entrenamiento.getTrainingName() + "&nivelEntrenamiento=" + nivelEntrenamiento;
        url = url + "&tipoEntrenamiento=" + tipoEntrenamiento + "&calorias=" + entrenamiento.getTrainingCalories();
        url = url + "&periodicidad=" + entrenamiento.getTrainingPeriod() + "&tiempo=" + tiempo + "&deporte=" + tipo;
        url = url + "&userId=" + ManagePreferences.getIdUser( getContext() );
        RequestQueue queue = Volley.newRequestQueue( getContext() );
        //Se hace la peticion y lo devuelve en String Request
        StringRequest stringRequest = new StringRequest( Request.Method.GET , url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){

                        boolean confirmacion = true;
                        confirmacion = Boolean.parseBoolean(response);

                        //Confimo si pudo insertar en la bdd
                        if ( confirmacion == true){

                            Toast.makeText( getContext() , R.string.M06_entrenamiento_creado_exito, Toast.LENGTH_SHORT).show();
                            _callBack.onSwap( "M06HomeTrainingFragment" , null );

                        }else{

                            Toast.makeText( getContext() , R.string.M06_entrenamiento_creado_fallo, Toast.LENGTH_SHORT).show();

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText( getContext() , "Error connection" , Toast.LENGTH_SHORT).show();

            }
        });
        // Add the request to the RequestQueue.
        queue.add( stringRequest );
    }


    /**
     * Recibe el entrenamiento del tipo de kilometros
     * @param entrenamiento
     * @param nivelEntrenamiento
     * @param tipoEntrenamiento
     * @param kilometros
     * @param tipo si es 1 significa que es correr, si es 2 significa que es trotar
     */

    public void makeRequestKilometros( Training entrenamiento , String nivelEntrenamiento, String tipoEntrenamiento,
                                       int kilometros, int tipo){

        IpStringConnection ip = new IpStringConnection();
        String url = ip.getIp() + "M06_ServicesTraining/createTrainingPredefined";
        url = url + "?nombreEntrenamiento=" + entrenamiento.getTrainingName() + "&nivelEntrenamiento=" + nivelEntrenamiento;
        url = url + "&tipoEntrenamiento=" + tipoEntrenamiento + "&calorias=" + entrenamiento.getTrainingCalories();
        url = url + "&periodicidad=" + entrenamiento.getTrainingPeriod() + "&kilometros=" + kilometros + "&deporte=" + tipo;
        url = url + "&userId=" + ManagePreferences.getIdUser( getContext() );
        RequestQueue queue = Volley.newRequestQueue( getContext() );

        //Se hace la peticion y lo devuelve en String Request
        StringRequest stringRequest = new StringRequest( Request.Method.GET , url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){

                        boolean confirmacion = true;
                        confirmacion = Boolean.parseBoolean(response);

                        //Confimo si pudo insertar en la bdd
                        if ( confirmacion == true){

                            Toast.makeText( getContext() , R.string.M06_entrenamiento_creado_exito, Toast.LENGTH_SHORT).show();
                            _callBack.onSwap( "M06HomeTrainingFragment" , null );

                        }else{

                            Toast.makeText( getContext() , R.string.M06_entrenamiento_creado_fallo, Toast.LENGTH_SHORT).show();

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText( getContext() , "Error connection" , Toast.LENGTH_SHORT).show();

            }
        });
        // Add the request to the RequestQueue.
        queue.add( stringRequest );
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String entrenamiento, tipo;
        entrenamiento= _spinnerEntrenamiento.getSelectedItem().toString();
        tipo = _spinnerTipo.getSelectedItem().toString();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( getContext() );
        int peso = Math.round( preferences.getFloat("weight", 0) );
        int  calorias;
        if(_spinnerTipoEntrenamiento.getSelectedItem().toString().equals("Por Tiempo")){
                if(entrenamiento.equals("Caminata")) {

                    if (tipo.equals("Fácil")) {

                        calorias =  peso * 10;
                        _caloriasView.setText( Integer.toString( calorias ) );

                    } else if (tipo.equals("Medio")) {

                        calorias =  peso * 15;
                        _caloriasView.setText( Integer.toString( calorias ) );

                    } else if (tipo.equals("Profesional")) {

                        calorias =  peso * 20;
                        _caloriasView.setText( Integer.toString( calorias ) );

                    }

                }else if(entrenamiento.equals("Trote")){

                    if (tipo.equals("Fácil")) {
                        calorias = peso * 10;
                        _caloriasView.setText( Integer.toString( calorias ) );
                    } else if (tipo.equals("Medio")) {
                        calorias = peso * 15;
                        _caloriasView.setText( Integer.toString( calorias ) );
                    } else if (tipo.equals("Profesional")) {
                        calorias = peso * 20;
                        _caloriasView.setText( Integer.toString( calorias ) );
                    }

                }

        }else{ //Si es por kilometros

            if(entrenamiento.equals("Caminata")) {

                if (tipo.equals("Fácil")) {

                    calorias = peso * 3 ;
                    _caloriasView.setText( Integer.toString( calorias ) );

                } else if (tipo.equals("Medio")) {

                    calorias =  peso * 5 ;
                    _caloriasView.setText( Integer.toString( calorias ) );

                } else if (tipo.equals("Profesional")) {

                    calorias =  peso * 10 ;
                    _caloriasView.setText( Integer.toString( calorias ) );

                }

            }else if(entrenamiento.equals("Trote")){

                if (tipo.equals("Fácil")) {

                    calorias = ( peso * 4 );
                    _caloriasView.setText( Integer.toString( calorias ) );

                } else if (tipo.equals("Medio")) {

                    calorias = ( peso * 7 );
                    _caloriasView.setText( Integer.toString( calorias ) );

                } else if (tipo.equals("Profesional")) {

                    calorias = ( peso * 10 );
                    _caloriasView.setText( Integer.toString( calorias ) );

                }

            }

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
