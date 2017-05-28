package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Model.Challenge;
import com.fitucab.ds1617b.fitucab.Model.Food;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static android.R.id.content;
import static android.R.id.list;


/**
 * clase principal de la aplicacion
 */
public class M08_ManagementChallenge extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    /**
     * método que se llama cuando se crea la actividad
     * @param savedInstanceState se usa para inicializar la creación de la interfaz de usuario.
     */
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private ArrayAdapter<String> adapter;
    private ListView list;
    private LayoutInflater inflater;
    private View view;
    private ArrayList<Challenge> challenges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m08_challenge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Esto debes aplicarlo a todas las vistas
        inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.activity_main_m08_challenge,drawer,true);
        // Hasta aqui

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Llena la lista challenges con todos los retos predefinidos
        PeticionRetosPredefinidos();
        String[] items = {"correr 100 km", "trotar 10 km","caminar 5 km"};
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, items);
        list = (ListView)findViewById(R.id.listViewRetos);
        list.setAdapter(adapter);

        PeticionRetosPredefinidos();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // La posición donde se hace clic en el elemento de lista se obtiene de la
                // la posición de parámetro de la vista de lista de Android
                int posicion = position;

                //obtengo el valor del string del elemento donde se hizo clic
                String itemValue = (String) list.getItemAtPosition(position);

                //Con el fin de empezar a mostrar una nueva actividad lo que necesitamos es una intención
                Intent intent = new Intent(M08_ManagementChallenge.this,M08InformationChallenge.class);

                // Aquí pasaremos el parámetro de la intención creada previamente
                startActivity(intent);
            }//cierre del onItemClickListener
        });//cierre del setOnItemClickListener


    }//cierre del metodo onCreate

    private void mostrarListView(ArrayList<Challenge> challenges) {


    }

    public void PeticionRetosPredefinidos()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        //OJO esta no es la consulta en si, hay que colocar la que es de los personalizados......
        //La haré mañana temprano.
        String jsonURL = "http://127.0.0.1:8080/WebServicesFitUCAB_war_exploded/" +
                "M08_Gestion_retos/getPredefinedChallenges";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        challenges = new ArrayList<>();
                        challenges = gson.fromJson(response, new TypeToken<ArrayList<Food>>(){}.getType());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(view.getContext(), "No existen Retos predefinidos", Toast.LENGTH_LONG);
                    }
                });
        requestQueue.add(stringRequest);
    }






    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_challenge, menu); //muestra el menu seleccionado
    }//cierre del metodo onCreateContextMenu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_m08, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

