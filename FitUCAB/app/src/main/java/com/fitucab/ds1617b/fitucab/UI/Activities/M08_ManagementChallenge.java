package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.ContextMenu;
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

import com.fitucab.ds1617b.fitucab.R;

import static android.R.id.list;


/**
 * clase principal del modulo 8
 */
public class M08_ManagementChallenge extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    /**
     * método que se llama cuando se crea la actividad
     * @param savedInstanceState se usa para inicializar la creación de la interfaz de usuario.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_m08_challenge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String[] items = {"correr 100 km", "trotar 10 km","caminar 5 km"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, items);
        final ListView list = (ListView)findViewById(R.id.listViewRetos);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // La posición donde se hace clic en el elemento de lista se obtiene de la
                // la posición de parámetro de la vista de lista de Android
                int posicion = position;

                //obtengo el valor del string del elemento donde se hizo clic
                String itemValue = (String) list.getItemAtPosition(position);

                //Con el fin de empezar a mostrar una nueva actividad lo que necesitamos es una intención
                Intent intent = new Intent(M08_ManagementChallenge.this, M08InformationChallenge.class);

                // Aquí pasaremos el parámetro de la intención creada previamente
                startActivity(intent);
            }//cierre del onItemClickListener
        });//cierre del setOnItemClickListener

        registerForContextMenu(list);
    }//cierre del metodo onCreate


    /**
     * vuelve a la Actividad o Fragmento anterior al que el usuario se encuentra en el momento
     *
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }//cierre del if (drawer.isDrawerOpen(GravityCompat.START))
        else {
            super.onBackPressed();
        }//cierre del else
    }//cierre del metodo onBackPressed


    /**
     * sobreescribe en la actividad el evento encargado de construir los menús contextuales asociados
     a los diferentes controles de la aplicación
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_challenge, menu); //muestra el menu seleccionado
    }//cierre del metodo onCreateContextMenu


    /**
     * Agrega el ítem (+) en el action bar
     * @param menu
     *
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_m08, menu);
        return true;
    }//cierre del metodo onCreateOptionsMenu


    /**
     * Redirige al usuario a la vista de agregar un reto
     * @param item icono de selección
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_m08) {
            startActivity(new Intent(this, M08AddChallenge.class));
            return true;
        }//cierre if (id == R.id.action_settings)

        return super.onOptionsItemSelected(item);
    }//cierre del metodo onOptionsItemSelected


    /**
     * menu lateral
     * @param item
     * @return
     */
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
    }//cierre del metodo onNavigationItemSelected
}//cierre de la class MainActivity

