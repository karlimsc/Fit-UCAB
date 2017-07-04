package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.AdapterM10ListView;
import com.fitucab.ds1617b.fitucab.Model.Planification;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06AddTrainingFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06DetailsTrainingFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06HomeTrainingFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M06.M06UpdateTrainingFragment;
import java.util.ArrayList;
import java.util.Arrays;

public class  M06TrainingActivity extends AppCompatActivity implements OnFragmentSwap {
    private FragmentManager FM = getSupportFragmentManager();

    private ArrayList <String> arrayList;
    private ArrayAdapter<String> adapter;
    private ArrayList <String> arrayListAdd;
    private ArrayAdapter <String> adapterAdd;
    private FloatingActionButton _addButton;
    private OnFragmentSwap _callBack;
    ListView  _listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // secuencia que va a listar los entyrenamientos que tiene la persona cambiar solo item por lo recibido

       /* ListView listView=(ListView) findViewById(R.id.listoftrainings);
        String [] items = {"Apple", "Banana", "Grape"};
        arrayList= new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.fragment_m06_listview_item,R.id.tv_m06_training,arrayList);
        listView.setAdapter(adapter); */



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
                case "M06UpdateTrainingFragment":
                    fragmentToSwap = new M06UpdateTrainingFragment();
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
    public void onSwapData(String fragmentName, Bundle bundle, Planification planification) {

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
