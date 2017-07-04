package com.fitucab.ds1617b.fitucab.UI.Fragments.M11;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
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
import com.fitucab.ds1617b.fitucab.Model.Diet;
import com.fitucab.ds1617b.fitucab.Model.Food;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 **/
public class M11SuggestionFragment extends Fragment {


    private TableLayout _gl_m11_listaSugerencia;
    private View _view;
    private OnFragmentSwap _callBack;

    public M11SuggestionFragment(){

    }

    /**
     * Metodo que se llama automaticamente cuando la la actividad anfitriona usa el fragmento.
     * @param activity Recibe la actividad anfitriona en la que va a mostrarse.
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


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _view = inflater.inflate(R.layout.fragment_m11_suggestion, container, false);

        _gl_m11_listaSugerencia = (TableLayout) _view.findViewById(R.id.gl_m11_listaSugerencia);

        // Inflate the layout for this fragment
        return _view;
    }

    public void PeticionAlimentos(String usuario)
    {
        String username;
        username = ManagePreferences.getUsername(getContext());
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        IpStringConnection jsonURL = new IpStringConnection();
        jsonURL.set_ip( jsonURL.getIp() + "M11_Food/getSuggestion?username=" + username + "&" +
                "calorie=" + 400);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        ArrayList<Food> foods = new ArrayList<>();
                        try {
                            Food aux1 = gson.fromJson(response, Food.class);
                            foods = aux1.jsonArray;

                            LlenaTablaAlimentos(foods);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(_view.getContext(), "Hola, no devolvio nada", Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add(stringRequest);
    }

    /**
     * Metodo de llenado de la tabla, este metodo se usa para llenar la tabla sobre la cual
     * estaran todos los alimentos de los usuarios.
     * @param alimentos Recibe un arreglo del Servicio Web, del tipo Food.
     */
    public void LlenaTablaAlimentos(final ArrayList<Food> alimentos )
    {
        for (int i = 0 ; i < alimentos.size() ; i++ ){
            final TableRow fila = new TableRow(getContext());
            fila.setId(alimentos.get(i).get_id());
            _gl_m11_listaSugerencia.addView(fila);
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT , TableLayout.LayoutParams.WRAP_CONTENT);
            AgregaColumna( alimentos.get(i).get_foodName(), fila , params);
            AgregaColumna( String.valueOf(alimentos.get(i).get_foodCalorie()), fila , params);
            final String alimento = alimentos.get(i).get_foodName();
            final int caloriaAlimento = Integer.valueOf(alimentos.get(i).get_foodCalorie());
            fila.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    //Confirmacion para eliminar alimento de la tabla de la die
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("¿Que acción desea realizar?")
                            .setTitle("Acción")
                            .setCancelable(false)
                            //Acciones para cuando dice no
                            .setNegativeButton("Agregar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //El id del usuario debe ser variable
                                    ingresarDieta(alimento, caloriaAlimento);
                                }
                            })
                            .setNeutralButton("Volver", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }
    /**
     * Metodo que agrega las columnas de cada fila de la tabla de alimentos.
     * @param atributo Recibe el atributo que va a agregar a la vista.
     * @param fila Recibe la fila sobre la cual se va a agregar.
     * @param params Recibe el Layout sobre el cual se agregara.
     */
    public void AgregaColumna ( String atributo , TableRow fila, TableLayout.LayoutParams params )
    {
        TextView currentText = new TextView(getContext());
        currentText.setText(atributo);
        //currentText.setTextSize(alimento.length());
        currentText.setTextSize(13); // Tamaño generico para todos(de otra forma se ve muy pequeño
        currentText.setMinHeight(25);
        currentText.setMinWidth(15);
        currentText.setTextColor(Color.BLACK);
        fila.setLayoutParams(params);
        fila.addView(currentText);
    }

    public void ingresarDieta(String alimento, int caloriaAlimento){
        int idUser = 0;
        idUser = ManagePreferences.getIdUser(getContext());
        RequestQueue requestQueue = Volley.newRequestQueue(_view.getContext());
        IpStringConnection jsonURL = new IpStringConnection();
        //El ID del usuario debe traerse del tlf.
        jsonURL.set_ip( jsonURL.getIp() + "M11_Diet/insertOneDiet/?idUser=" + idUser + "&dietCalorie="
                + 128 + "&foodName=" + alimento  + "&moment=CENA"  );
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL.getIp(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        Diet aux = gson.fromJson( response,Diet.class);
                        Map<String, String> respuesta = new HashMap<>();
                        respuesta = aux.getResponse();

                        Toast.makeText( getContext() , respuesta.get("data") , Toast.LENGTH_LONG);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText( getContext() , "Hola, no devolvio nada" , Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add(stringRequest);

    }
}