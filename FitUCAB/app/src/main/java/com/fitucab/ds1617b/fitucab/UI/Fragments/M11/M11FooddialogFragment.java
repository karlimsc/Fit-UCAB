package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Model.Food;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;


public class M11FooddialogFragment extends DialogFragment {

    private String _nombreAlimento;
    private String _pesoAlimento;
    private String _caloriasAlimento;
    private int _idAlimento;
    private EditText _tv_m11_nombreAlimento;
    private EditText _tv_m11_peso;
    private EditText _tv_m11_calorias;
    private CheckBox _chbx_m11_cena;
    private View _view;

    public M11FooddialogFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public M11FooddialogFragment(String _nombreAlimento, String _pesoAlimento, String _caloriasAlimento
            , int _idAlimento){
        this._nombreAlimento = _nombreAlimento;
        this._pesoAlimento = _pesoAlimento;
        this._caloriasAlimento = _caloriasAlimento;
        this._idAlimento = _idAlimento;
    }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            //Infla el fragmento
            final LayoutInflater inflater = getActivity().getLayoutInflater();
            _view = inflater.inflate(R.layout.fragment_m11_fooddialog, null);
            Dialog dlg = getDialog();
            _tv_m11_nombreAlimento = (EditText) dlg.findViewById(R.id.tv_m11_nombreAlimento5);
            _tv_m11_peso = (EditText) dlg.findViewById(R.id.tv_m11_peso5);
            _tv_m11_calorias = (EditText) dlg.findViewById(R.id.tv_m11_calorias5);
            _chbx_m11_cena = (CheckBox) dlg.findViewById(R.id.chbx_m11_cena);
            //_tv_m11_nombreAlimento.setText(String.valueOf(get_nombreAlimento()));
            //_tv_m11_peso.setText(String.valueOf(get_pesoAlimento()));
            //_tv_m11_calorias.setText(String.valueOf(get_caloriasAlimento()));
            //final String hola = _tv_m11_calorias.getText().toString();
            //Si voy a agregar un alimento.
            /*if ( get_idAlimento() == 0 ) {
                builder.setView(inflater.inflate(R.layout.fragment_m11_fooddialog, null))
                        //Conecta con el XML que contiene la personalizacion del Dialog

                        .setTitle("Agregar Alimento Personalizado")
                        .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Si preciona el boton Agregar el alimento personalizado
                                set_nombreAlimento(_tv_m11_nombreAlimento.getText().toString());
                                set_caloriasAlimento(_tv_m11_calorias.getText().toString());
                                set_pesoAlimento(_tv_m11_peso.getText().toString());
                                String name = _tv_m11_nombreAlimento.getText().toString();
                                Log.wtf("PROBANDO CON STRING!" , name);
                                insertarAlimentoPersonalizado (get_nombreAlimento() , get_pesoAlimento() ,
                                        get_caloriasAlimento() , 1 , inflater.getContext());

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Si preciona el boton cancelar solo se cerrar el Dialog
                                dialog.cancel();
                            }

                        });


            }*/
            //Si voy a editar un alimento.
           // else{
                builder.setView(inflater.inflate(R.layout.fragment_m11_fooddialog, null))
                        //Conecta con el XML que contiene la personalizacion del Dialog
                        .setTitle("Editar Alimento Personalizado")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Si preciona el boton Agregar el alimento personalizado
                                // Es necesario extraer el usuario!!!


                                set_nombreAlimento(_tv_m11_nombreAlimento.getText().toString());
                                set_caloriasAlimento(_tv_m11_calorias.getText().toString());
                                set_pesoAlimento(_tv_m11_peso.getText().toString());
                                editarAlimentoPersonalizado( get_nombreAlimento() , get_pesoAlimento() ,
                                        get_caloriasAlimento() , 1 , inflater.getContext() );
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Si preciona el boton cancelar solo se cerrar el Dialog
                                dialog.cancel();
                            }

                        });

            //}
            return builder.create();
        }


    public String get_nombreAlimento() {
        return _nombreAlimento;
    }

    public void set_nombreAlimento(String _nombreAlimento) {
        this._nombreAlimento = _nombreAlimento;
    }

    public String get_pesoAlimento() {
        return _pesoAlimento;
    }

    public void set_pesoAlimento(String _pesoAlimento) {
        this._pesoAlimento = _pesoAlimento;
    }

    public String get_caloriasAlimento() {
        return _caloriasAlimento;
    }

    public void set_caloriasAlimento(String _caloriasAlimento) {
        this._caloriasAlimento = _caloriasAlimento;
    }

    public int get_idAlimento() {
        return _idAlimento;
    }

    public void set_idAlimento(int _idAlimento) {
        this._idAlimento = _idAlimento;
    }

    /**
     * Metodo que realiza la peticion al Servicio Web para editar un alimento personalizado.
     * @param nombreAlimento Representa el nombre del alimento que va a ser editado.
     * @param peso Representa el peso del alimento que va a ser editado.
     * @param caloria Representa las calorias del alimento que va a ser editado.
     * @param usuarioID Representa el ID del usuario que va a editar el alimento.
     * @param inflater Representa el Contexto sobre el cual se realiza la edicion.
     */
    public void editarAlimentoPersonalizado(String nombreAlimento , String peso , String caloria ,
                                            int usuarioID , final Context inflater){
        RequestQueue requestQueue = Volley.newRequestQueue( inflater );
        IpStringConnection jsonURL = new IpStringConnection();
        jsonURL.set_ip( jsonURL.get_ip() + "M11_Food/updatePersonalized?foodName="
                + nombreAlimento + "&foodWeight=" + peso + "&calorie="
                + caloria + "&idUser=" + usuarioID );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.get_ip(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        ArrayList<String> respuesta = new ArrayList<>();
                        respuesta = gson.fromJson( response,
                                new TypeToken<ArrayList<String>>(){}.getType() );
                        Toast mensaje = new Toast( inflater );
                        mensaje.setText( "El alimento fue actualizado correctamente" );
                        mensaje.setDuration( Toast.LENGTH_LONG );
                        mensaje.setGravity( Gravity.CENTER , 0 , 0);
                        mensaje.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( inflater , "Hola, no devolvio nada" , Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add( stringRequest );

    }
    //Se debe cambiar el plsql de esto, ya que tambien es necesario saber si quiere que el alimento
    //Aparezca para las cenas
    //Hay error con la función en el sw, no toma el json creo.
    public void insertarAlimentoPersonalizado ( String nombreAlimento , String peso , String caloria ,
                                               int usuarioID , final Context inflater ){
        RequestQueue requestQueue = Volley.newRequestQueue( inflater );
        IpStringConnection jsonURL = new IpStringConnection();
        ArrayList<Food> foodJson = new ArrayList<>();
        foodJson.add ( new Food() );
        foodJson.get(0).set_FoodCalorie( caloria );
        foodJson.get(0).set_FoodName( nombreAlimento );
        foodJson.get(0).set_FoodWeight( peso );
        foodJson.get(0).set_Id(1); //idUser
        if (_chbx_m11_cena.isChecked())
            foodJson.get(0).set_FoodDinner(true);
        else foodJson.get(0).set_FoodDinner(false);
        Gson gson = new Gson();
        jsonURL.set_ip( jsonURL.get_ip() + "M11_Food/insertPersonalizedFood?foodJson="+
                gson.toJson(foodJson) );
        //Log.wtf("PUTO ERRRRRRROOOOOOOOOOOOOO------", gson.toJson(foodJson));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.get_ip(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        HashMap<String, String> respuesta = new HashMap<>();
                        respuesta = gson.fromJson( response,
                                new TypeToken<HashMap<String, String>>(){}.getType() );
                        Toast mensaje = new Toast( inflater );
                        mensaje.setText( "El alimento fue agregado correctamente" );
                        mensaje.setDuration( Toast.LENGTH_LONG );
                        mensaje.setGravity( Gravity.CENTER , 0 , 0);
                        mensaje.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( inflater , "Hola, no devolvio nada" , Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add( stringRequest );

    }
}
