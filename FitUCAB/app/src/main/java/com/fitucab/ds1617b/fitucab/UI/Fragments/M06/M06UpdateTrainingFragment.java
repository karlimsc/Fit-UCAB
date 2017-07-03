package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fitucab.ds1617b.fitucab.Helper.M06Util;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiClient;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiEndPointInterface;
import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.Model.Training;
import com.fitucab.ds1617b.fitucab.R;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fitucab.ds1617b.fitucab.Helper.M01Util.getInstaceDialog;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.showToast;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.validateExceptionMessage;

public class M06UpdateTrainingFragment extends Fragment {
    //ManagePreferences manageId = new ManagePreferences();
    //View rootView;

    private OnFragmentSwap _callBack;
    private Button _btn;
    private Button _btn2;
    private EditText _edittext;
    private View _view;
    ListView _listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private ArrayList <Activit> arrayListAdd;
    Context context;
    CheckBox _checkbox;
    TextView _textview;
    ActivitiesAdapter madapter;
    private RecyclerView recyclerView;
    private Training _Training;




    private ArrayAdapter <String> adapterAdd;




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {

            _callBack = (OnFragmentSwap) activity;

        }
        catch (ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }
    public M06UpdateTrainingFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _view =  inflater.inflate(R.layout.fragment_m06_update_training2, container, false);
        Bundle mBundle = this.getArguments();
        _Training = mBundle.getParcelable("training");
        setupViewValues();
        manageListView();
        manageChangeFragmentTraining();





        return _view;
    }

    /**
     * metodo de listener del boton agregar, para realizar el cambio al otro fragmento.
     */
    private void manageChangeFragmentTraining() {


        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                int id= preferences.getInt("idUser",0);
                _edittext =(EditText) _view.findViewById(R.id.update_activity);
                String name = _edittext.getText().toString();
                //int userId = manageId.getIdUser(rootView.getContext());
                String actividades = ActivitiesAdapter.activities;
                ActivitiesAdapter.activities = "";



                if (!name.equals(""))
                {
                    if (!actividades.equals(""))
                    {
                        getRetrofit(_Training.get_trainingId(), name, actividades);

                    }else{
                        Toast.makeText(getContext(),
                                "No has seleccionado las actividades que reemplazarán las viejas actividades",
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(),
                            "Falta escribir el nombre de la lista",
                            Toast.LENGTH_LONG).show();
                }
                if (!name.equals(""))
                {
                    if (!actividades.equals(""))
                    {


                    }
                }


            }
        });



        _btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("training",_Training);
                _callBack.onSwap("M06DetailsTrainingFragment",bundle);
            }
        });


    }


    /**
     * Prepara el componente de la vista
     */
    private void setupViewValues() {

        _edittext =(EditText) _view.findViewById(R.id.update_activity);
        _btn = (Button) _view.findViewById(R.id.Button);
        _btn2 = (Button) _view.findViewById(R.id.Button2);

    }

    public void getRetrofit(int id, String trainingname, String activities) {


        ApiEndPointInterface apiService = ApiClient.getClient().create(ApiEndPointInterface.class);
        Call<Training> call = apiService.updateTraining(id, trainingname,activities);

        final MaterialDialog dialog = getInstaceDialog(getContext());

        call.enqueue(new Callback<Training>() {

            @Override
            public void onResponse(Call<Training> call, Response<Training> response) {

                dialog.dismiss();

                try {

                    Training training = response.body();
                    _callBack.onSwap("M06HomeTrainingFragment",null);
                    Toast.makeText(getContext(), "Se ha modificado exitosamente el entrenamiento",
                            Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("No es problema de bd ni internet");

                }

            }
            @Override
            public void onFailure(Call<Training> call, Throwable t) {

                dialog.dismiss();
                String error = t.getMessage();
                String errorResult = "Ha ocurrido un error modificando";
                Toast.makeText(getContext(), errorResult,
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private void manageListView(){

        Activit [] activities = {new Activit(1,"Caminar", 1), new Activit(2,"Trotar", 1), new Activit(3,"Bicicleta", 1), new Activit(4,"Natacion", 1),
                new Activit(5,"Yoga", 1), new Activit(6,"Estiramientos", 1), new Activit(7,"Eliptica", 1), new Activit(8,"Escaleras", 1),
                new Activit(9,"Bailar", 1), new Activit(10,"Aerobic", 1), new Activit(11,"Remo", 1), new Activit(12,"Basketball", 1),
                new Activit(13,"Futbol", 1), new Activit(14,"Tenis", 1), new Activit(15,"Voleibol", 1)};

        arrayListAdd = new ArrayList<>(Arrays.asList(activities));
        madapter = new ActivitiesAdapter(arrayListAdd);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) _view.findViewById(R.id.m06_recycler_view) ;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(madapter);
        _edittext.setText(_Training.get_trainingName());






    }




}
