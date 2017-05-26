package com.example.juanmacedo.fitucab;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import org.w3c.dom.Text;


import java.util.ArrayList;

public class Gamificacion extends AppCompatActivity implements View.OnClickListener, 
        Logros.OnFragmentInteractionListener{

    TextView TextViewpuntaje;
    int puntajeTotal;
    public static int logrado;
    public static int noLogrado;
    private static String URL = "http://192.168.1.8:8080/FitUcabService_war_exploded/db/obtener";
    private static String URLtamaño = "http://192.168.43.152:8080/FitUcabService_war_exploded/dbgrafica/obtener";
    ItemAdapter itemAdapter;
    private ArrayList<String> _arrayList;
    private String[] _logros = {"5 Km recorrido", "10 km recorrido", 
            "Regimen Agua", "Gana a un amigo", "Invita a un amigo"};
    private ArrayAdapter<String> _adapter;
    private ListView _lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamificacion);
        TextViewpuntaje = (TextView) findViewById(R.id.tv_puntos);
        _lista = (ListView) findViewById(R.id.lista);
     //   arrayList = new ArrayList<String>();                                               //ARRAY DONDE SE LLENA VA LA LISTA
    //
     //   lista.setAdapter(adapter);




        ImageView _perfil = (ImageView) findViewById(R.id.profile_image);
        _perfil.setOnClickListener(this);
        TextView _nombreUsuarioVista = (TextView) findViewById(R.id.tv_nombreLabel);
        _nombreUsuarioVista.setOnClickListener(this);
        TextView _nivelVista = (TextView) findViewById(R.id.tv_nivelLabel);
        _nivelVista.setOnClickListener(this);
        TextView _puntajeVista = (TextView) findViewById(R.id.tv_puntos);
        _puntajeVista.setOnClickListener(this);


        //ARRAY DONDE SE LLENA VA LA LISTA
        _arrayList = new ArrayList<String>();
        _adapter = new ArrayAdapter<String>(this, R.layout.contenido,R.id.tv_logro, _logros);
        _lista.setAdapter(_adapter);

        _lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                TextView _item = (TextView) view.findViewById(R.id.tv_logro);
                Bundle _bundle = new Bundle();
                //ASI LE PASO VALORES AL FRAGMENT CON EL BUNDLE
                _bundle.putString("item", (String) _item.getText());
                //INSTANCIO EL FRAGMENT
                FragmentManager _fragmentManager = getSupportFragmentManager();
                //COMIENZO LA TRANSACCION
                FragmentTransaction _transaction = _fragmentManager.beginTransaction();
                //INSTANCIO LA CLASE LOGRO QUE ES EL FRAGMENT
                Logros _fragment = new Logros();
                //LE PASO EL BUNDLE
                _fragment.setArguments(_bundle);
                //INDICO DONDE SE HARA LA TRANSACCION
                _transaction.add(R.id.gamificacion, _fragment);
                //EJECUTO
                _transaction.commit();

            }
        });

        sendRequest(this);
     //   traerTamaños();

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

        _lista.setAdapter(cl);
    }

    public void traerTamaños(){
        StringRequest stringRequest = new StringRequest(URLtamaño,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("onResponse()", response.toString());
                        showJSON2(response);

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

    private void showJSON2(String json){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);

                logrado = Integer.parseInt(jsonObject.getString("Logrado"));
                noLogrado = Integer.parseInt(jsonObject.getString("NoLogrado"));

            } catch (JSONException e1) {
            e1.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {



        if ((v.getId()== R.id.profile_image) || (v.getId() == R.id.tv_nombreLabel)
                || (v.getId() == R.id.tv_nivelLabel) || (v.getId() == R.id.tv_puntos)){

            Intent _myIntent = new Intent(Gamificacion.this, GraficaNivel.class);
            _myIntent.putExtra("Logrado", logrado); //Optional parameters
            _myIntent.putExtra("NoLogrado", noLogrado); //Optional parameters
            startActivity(_myIntent);
        }
        else {
            FragmentManager _fragmentManager = getSupportFragmentManager();
            FragmentTransaction _transaction = _fragmentManager.beginTransaction();
            Logros _fragment = new Logros();
            _transaction.add(R.id.gamificacion, _fragment);
            _transaction.commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }




}
