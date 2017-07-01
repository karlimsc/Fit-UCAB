package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M07.M07ActivityFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M07.M07HomeFragment;

public class M07PlanificationActivity extends AppCompatActivity implements OnFragmentSwap{

    private FragmentManager FM = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m07_planification);
        onSwap("M07HomeFragment", null);

    }
    @Override
    public void onSwap(String fragmentName, Bundle bundle) {

        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();

        try {

            switch (fragmentName) {
                case "M07ActivityFragment":
                    fragmentToSwap = new M07ActivityFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    break;
                case "M07HomeFragment":
                    fragmentToSwap = new M07HomeFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    break;
            }
            fragmentTransaction.commit();
           // probando();
        }

        catch(Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void onSwapActivity(String activityName, Bundle bundle) {

    }
/**
    private void probando() {

        _btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M07ActivityFragment",null);
            }
        });
    }*/
}
