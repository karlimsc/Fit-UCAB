package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.M02Exception;
import com.fitucab.ds1617b.fitucab.Helper.M02Util;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M02.M02AccountFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M02.M02HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import static android.support.test.InstrumentationRegistry.getContext;
import static com.fitucab.ds1617b.fitucab.Helper.ManagePreferences.getIdUser;

/**
 * Clase M02AccountFragment que maneja el fragmeto de perfil
 *
 * @author  Mario Salazar, Juan Mendez, David Garcia
 * @version  1.0
 */

public class M02HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView _tv_m02_nombre;
    private TextView _tv_m02_peso;
    private String TAG= "FitUCAB";
    private User user;
    private SharedPreferences preferences;
    private IpStringConnection ip= new IpStringConnection();
    private FragmentManager FM = getSupportFragmentManager();
    private int _id;
    private ManagePreferences bringid;


    /**
     * Void onCreate que genera la vista M02HomeActivity
     * @param  savedInstanceState el estado de la instancia
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m02_home);
        initcomponents();
    }

    /**
     * Void initcomponentes donde se inicializan todos los componentes
     * Vista donde se encuentran los bontones y componentes de la vista
     */
    private void initcomponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        _tv_m02_peso = (TextView) header.findViewById(R.id.tv_m02_peso);
        _tv_m02_nombre = (TextView) header.findViewById(R.id.tv_m02_nombre);

        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();
        fragmentToSwap = new M02HomeFragment();
        fragmentTransaction.replace(R.id.flContent_m02_home, fragmentToSwap).commit();
        toAskWebService();
    }

    /**
     * VOID toAskWebService que realiza las peticiones al webservice
     *
     */
    private void toAskWebService() {
        try {
            int id = getIdUser(this);
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            String webUrl= ip.getIp()+"M02Users/"+id;
            Log.i(TAG, "toAskWebService: "+webUrl);
            JsonObjectRequest jsonrequest= new  JsonObjectRequest(Request.Method.GET, webUrl,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i(TAG, "onResponse: "+response.toString());
                            setJsonView(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i(TAG, " ERROR"+ error.toString());
                }
            });

            requestQueue.add(jsonrequest);

            //throw new M02Exception();
        }
//        catch (M02Exception e){
//           e.printStackTrace();
//        }
        catch (NullPointerException e){

            e.printStackTrace();
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    /**
     * VOID setJsonView que setea todos los componentes de la vista con los valores
     * @param response Objeto Json que viene del webservice
     */
    private void setJsonView(JSONObject response) {
        try {

            String username= response.getString("user");

            int weight= response.getInt("weight");
            Log.i(TAG, "setJsonView e: "+ username+ " , "+weight);
            _tv_m02_nombre.setText(username);
            _tv_m02_peso.setText( "Peso: "+weight+" Kg");



            throw new M02Exception();
        }
        catch (M02Exception e){
            e.toString();
        }catch (NullPointerException e){

            e.printStackTrace();
        }catch (Exception e){

            e.printStackTrace();
        }

    }
    /**
     * VOID onBackPressed se instacia que va realizar la actividad si se presiona back
     *
     */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /**
     * VOID onNavigationItemSelected crea que realizara cada item de nav
     * @param item item del navigation
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_m02_home) {
            fragmentToSwap = new M02HomeFragment();
            fragmentTransaction.replace(R.id.flContent_m02_home, fragmentToSwap).commit();
            // Handle the camera action
        } else if (id == R.id.nav_m02_calories) {
            Intent myintent = new Intent(M02HomeActivity.this, M11Food.class);
            startActivity(myintent);

        } else if (id == R.id.nav_m02_training) {
//            Intent myintent = new Intent(M02HomeActivity.this, M06TrainingActivity.class);
//            startActivity(myintent);

        } else if (id == R.id.nav_m02_logout) {
            Intent myintent = new Intent(M02HomeActivity.this, M01LoginActivity.class);
            startActivity(myintent);

        }
        else if (id == R.id.nav_m02_account) {
            fragmentToSwap = new M02AccountFragment();
            fragmentTransaction.replace(R.id.flContent_m02_home, fragmentToSwap).commit();

        }else if (id == R.id.nav_m02_activitys) {

        }
        else if (id == R.id.nav_m02_challenges) {
            //            Intent myintent = new Intent(M02HomeActivity.this, M08ManagementChallengeActivity.class);
//            startActivity(myintent);

        }
        else if (id == R.id.nav_m02_friends) {
            Intent myintent = new Intent(M02HomeActivity.this, M03FriendsActivity.class);
            startActivity(myintent);
        }
        else if (id == R.id.nav_m02_gamification) {
            Intent myintent = new Intent(M02HomeActivity.this, M09GamificationActivity.class);
            startActivity(myintent);
        }
        else if (id == R.id.nav_m02_hydration) {
            Intent myintent = new Intent(M02HomeActivity.this, M10WaterGlassActivity.class);
            startActivity(myintent);
        }
        else if (id == R.id.nav_m02_training) {
            Intent myintent = new Intent(M02HomeActivity.this, M01LoginActivity.class);
            startActivity(myintent);

        }
        else if (id == R.id.nav_m02_planing_activitys) {

        } else if (id == R.id.nav_m02_notifications) {
            Intent myintent = new Intent(M02HomeActivity.this, M04NotificationActivity.class);
            startActivity(myintent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
