package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Model.Food;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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

    /**
     * Metodo sobre el cual se maneja el dialogo para agregar y editar alimentos personalizados
     * @param savedInstanceState La instancia.
     * @return Devuelve el dialogo construido.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Infla el fragmento
        final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _view =  inflater.inflate(R.layout.fragment_m11_fooddialog, null);
        _tv_m11_nombreAlimento = (EditText) _view.findViewById(R.id.tv_m11_nombreAlimento5);
        _tv_m11_peso = (EditText) _view.findViewById(R.id.tv_m11_peso5);
        _tv_m11_calorias = (EditText) _view.findViewById(R.id.tv_m11_calorias5);
        _chbx_m11_cena = (CheckBox) _view.findViewById(R.id.chbx_m11_cena);
        //Si voy a agregar un alimento.
        if ( get_idAlimento() == 0 ) {
            builder.setView(_view);
            //Conecta con el XML que contiene la personalizacion del Dialog
            builder.setTitle("Agregar Alimento Personalizado");
            builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Si preciona el boton Agregar el alimento personalizado
                    set_nombreAlimento(_tv_m11_nombreAlimento.getText().toString());
                    set_caloriasAlimento(_tv_m11_calorias.getText().toString());
                    set_pesoAlimento(_tv_m11_peso.getText().toString());
                    int idUser = 0;
                    idUser = ManagePreferences.getIdUser(inflater.getContext());
                    insertarAlimentoPersonalizado (get_nombreAlimento() , get_pesoAlimento() ,
                            get_caloriasAlimento() , idUser , inflater.getContext());
                }
            })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Si preciona el boton cancelar solo se cerrar el Dialog
                            dialog.cancel();
                        }
                    });
        }
        //Si voy a editar un alimento.
        else{
            _tv_m11_nombreAlimento.setText(String.valueOf(get_nombreAlimento()));
            _tv_m11_peso.setText(String.valueOf(get_pesoAlimento()));
            _tv_m11_calorias.setText(String.valueOf(get_caloriasAlimento()));
            builder.setView(_view);
            //Conecta con el XML que contiene la personalizacion del Dialog
            builder.setTitle("Editar Alimento Personalizado");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //Si preciona el boton Agregar el alimento personalizado
                    // Es necesario extraer el usuario!!!


                    set_nombreAlimento(_tv_m11_nombreAlimento.getText().toString());
                    set_caloriasAlimento(_tv_m11_calorias.getText().toString());
                    set_pesoAlimento(_tv_m11_peso.getText().toString());
                    int idUser = 0;
                    idUser = ManagePreferences.getIdUser(inflater.getContext());
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

        }
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
        jsonURL.set_ip( jsonURL.getIp() + "M11_Food/updatePersonalized?foodName="
                + nombreAlimento + "&foodWeight=" + peso + "&calorie="
                + caloria + "&idUser=" + usuarioID );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        Food auu3=gson.fromJson( response,Food.class);
                        Map<String, String> respuesta = new HashMap<>();
                        respuesta = auu3.getResponse();

                        Toast.makeText( inflater , respuesta.get("data") , Toast.LENGTH_LONG);
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

    /**
     * Metodo que se utiliza para hacer la peticion y
     * agregar un alimento personalizado por el usuario.
     * @param nombreAlimento Recibe el nombre del alimento.
     * @param peso Recibe el peso del alimento.
     * @param caloria Recibe las calorias del alimento.
     * @param usuarioID Recibe el id del usuario.
     * @param inflater recibe el contexto sobre el cual se maneja.
     */
    public void insertarAlimentoPersonalizado ( String nombreAlimento , String peso , String caloria ,
                                                int usuarioID , final Context inflater ){
        RequestQueue requestQueue = Volley.newRequestQueue( inflater );
        IpStringConnection jsonURL = new IpStringConnection();
        ArrayList<Food> foodJson = new ArrayList<>();
        foodJson.add ( new Food() );
        foodJson.get(0).set_foodCalorie( caloria );
        foodJson.get(0).set_foodName( nombreAlimento );
        foodJson.get(0).set_foodWeight( peso );
        int idUser = 0;
        idUser = ManagePreferences.getIdUser(inflater);
        if (_chbx_m11_cena.isChecked())
            foodJson.get(0).set_foodPersonalized(true);
        else foodJson.get(0).set_foodPersonalized(false);
        jsonURL.set_ip( jsonURL.getIp() + "M11_Food/insertOnePersonalizedFood?foodName="+
                get_nombreAlimento() + "&foodCalorie=" + get_caloriasAlimento() + "&foodWeight=" +
                get_pesoAlimento() + "&foodDinner=" + foodJson.get(0).get_foodPersonalized() +
                "&idUser=" + idUser );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Gson gson = new Gson();
                            Map<String, String> respuesta = new HashMap<>();
                            Food l = gson.fromJson(response, Food.class);
                            respuesta = l.getResponse();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                       // Toast.makeText( inflater , respuesta.get("data") , Toast.LENGTH_LONG);
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