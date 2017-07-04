package com.fitucab.ds1617b.fitucab.UI.Activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Planification;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M01.M01HomeFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M01.M01LoginFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M01.M01RecoveryFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M01.M01SignUpFragment;

import static com.fitucab.ds1617b.fitucab.Helper.ManagePreferences.getIdUser;

public class M01LoginActivity extends AppCompatActivity implements OnFragmentSwap {

    private FragmentManager FM = getSupportFragmentManager();

    /**
     * Metodo que inicia para selecionar el fragmento al que se va a cambiar
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //Declaracion del Toolbar. si alguien necesita acceder al toolbar en un Fragment
        //debe declarar este atributo como privado y hacerle un Getter.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // int id= getIdUser(this);
       //if(id==0){
            //Seleccionamos el fragmento que queremos mostrar.
            onSwap("M01HomeFragment",null);
        //}
        //else{

         // onSwapActivity("M02HomeActivity",null);
        //}

    }

    /**
     * Metodo que realiza el cambio de fragmento.
     * @param fragmentName Recibe el nobmre del fragment (nombre de la clase)
     * @param bundle Recibe la data encapsulada que se le pasara al fragment, (puede ser null)
     */
    //cambiar fragment
    @Override
    public void onSwap(String fragmentName, Bundle bundle) {
        //Declaramos un Fragmento nulo (al que vamos a cambiar)
        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();

        try {
            switch (fragmentName) {
                case "M01HomeFragment":
                    fragmentToSwap = new M01HomeFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    break;

                case "M01SignUpFragment":
                    fragmentToSwap = new M01SignUpFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M01SignUpFragment");

                    break;

                case "M01LoginFragment":
                    fragmentToSwap = new M01LoginFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M01LoginFragment");

                    break;
                case "M01RecoveryFragment":
                    fragmentToSwap = new M01RecoveryFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M01RecoveryFragment");

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

    /**
     * Metodo que realiza cambio entre actividades, cambio a el home
     * @param activityName Nombre de la actividad
     * @param bundle
     */
    @Override
    public void onSwapActivity(String activityName, Bundle bundle) {

        switch (activityName){
            case "M02HomeActivity":
                Intent newActivity = new Intent(this, M02HomeActivity.class);
                startActivity(newActivity);
                //Esto es para que una vez cambie de actividad muera el login
                this.finish();
                break;

            case "M06TrainingActivity":
                Intent newActivity2 = new Intent(this, M06TrainingActivity.class);
                startActivity(newActivity2);
                //Esto es para que una vez cambie de actividad muera el login
                this.finish();
                break;


        }

    }

}
