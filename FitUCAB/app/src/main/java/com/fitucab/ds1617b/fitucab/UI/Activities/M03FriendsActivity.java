package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.provider.ContactsContract;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import com.fitucab.ds1617b.fitucab.Model.PagerAdapter;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;


import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;

import android.view.View;
import android.widget.AdapterView;

import android.widget.Toast;

import com.fitucab.ds1617b.fitucab.R;

import static com.fitucab.ds1617b.fitucab.R.id.contactsList;

/**
 En esta actividad tenemos los tabs para poder movernos dentro .

 */
public class M03FriendsActivity extends AppCompatActivity
       /* implements NavigationView.OnNavigationItemSelectedListener*/ {

    private ListView mListView;
/**
    Es llamado cuando el fragmento es creado por primera vez
    @param savedInstanceState este parametro es un objeto que recibe informacion previa

   */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m03_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

       /* NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

       /*
        TabLayout proporciona un diseño horizontal para mostrar pestañas.
        Se instancia TabLayout con el id de la interfaz ya creada.
        Crea tabulaciones a través de newTab (). Desde allí usamos el setText para colocar nuestros
        strings.xml , que agregamos con el metodo addTab que se encarga de mostrar la pestaña
        */

       TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_03_amigos));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_03_libreta));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_03_cercademi));

        // este es el modo que nuestro tab sea constante y se llena tanto como sea posible

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        //se instancia el viewpager y el adapter
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        //Unca clase que contiene las llamadas necesarias de nuevo al tabLayout proporcionado para
        // que la posición se mantenga sincronizada.

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            /**
             Estos son los controladores del metodo setOnTabSelectedListener
             el metodo(OnTabSelectedListener) sirve para notificar cuando se ha cambiado el estado
             de selección de una pestaña
             @param tab es el tab que estamos usando.
            */
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(M03FriendsActivity.this, M02HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
