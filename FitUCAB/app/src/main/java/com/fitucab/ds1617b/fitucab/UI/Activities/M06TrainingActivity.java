package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

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


public class M06TrainingActivity extends AppCompatActivity implements OnFragmentSwap {
    private FragmentManager FM = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m06_training);
        //Se declara el toolbar
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Entrenamiento");
        setSupportActionBar(toolbar);*/
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
}
