package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.fitucab.ds1617b.fitucab.Model.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06AddTrainingFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06AddTrainingTypePersonalizedFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06AddTrainingTypePredefinedFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06DeleteTrainingFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06DetailsTrainingFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06HomeTrainingFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06ModifySelectTrainingFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06ModifyTrainingFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06ShareTrainingFragment;


public class M06TrainingActivity extends AppCompatActivity implements OnFragmentSwap,
                                NavigationView.OnNavigationItemSelectedListener{

    private FragmentManager FM = getSupportFragmentManager();
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m06_training);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Se configura el Drawer Layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //Se configura el Navigation View
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Se declara el toolbar
        toolbar.setTitle(R.string.M06_nombre_modulo);
        setSupportActionBar(toolbar);
        //Seleccionamos el fragmento que queremos mostrar.
        onSwap("M06HomeTrainingFragment",null);
    }

    /**
     * Metodo que realiza el cambio de fragmento.
     * @param fragmentName Recibe el nobmre del fragment (nombre de la clase)
     * @param bundle Recibe la data encapsulada que se le pasara al fragment, (puede ser null)
     */

    @Override
    public void onSwap(String fragmentName, Bundle bundle) {
        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();

        try {
            switch (fragmentName) {
                case "M06HomeTrainingFragment":
                    toolbar.setTitle(R.string.M06_nombre_modulo);
                    setSupportActionBar(toolbar);
                    fragmentToSwap = new M06HomeTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    break;
                case "M06AddTrainingFragment":
                    toolbar.setTitle(R.string.M06_title_activity_opcion_agregar_entrenamiento);
                    setSupportActionBar(toolbar);
                    fragmentToSwap = new M06AddTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06AddTrainingTypePersonalizedFragment":
                    toolbar.setTitle(R.string.M06_title_activity_opcion_agregar_entrenamiento_personalizado);
                    setSupportActionBar(toolbar);
                    fragmentToSwap = new M06AddTrainingTypePersonalizedFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06AddTrainingFragment");
                    break;
                case "M06AddTrainingTypePredefinedFragment":
                    toolbar.setTitle(R.string.M06_title_activity_opcion_agregar_entrenamiento_predefinido);
                    setSupportActionBar(toolbar);
                    fragmentToSwap = new M06AddTrainingTypePredefinedFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06DetailsTrainingFragment":
                    toolbar.setTitle(R.string.M06_title_detalle_entrenamiento);
                    setSupportActionBar(toolbar);
                    fragmentToSwap = new M06DetailsTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06ShareTrainingFragment":
                    toolbar.setTitle(R.string.M06_compartir_entrenamiento);
                    setSupportActionBar(toolbar);
                    fragmentToSwap = new M06ShareTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06DeleteTrainingFragment":
                    toolbar.setTitle(R.string.M06_title_eliminar_entrenamiento);
                    setSupportActionBar(toolbar);
                    fragmentToSwap = new M06DeleteTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06ModifySelectTrainingFragment":
                    toolbar.setTitle(R.string.M06_title_seleccionar_entrenamiento);
                    setSupportActionBar(toolbar);
                    fragmentToSwap = new M06ModifySelectTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06ModifyTrainingFragment":
                    toolbar.setTitle(R.string.M06_title_modificar_entrenamiento);
                    setSupportActionBar(toolbar);
                    fragmentToSwap = new M06ModifyTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06ModifyTrainingFragment");
                    break;
            }
            fragmentTransaction.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Usado para cambiar de actividad/modulo
     * @param activityName
     * @param bundle
     */

    @Override
    public void onSwapActivity(String activityName, Bundle bundle) {
        switch (activityName){
            case "M02HomeActivity":
                Intent newActivity = new Intent(this, M02HomeActivity.class);
                startActivity(newActivity);
                break;
        }
    }

    /**
     * Metodo por el agarra el boton del navigation drawer el cual es seleccionado para luego
     * cambiar entre actividades
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_m06_home) {
            onSwapActivity("M02HomeActivity",null);
        } else if (id == R.id.nav_m06_calories) {

        } else if (id == R.id.nav_m06_training) {

        } else if (id == R.id.nav_m06_logout) {

        } else if (id == R.id.nav_m06_account) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Metodo que coloca en la toolbar el menu con lo que contiene el layout
     * activity_m06_home_options_menu ubicado en /menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_m06_home_options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.m06_activity_agregar_entrenamiento:
                onSwap("M06AddTrainingFragment",null);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
