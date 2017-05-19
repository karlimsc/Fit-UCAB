package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m06_training);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    @Override
    public void onSwap(String fragmentName, Bundle bundle) {
        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();

        try {
            switch (fragmentName) {
                case "M06HomeTrainingFragment":
                    fragmentToSwap = new M06HomeTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    break;
                case "M06AddTrainingFragment":
                    fragmentToSwap = new M06AddTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06AddTrainingTypePersonalizedFragment":
                    fragmentToSwap = new M06AddTrainingTypePersonalizedFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06AddTrainingFragment");
                    break;
                case "M06AddTrainingTypePredefinedFragment":
                    fragmentToSwap = new M06AddTrainingTypePredefinedFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06DetailsTrainingFragment":
                    fragmentToSwap = new M06DetailsTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06ShareTrainingFragment":
                    fragmentToSwap = new M06ShareTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06DeleteTrainingFragment":
                    fragmentToSwap = new M06DeleteTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06ModifySelectTrainingFragment":
                    fragmentToSwap = new M06ModifySelectTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.m06_fLayoutContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06ModifyTrainingFragment":
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


    @Override
    public void onSwapActivity(String activityName, Bundle bundle) {
        switch (activityName){
            case "MainActivity":
                Intent newActivity = new Intent(this, MainActivity.class);
                startActivity(newActivity);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
