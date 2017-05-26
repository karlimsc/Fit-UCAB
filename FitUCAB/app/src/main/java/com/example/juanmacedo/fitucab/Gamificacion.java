package com.example.juanmacedo.fitucab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Gamificacion extends AppCompatActivity implements View.OnClickListener, Logros.OnFragmentInteractionListener{

    private ArrayList<String> arrayList;
    private String[] logros = {"5 Km recorrido", "10 km recorrido", "Regimen Agua", "Gana a un amigo", "Invita a un amigo"};
    private ArrayAdapter<String> adapter;
    private ListView lista;
    TextView TextViewpuntaje;
    int puntajeTotal;
    private static String URL = "http://192.168.56.1:8080/FitUcabService_war_exploded/db/obtener";
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamificacion);
        TextViewpuntaje = (TextView) findViewById(R.id.tv_puntos);
        lista = (ListView) findViewById(R.id.lista);
     //   arrayList = new ArrayList<String>();                                               //ARRAY DONDE SE LLENA VA LA LISTA
    //
     //   lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView item = (TextView) view.findViewById(R.id.tv_logro);
                Bundle bundle = new Bundle();
                bundle.putString("item", (String) item.getText());                         //ASI LE PASO VALORES AL FRAGMENT CON EL BUNDLE
                Log.d("DATOOO", (String) item.getText());                                  //PRINT DEL VALOR

                FragmentManager fragmentManager = getSupportFragmentManager();             //INSTANCIO EL FRAGMENT
                FragmentTransaction transaction = fragmentManager.beginTransaction();      //COMIENZO LA TRANSACCION
                Logros fragment = new Logros();                                            //INSTANCIO LA CLASE LOGRO QUE ES EL FRAGMENT
                fragment.setArguments(bundle);                                             //LE PASO EL BUNDLE
                transaction.add(R.id.gamificacion, fragment);                              //INDICO DONDE SE HARA LA TRANSACCION
                transaction.commit();                                                      //EJECUTO

            }
        });

        sendRequest(this);
      if(puntajeTotal != 0)
       TextViewpuntaje.setText(String.valueOf(puntajeTotal));

    }

    public void sendRequest(final Context context){
        StringRequest stringRequest = new StringRequest(URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      Log.d("onResponse()", response.toString());
                      showJSON(response, context);

                    }

                }, new Response.ErrorListener() {
            @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("onResponse()", error.toString());
                }
        });

        RequestQueue request = Volley.newRequestQueue(this);

        request.add(stringRequest);
        Log.d("objetooo", stringRequest.toString());

    }

    private void showJSON(String json, Context context){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        String[] puntajes = ParseJSON.puntaje;
        int puntaje1 = 0;
        for(int i=0; i< ParseJSON.puntaje.length; i++){
            puntaje1 = puntaje1 + Integer.parseInt(puntajes[i]);
            puntajeTotal = puntaje1;
        }
        String puntajeTotalS = String.valueOf(puntajeTotal);
        TextViewpuntaje = (TextView) ((Activity)context).findViewById(R.id.tv_puntos);
        TextViewpuntaje.setText("Puntos: " + puntajeTotalS);
        CustomList cl = new CustomList(this, ParseJSON.ids, ParseJSON.names,ParseJSON.descripciones, puntajeTotalS);

        lista.setAdapter(cl);
    }

    @Override
    public void onClick(View v) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Logros fragment = new Logros();
        transaction.add(R.id.gamificacion, fragment);
        transaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }




}
