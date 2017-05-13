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
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.contenido,R.id.tv_logro, logros);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView item = (TextView) view.findViewById(R.id.tv_logro);
                Bundle bundle = new Bundle();
                bundle.putString("item", (String) item.getText());
                Log.d("DATOOO", (String) item.getText());

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Logros fragment = new Logros();
                fragment.setArguments(bundle);
                transaction.add(R.id.gamificacion, fragment);
                transaction.commit();

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
