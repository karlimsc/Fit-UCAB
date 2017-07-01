package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.AdapterM10ListView;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06AddTrainingFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06DetailsTrainingFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06HomeTrainingFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class M06TrainingActivity extends AppCompatActivity implements OnFragmentSwap {
    private FragmentManager FM = getSupportFragmentManager();

    private ArrayList <String> arrayList;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_m06_home_training);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView=(ListView) findViewById(R.id.listoftrainings);
        String [] items = {"Apple", "Banana", "Grape"};
        arrayList= new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.fragment_m06_listview_item,R.id.tv_m06_training,arrayList);
        listView.setAdapter(adapter);
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
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    break;
                case "M06AddTrainingFragment":
                    fragmentToSwap = new M06AddTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
                    break;
                case "M06DetailsTrainingFragment":
                    fragmentToSwap = new M06DetailsTrainingFragment();
                    fragmentToSwap.setArguments(bundle);
                    fragmentTransaction.replace(R.id.flContent, fragmentToSwap);
                    fragmentTransaction.addToBackStack("M06HomeTrainingFragment");
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
            case "M09GamificationActivity":
                Intent newActivity = new Intent(this, M09GamificationActivity.class);
                startActivity(newActivity);
                //Esto es para que una vez cambie de actividad muera el login
                this.finish();
                break;

        }

    }
}
