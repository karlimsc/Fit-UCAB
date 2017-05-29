package com.fitucab.ds1617b.fitucab.UI.Fragments.M05;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.OnFragmentSwap;
import com.fitucab.ds1617b.fitucab.Helper.Rest.VolleySingleton;
import com.fitucab.ds1617b.fitucab.Model.Sport;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Activities.M05StartTrackingActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by karo on 13/04/17.
 */

public class M05TrackActivityFragment extends Fragment implements OnMapReadyCallback,
        ListView.OnItemClickListener {

    private OnFragmentSwap _callBack;
    private GoogleMap mMap;
    private static View _view;
    private Sport mSport = new Sport();
    private User mUser = new User();
    private IpStringConnection baseIp = new IpStringConnection();
    private Button _startTracking;
    private ListView _sportsListView;
    private ArrayList<String> mSportsbyUser = new ArrayList<>();
    private String _mSportName;


    //Constructor
    public M05TrackActivityFragment (){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (_view != null) {
            ViewGroup parent = (ViewGroup) _view.getParent();
            if (parent != null)
                parent.removeView(_view);
        }
        try {
            _view = inflater.inflate(R.layout.fragment_m05_track_activity, container, false);
            initArguments();
            inflateFragment();

        } catch (InflateException e) {
        /* map is already there, just return view as it is */

        }

        return _view;
    }

    View.OnClickListener startTracking = new View.OnClickListener(){
        public void onClick(View v) {

            Intent intent = new Intent(getContext(), M05StartTrackingActivity.class);
            intent.putExtra("user", mUser);
            intent.putExtra("sport", mSport);
            startActivity(intent);

        }
    };

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public User activityTestUser(){
        User mUser = new User();
        mUser.set_idUser(1);
        mUser.set_weight((float) 63.7);
        return mUser;
    }

    public void requestSportbyName(final String sportName) {
        final String URL = baseIp.getIp() + "M05_ServicesSport/getMetSport?nameSpo=" + sportName;
        Log.i("SPORTNAME",_mSportName);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        mSport = gson.fromJson(response,Sport.class);
                        mSport.setName(sportName);

                        Log.i("Nombre", response.toString());
                        Log.i("ID", String.valueOf(mSport.getId()));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(_view.getContext(), "Hola, no devolvio nada", Toast.LENGTH_LONG);
                    }
                });
// Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(this.getContext()).addToRequestQueue(stringRequest);
    }




    public void inflateFragment(){
        _startTracking = (Button) _view.findViewById(R.id.bt_m05_startTracking);
        _sportsListView = (ListView) _view.findViewById( R.id.lv_m05_SportsListView );
        fillSportsListView();
        _startTracking.setOnClickListener(startTracking);
    }

    public void initArguments(){
        mUser = activityTestUser();
        //requestSportsbyId(mUser.get_idUser());

    }

    public void fillSportsListView(){

        final Gson gson = new Gson();
        String URL = baseIp.getIp() + "M05_ServicesSport/getSportsUser?idPer="+String.valueOf(mUser.get_idUser());


        //Se hace la peticion y lo devuelve en String Request
        StringRequest stringRequest = new StringRequest( Request.Method.GET , URL ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Type mSportsbyUser= new TypeToken<ArrayList<String>>(){}.getType();
                        ArrayList<String> _mSportsbyUser = gson.fromJson(response, mSportsbyUser);
                          Log.i("ENTRE",response.toString());
                        Log.i("COLE",mSportsbyUser.toString());
                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, _mSportsbyUser);
                        _sportsListView.setAdapter( adaptador );
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ArrayList<String> mSportsbyUser = new ArrayList<String>();
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, mSportsbyUser);
                _sportsListView.setAdapter( adaptador );
                mSportsbyUser.add( "Fallo la conexión intente mas tarde");

            }
        });
        // Add the request to the RequestQueue.
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        _sportsListView.setOnItemClickListener( this );
    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         _mSportName = parent.getItemAtPosition(position).toString();
        requestSportbyName(_mSportName);
    }
}
