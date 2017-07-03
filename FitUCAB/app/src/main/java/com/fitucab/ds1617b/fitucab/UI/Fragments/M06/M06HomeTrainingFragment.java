package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiClient;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiEndPointInterface;
import com.fitucab.ds1617b.fitucab.Model.Training;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fitucab.ds1617b.fitucab.Helper.M01Util.getInstaceDialog;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.showToast;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.validateExceptionMessage;
import static com.fitucab.ds1617b.fitucab.Model.MockM06.getMockTraining;


public class M06HomeTrainingFragment extends Fragment {

    private View _view;
    private OnFragmentSwap _callBack;
    private ImageView _btnAddTraining;
    ArrayList<Training> mTrainings;
    private RecyclerView recyclerView;
    private TrainingAdapter mAdapter;
    private User user;
    public M06HomeTrainingFragment() {

    }

    //En el callBack guardo la instancia de la aplicacion
    /**
     * Una vez la activity llama a un fragment se ejecuta este metodo
     * @param activity recibe la activity que llamo o instancio al fragment
     */
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view =  inflater.inflate(R.layout.fragment_m06_home_training, container, false);
        getActivity().setTitle("Listado de entrenamientos.");

        mTrainings= getMockTraining();
        manageRecyclerView();
        setupViewValues();
        manageChangeFragmentTraining();
        getRetrofit(user.get_idUser());
        return _view;
    }


    /**
     * metodo de listener del boton agregar, para realizar el cambio al otro fragmento.
     */
    private void manageChangeFragmentTraining() {

        _btnAddTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M06AddTrainingFragment",null);
            }
        });
    }

    /**
     * Prepara el componente de la vista
     */
    private void setupViewValues() {

        _btnAddTraining = (ImageView) _view.findViewById(R.id.btn_m06_addtraining);

    }

    private void manageRecyclerView(){

        mAdapter = new TrainingAdapter(mTrainings,_callBack);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) _view.findViewById(R.id.m06_recycler_view) ;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void getRetrofit(int userId){

           ApiEndPointInterface apiService= ApiClient.getClient().create(ApiEndPointInterface.class);
             Call<ArrayList<Training>> call= apiService.getAllTraining(1);

             final MaterialDialog dialog =getInstaceDialog(getContext());

             call.enqueue(new Callback<ArrayList<Training>>() {

                 @Override
                 public void onResponse(Call<ArrayList<Training>> call, Response<ArrayList<Training>> response) {

                     dialog.dismiss();

                     try{

                         ArrayList<Training> trainings = response.body();

                     }
                     catch (Exception e){
                         e.printStackTrace();
                         System.out.println("No es problema de bd ni internet");
                     }
                 }
                 @Override
                 public void onFailure(Call<ArrayList<Training>> call, Throwable t) {

                     dialog.dismiss();
                     String error=t.getMessage();
                     String errorResult= validateExceptionMessage(error,getContext());
                     showToast(getContext(),errorResult);
                 }
             });
    }
}
