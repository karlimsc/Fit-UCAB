package com.example.juanmacedo.fitucab;

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

import java.util.ArrayList;

public class Gamificacion extends AppCompatActivity implements View.OnClickListener, 
        Logros.OnFragmentInteractionListener{

    private ArrayList<String> _arrayList;
    private String[] _logros = {"5 Km recorrido", "10 km recorrido", 
            "Regimen Agua", "Gana a un amigo", "Invita a un amigo"};
    private ArrayAdapter<String> _adapter;
    private ListView _lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamificacion);

        ImageView _perfil = (ImageView) findViewById(R.id.profile_image);
        _perfil.setOnClickListener(this);

        _lista = (ListView) findViewById(R.id.lista);
        //ARRAY DONDE SE LLENA VA LA LISTA
        _arrayList = new ArrayList<String>();
        _adapter = new ArrayAdapter<String>(this, R.layout.contenido,R.id.tv_logro, _logros);
        _lista.setAdapter(_adapter);

        _lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView item = (TextView) view.findViewById(R.id.tv_logro);
                Bundle bundle = new Bundle();
                //ASI LE PASO VALORES AL FRAGMENT CON EL BUNDLE
                bundle.putString("item", (String) item.getText());
                //PRINT DEL VALOR
                Log.d("DATOOO", (String) item.getText());
                //INSTANCIO EL FRAGMENT
                FragmentManager fragmentManager = getSupportFragmentManager();
                //COMIENZO LA TRANSACCION
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                //INSTANCIO LA CLASE LOGRO QUE ES EL FRAGMENT
                Logros fragment = new Logros();
                //LE PASO EL BUNDLE
                fragment.setArguments(bundle);
                //INDICO DONDE SE HARA LA TRANSACCION
                transaction.add(R.id.gamificacion, fragment);
                //EJECUTO
                transaction.commit();

            }
        });



    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.profile_image){


            Intent myIntent = new Intent(Gamificacion.this, GraficaNivel.class);
            //myIntent.putExtra("key", value); //Optional parameters
            Gamificacion.this.startActivity(myIntent);

            //Toast.makeText(Gamificacion.this, "Vas bien gafito",
            //        Toast.LENGTH_LONG).show();

        }
        else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Logros fragment = new Logros();
            transaction.add(R.id.gamificacion, fragment);
            transaction.commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
