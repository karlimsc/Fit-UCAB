package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Planification;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M01.M01SignUpFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M11.M11DietFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M11.M11FoodFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M11.M11FoodhomeFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M11.M11GraphicFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M11.M11SuggestionFragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;

import android.content.Intent;


public class M11Food extends AppCompatActivity implements OnFragmentSwap {

    private FragmentManager FM = getSupportFragmentManager();

    /**
     * Metodo para iniciar la actividad con el fragmento de inicio.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m11_food);
        onSwap("M11FoodHomeFragment",null);
    }

    /**
     * Metodo para hacer el cambio entre fragmentos con la actividad anfitriona.
     * @param fragmentName Recibe el nobmre del fragment (nombre de la clase)
     * @param bundle Recibe la data encapsulada que se le pasara al fragment, (puede ser null)
     */
    @Override
    public void onSwap(String fragmentName, Bundle bundle) {

        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();

        try {

            switch (fragmentName) {
                case "M11FoodHomeFragment":
                    fragmentToSwap = new M11FoodhomeFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    break;

                case "M11DietFragment":
                    fragmentToSwap = new M11DietFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M11DietFragment");

                    break;

                case "M11FoodFragment":
                    fragmentToSwap = new M11FoodFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M11FoodFragment");

                    break;

                case "M11GraphicFragment":
                    fragmentToSwap = new M11GraphicFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M11GraphicFragment");

                    break;

                case "M11SuggestionFragment":
                    fragmentToSwap = new M11SuggestionFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M11SuggestionFragment");

                    break;


            }

            fragmentTransaction.commit();

        }

        catch(Exception e){
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
