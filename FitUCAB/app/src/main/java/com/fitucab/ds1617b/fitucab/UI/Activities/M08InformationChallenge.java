package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitucab.ds1617b.fitucab.R;

import static android.R.id.list;

/**
 * Created by noe on 27/5/2017.
 */

/**
 * clase que maneja toda la parte de la informacion de retos
 */
public class M08InformationChallenge extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private TextView tituloNombreReto;
    private TextView textViewDescripcionReto;
    private TextView descripcionReto;
    private TextView textViewRetoActivo;
    private TextView textViewRetoActivoSN;
    private TextView textViewTipoReto;
    private TextView textViewInfTipoReto;
    private TextView textViewKm;
    private TextView kilometros;
    private TextView textViewDiasReto;
    private TextView textViewInfDiasReto;
    private Button buttonEmpezarAhora;


    private View view;
    private LayoutInflater inflater;

    /**
     * método que se llama cuando se crea la actividad
     * @param savedInstanceState se usa para inicializar la creación de la interfaz de usuario.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m08_information_challenge);
        // Esto debes aplicarlo a todas las vistas
        inflater = LayoutInflater.from(this);

        // Hasta aqui
        ;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tituloNombreReto = (TextView)findViewById(R.id.tituloNombreReto);
        textViewDescripcionReto = (TextView)findViewById(R.id.textViewDescripcionReto);
        descripcionReto = (TextView)findViewById(R.id.descripcionReto);
        textViewRetoActivo = (TextView)findViewById(R.id.textViewRetoActivo);
        textViewRetoActivoSN = (TextView)findViewById(R.id.textViewRetoActivoSN);
        textViewTipoReto = (TextView)findViewById(R.id.textViewTipoReto);
        textViewInfTipoReto = (TextView)findViewById(R.id.textViewInfTipoReto);
        textViewKm = (TextView)findViewById(R.id.textViewKm);
        kilometros = (TextView)findViewById(R.id.kilometros);
        textViewDiasReto = (TextView)findViewById(R.id.textViewDiasReto);
        textViewInfDiasReto = (TextView)findViewById(R.id.textViewInfDiasReto);
        buttonEmpezarAhora = (Button)findViewById(R.id.buttonEmpezarAhora);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //view = inflater.inflate(R.layout.activity_main_m08_challenge,drawer,true);

        buttonEmpezarAhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String respuesta = PeticionActivarRetoPredefinido();
                Toast activarReto = Toast.makeText(getApplicationContext(), "Se ha activado el reto exitosamente", Toast.LENGTH_LONG);
                activarReto.show();
            }
        });

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }//cierre del void conCreate

    private String PeticionActivarRetoPredefinido() {

        return null;
    }


    /**
     * menu lateral
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }//cierre del onNavigationItemSelected


}//cierre de la clase M08InformationChallenge