package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fitucab.ds1617b.fitucab.Helper.M06Util;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiClient;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiEndPointInterface;
import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.Model.Training;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;

import org.junit.runner.Describable;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fitucab.ds1617b.fitucab.Helper.M01Util.getInstaceDialog;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.showToast;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.validateExceptionMessage;

public class M06DetailsTrainingFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView _title;
    private View _view;
    private Training _Training;
    private OnFragmentSwap _callBack;
    private User user;

    public M06DetailsTrainingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle mBundle = this.getArguments();
        _Training = mBundle.getParcelable("training");

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
        _view =  inflater.inflate(R.layout.fragment_m06_details_training, container, false);
        setHasOptionsMenu(true);
        setupViewValues();
        manageRecyclerView();
        return _view;
    }


    /**
     * Method type: @Override Void
     * Method Name: onCreateOptionsMenu
     * Method @Params Menu menu, MenuInflater inflater
     * Method Description: this method makes a copnfiguration to MenuOptions
     * **/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_m06_training, menu);
        super.onCreateOptionsMenu(menu,inflater);
        setHasOptionsMenu(true);

    }

    /**
     * Method type: @Override Void
     * Method Name: onOptionsItemSelected
     * Method @Params Menu menu, MenuInflater inflater
     * Method Description: this method drive the click on the menuIcon
     * **/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //_Training.set_trainingId(505); //PRUEBA
        switch (id) {
            case R.id.m06_edit_training:
                Bundle bundle = new Bundle();
//                bundle.putInt("User", user.get_idUser());
                _callBack.onSwap("M06UpdateTraining",null);
                return true;

            case R.id.m06_active_training:
                // do stuff
                ///TODO CALL ACTIVE TRAINING
                //_callBack.onSwap();
                return true;

            case R.id.m06_delete_training:
                // do stuff
                ///TODO GO TO DELETE TRAINING
                getRetrofit(_Training.get_trainingId(), _Training.get_trainingName());
                //_callBack.onSwap();
                return true;

            case R.id.m06_share_training:
                // do stuff
                ///TODO GO TO SHARE
                _getRetrofit(_Training, 5000);
                //_callBack.onSwap();
                return true;
        }



        return false;
    }

    private void setupViewValues(){

        _title = (TextView) _view.findViewById(R.id.tv_m06_training_name);
        //_title.setText();
        getActivity().setTitle(_Training.get_trainingName());


    }

    public void swap(){
        Bundle bundle = new Bundle();
        bundle.putParcelable("training",_Training);
        _callBack.onSwap("M06UpdateTrainingFragment",bundle);
    }
    public void getRetrofit(int id, String trainingname) {


        ApiEndPointInterface apiService = ApiClient.getClient().create(ApiEndPointInterface.class);
        Call<Training> call = apiService.deleteTraining(id, trainingname);

        final MaterialDialog dialog = getInstaceDialog(getContext());

        call.enqueue(new Callback<Training>() {

            @Override
            public void onResponse(Call<Training> call, Response<Training> response) {

                dialog.dismiss();

                try {

                    _callBack.onSwap("M06HomeTrainingFragment",null);
                    Toast.makeText(getContext(),
                            "Se ha eliminado exitosamente el entrenamiento",
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
                String errorResult = "Ha ocurrido un error eliminando";
                Toast.makeText(getContext(), errorResult,
                        Toast.LENGTH_LONG).show();
            }
        });

    }
    public void _getRetrofit(Training t, int userShare) {


        ApiEndPointInterface apiService = ApiClient.getClient().create(ApiEndPointInterface.class);
        String _act = "";
        for (Activit a:_Training.get_activities() ) {
            _act += a.get_name() + ";";
        }
        Call<Training> call = apiService.shareTraining(_Training.get_trainingName(), _act, userShare);

        final MaterialDialog dialog = getInstaceDialog(getContext());

        call.enqueue(new Callback<Training>() {

            @Override
            public void onResponse(Call<Training> call, Response<Training> response) {

                dialog.dismiss();

                try {
                    Toast.makeText(getContext(),
                            "Se ha compartido exitosamente el entrenamiento",
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
                String errorResult = "Ha ocurrido un error compartiendo";
                Toast.makeText(getContext(), errorResult,
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private void manageRecyclerView(){

        ActivitiesAdapter mAdapter = new ActivitiesAdapter(_Training.get_activities());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) _view.findViewById(R.id.m06_activities_recycler) ;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }



}
