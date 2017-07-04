package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
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
import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.Model.Training;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
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
        //manageRecyclerView();
        setupViewValues();
        fetchUserTrainings(null);


        manageChangeFragmentTraining();
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


    public void getRetrofit() {


            ApiEndPointInterface apiService = ApiClient.getClient().create(ApiEndPointInterface.class);
            Call<ArrayList<Training>> call = apiService.getAllTraining(1);

            final MaterialDialog dialog = getInstaceDialog(getContext());

            call.enqueue(new Callback<ArrayList<Training>>() {

                @Override
                public void onResponse(Call<ArrayList<Training>> call, Response<ArrayList<Training>> response) {

                    dialog.dismiss();

                    try {

                        ArrayList<Training> training = response.body();
                        System.out.println("hola mundo");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("No es problema de bd ni internet");

                    }


                }

                @Override
                public void onFailure(Call<ArrayList<Training>> call, Throwable t) {
                    dialog.dismiss();
                    String error = t.getMessage();
                    String errorResult = validateExceptionMessage(error, getContext());
                    showToast(getContext(), errorResult);

                }

            });

        }



    private void manageRecyclerView(){

        mAdapter = new TrainingAdapter(mTrainings,_callBack);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) _view.findViewById(R.id.m06_recycler_view) ;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    private void fetchUserTrainings(final RequestParams params){
        RequestParams test = new RequestParams();
        test.put("userId",1);
        AsyncHttpClient client = new AsyncHttpClient();
        System.out.println("Parametros:");
        //System.out.println(params.toString());
        client.post("http://192.168.0.107:8080/fitucab/M06_ServicesTraining/getAllTraining?userId=1", test, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse((new String(responseBody)));

                    ArrayList<Training> trainingList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonElement json_data = jsonArray.get(i);
                        JsonObject obj = json_data.getAsJsonObject();
                        String tName = obj.get("_trainingName").getAsString();
                        int idTrain = obj.get("_id").getAsInt();
                        JsonArray activities = obj.get("_activitiesList").getAsJsonArray();
                        ArrayList<Activit> trainingActivities = new ArrayList<>();
                        for (int j = 0; j < activities.size(); j++) {

                            JsonElement json_data2 = activities.get(j);
                            JsonObject obj2 = json_data2.getAsJsonObject();
                            String actName = obj2.get("_name").getAsString();
                            int actdur = obj2.get("_duration").getAsInt();
                            int idact = obj2.get("_id").getAsInt();

                            Activit act = new Activit(idact,actName,actdur);
                            trainingActivities.add(act);

                        }
                        Training training = new Training(idTrain,tName,trainingActivities);
                        trainingList.add(training);
                        System.out.println("hola");
                    }
                    mTrainings = trainingList ;
                    manageRecyclerView();
                    // ArrayList<Training> training = response.body();
                    System.out.println("hola mundo");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("No es problema de bd ni internet");

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("No es problema de bd ni internet");

            }


        });
    }

}
