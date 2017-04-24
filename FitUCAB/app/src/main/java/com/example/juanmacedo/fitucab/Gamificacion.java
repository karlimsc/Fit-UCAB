package com.example.juanmacedo.fitucab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Gamificacion extends AppCompatActivity {

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
    }
}
