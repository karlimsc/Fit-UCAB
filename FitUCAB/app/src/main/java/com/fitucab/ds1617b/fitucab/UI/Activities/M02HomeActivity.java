package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M01.M01HomeFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M02.M02AccountFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M02.M02HomeFragment;

public class M02HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager FM = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m02_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();
        fragmentToSwap = new M02HomeFragment();
        fragmentTransaction.replace(R.id.flContent_m02_home, fragmentToSwap).commit();
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

        } else if (id == R.id.nav_m02_training) {

        } else if (id == R.id.nav_m02_logout) {

        } else if (id == R.id.nav_m02_account) {
            fragmentToSwap = new M02AccountFragment();
            fragmentTransaction.replace(R.id.flContent_m02_home, fragmentToSwap).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
