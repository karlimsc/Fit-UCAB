package com.example.juanmacedo.fitucab;

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

import java.util.ArrayList;

public class Gamificacion extends AppCompatActivity implements View.OnClickListener, Logros.OnFragmentInteractionListener{

    private ArrayList<String> arrayList;
    private String[] logros = {"5 Km recorrido", "10 km recorrido", "Regimen Agua", "Gana a un amigo", "Invita a un amigo"};
    private ArrayAdapter<String> adapter;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamificacion);

        lista = (ListView) findViewById(R.id.lista);
        arrayList = new ArrayList<String>();                                               //ARRAY DONDE SE LLENA VA LA LISTA
        adapter = new ArrayAdapter<String>(this, R.layout.contenido,R.id.tv_logro, logros);
        lista.setAdapter(adapter);

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
