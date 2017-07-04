package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.Model.Planification;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M05.M05ModifyFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M05.M05PrincipalActivityFragment;

/**
 * Se implementa como View.OnClickListener para accionar los Dialgos
 */

public class M05PrincipalActivity extends AppCompatActivity implements OnFragmentSwap {

    private FragmentManager FM = getSupportFragmentManager();

    public static Activit _activit  ;

    public static Activit get_activit() {
        return _activit;
    }

    public static void set_activit(Activit _activit) {
        M05PrincipalActivity._activit = _activit;
    }

    public M05PrincipalActivity() {

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m05_princ);
        //Declaracion del Toolbar. si alguien necesita acceder al toolbar en un Fragment
        //debe declarar este atributo como privado y hacerle un Getter.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_m05);
        setSupportActionBar(toolbar);

        //Seleccionamos el fragmento que queremos mostrar.
        onSwap("M05PrincipalActivityFragment",null);
    }

    @Override
    public void onSwap(String fragmentName, Bundle bundle) {
        //Declaramos un Fragmento nulo (al que vamos a cambiar)
        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();

        try {
            switch (fragmentName) {
                case "M05PrincipalActivityFragment":
                    fragmentToSwap = new M05PrincipalActivityFragment();
                    fragmentToSwap.setArguments(bundle);
                     fragmentTransaction.replace(R.id.flContent_m05, fragmentToSwap);
                    break;

                case "M05ModifyFragment":
                    fragmentToSwap = new M05ModifyFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent_m05, fragmentToSwap);
                    break;

            }
            fragmentTransaction.commit();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onSwapData(String fragmentName, Bundle bundle, Planification planification) {

    }

    @Override
    public void onSwapActivity(String activityName, Bundle bundle) {

    }



}


