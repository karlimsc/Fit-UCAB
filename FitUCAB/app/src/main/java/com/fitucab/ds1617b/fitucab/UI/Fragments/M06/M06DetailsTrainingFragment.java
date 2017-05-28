package com.fitucab.ds1617b.fitucab.UI.Fragments.M06;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Model.Sport;
import com.fitucab.ds1617b.fitucab.Model.Training;
import com.fitucab.ds1617b.fitucab.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alejandro Fernandez on 24/4/2017.
 */

public class M06DetailsTrainingFragment extends Fragment {
    private ListView _listView;
    private View _view;
    private OnFragmentSwap _callBack;
    private ArrayAdapter<String> _adaptador;
    private Toolbar _toolbar;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            _callBack = (OnFragmentSwap) activity;
        } catch (ClassCastException e) {


            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_m06_details_training_fragment, container, false);
        fillListView();
        return _view;
    }

    private void setupViewValues() {
        //Llenando el list View

    }

    public void fillListView(){

        //Url a la cual se va a hacer conexion
        IpStringConnection ip = new IpStringConnection();
        Bundle bundle = getArguments();
        String url = ip.getIp() + "M06_ServicesTraining/detailedTraining?trainingId=" + bundle.getInt("idEntrenamiento");
        final Gson gson = new Gson();

        // Instancia RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        _listView = (ListView) _view.findViewById( R.id.m06_listViewEntrenamiento );

        //Se hace la peticion y lo devuelve en String Request
        StringRequest stringRequest = new StringRequest( Request.Method.GET , url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Si hay conexion al web services
                        ArrayList<String> deportes = new ArrayList<String>();
                        ArrayList<Sport> at = new ArrayList<Sport>();
                        at = gson.fromJson( response , new TypeToken< List< Sport > >(){}.getType() );
                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, deportes );
                        _listView.setAdapter( adaptador );

                        //Hago el for para meter todo en el sport y asi pasarlo al listview
                        for(int i = 0;i<at.size();i++){
                            deportes.add( at.get(i).getName() );
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //En caso de que no haya conexión al SW
                ArrayList<String> deporte = new ArrayList<String>();
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, deporte );
                _listView.setAdapter( adaptador );
                deporte.add( "Fallo la conexión intente mas tarde");

            }
        });
        // Add the request to the RequestQueue.
        queue.add( stringRequest );
    }
}
