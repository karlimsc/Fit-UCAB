package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiClient;
import com.fitucab.ds1617b.fitucab.Helper.Rest.ApiEndPointInterface;
import com.fitucab.ds1617b.fitucab.Model.Training;
import com.fitucab.ds1617b.fitucab.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fitucab.ds1617b.fitucab.Helper.M01Util.getInstaceDialog;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.showToast;
import static com.fitucab.ds1617b.fitucab.Helper.M01Util.validateExceptionMessage;


public class M06AddTrainingFragment extends Fragment {

    private OnFragmentSwap _callBack;
    private FloatingActionButton _btn;
    private EditText _edittext;
    private View _view;
    ListView _listView;


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
    public M06AddTrainingFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _view =  inflater.inflate(R.layout.fragment_m06_add_training, container, false);
        setupViewValues();
        manageListView();

        return _view;
    }

    /**
     * metodo de listener del boton agregar, para realizar el cambio al otro fragmento.
     */
    private void manageChangeFragmentTraining() {
        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _callBack.onSwap("M06HomeTrainingFragment",null);
            }
        });


    }
    /**
     * Prepara el componente de la vista
     */
    private void setupViewValues() {

       _edittext =(EditText) _view.findViewById(R.id.new_activity);
       _btn = (FloatingActionButton) _view.findViewById(R.id.floatingActionButton);

    }

    public void getRetrofit() {


        ApiEndPointInterface apiService = ApiClient.getClient().create(ApiEndPointInterface.class);
        Call<Training> call = apiService.getAllTraining(1);

        final MaterialDialog dialog = getInstaceDialog(getContext());

        call.enqueue(new Callback<Training>() {

            @Override
            public void onResponse(Call<Training> call, Response<Training> response) {

                dialog.dismiss();

                try {

                    Training training = response.body();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("No es problema de bd ni internet");

                }

            }
            @Override
            public void onFailure(Call<Training> call, Throwable t) {

                dialog.dismiss();
                String error = t.getMessage();
                String errorResult = validateExceptionMessage(error, getContext());
                showToast(getContext(), errorResult);
            }
        });

    }

    private void manageListView(){

        _listView = (ListView) _view.findViewById(R.id.listofactivities);


    }




}
