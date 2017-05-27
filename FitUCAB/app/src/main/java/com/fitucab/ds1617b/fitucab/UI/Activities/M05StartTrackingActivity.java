package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.location.Location;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fitucab.ds1617b.fitucab.Helper.FormatUtility;
import com.fitucab.ds1617b.fitucab.Helper.GeoLocalization.GeoLocalization;
import com.fitucab.ds1617b.fitucab.Helper.Rest.VolleySingleton;
import com.fitucab.ds1617b.fitucab.Model.Global;
import com.fitucab.ds1617b.fitucab.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;

public class M05StartTrackingActivity extends GeoLocalization implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    //Atributos para la interfaz

    private Chronometer M05_textview_time;
    private TextView M05_textview_time_tag;
    private TextView M05_textview_speed;
    private TextView M05_textview_speed_tag;
    private TextView M05_textview_km_tag;
    private TextView M05_textview_km;
    private Button M05_button_pause;
    private Button M05_button_resume;
    private Button M05_button_end;
    private long timeWhenStopped = 0;
    private FormatUtility formatUtility = new FormatUtility();



    private ArrayList<Location> LocationPoints;
    private ArrayList<Long> TimePassed;
    private ArrayList<Double> velocidadPromedio;
    private float distance = 0;
    private double velocidad=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m05_start_tracking);

        LocationPoints = new ArrayList<>();
        TimePassed = new ArrayList<>();
        velocidadPromedio = new ArrayList<>();

        M05_textview_time = (Chronometer) findViewById(R.id.tv_m05_tiempo);
        M05_textview_time_tag = (TextView) findViewById(R.id.tv_m05_tiempo_tag);
        M05_textview_speed = (TextView) findViewById(R.id.tv_m05_velocidad);
        M05_textview_speed_tag = (TextView) findViewById(R.id.tv_m05_velocidad_tag);
        M05_textview_km = (TextView) findViewById(R.id.tv_m05_km);
        M05_textview_km_tag = (TextView) findViewById(R.id.tv_m05_km_tag);
        M05_button_pause = (Button) findViewById(R.id.btn_m05_pause_track);
        M05_button_resume = (Button) findViewById(R.id.btn_m05_resume_track);
        M05_button_end = (Button) findViewById(R.id.btn_m05_resume_track);
        M05_button_pause.setOnClickListener(pause);
        M05_button_resume.setOnClickListener(resume);


        //Desde donde inicia.
        M05_textview_time.setBase(SystemClock.elapsedRealtime());
        M05_textview_time.start();



        // Update values using data stored in the Bundle.
       // updateValuesFromBundle(savedInstanceState);

        //Check for Location Permissions
        super.checkLocationPermission();

        //Creates GoogleAPIClient Instance
        super.instanceGoogleApiClient();

        //Creates a Location Request.
        super.createLocationRequest();

        //M05_textview_speed_tag.setText(requestList.makeRequest());
        requestSportsListbyUser();

    }


    /**
     * Listen when the button is pressed and makes actions.
     */
    View.OnClickListener pause = new View.OnClickListener() {
        public void onClick(View v) {
            timeWhenStopped = M05_textview_time.getBase() - SystemClock.elapsedRealtime();
            M05_textview_time.stop();
            M05_button_resume.setVisibility(View.VISIBLE);
            M05_button_end.setVisibility(View.VISIBLE);
            M05_button_pause.setVisibility(View.INVISIBLE);

        }
    };

    /**
     * Listen when the button is pressed and makes actions.
     */
    View.OnClickListener resume = new View.OnClickListener() {
        public void onClick(View v) {
            M05_textview_time.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            M05_textview_time.start();
            M05_button_resume.setVisibility(View.INVISIBLE);
        }
    };


    /**Métodos Geolocalización**/


    /**
     * To request the last known location, call the getLastLocation() method,
     * passing it your instance of the GoogleApiClient object.
     * @param connectionHint
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        super.startLocationUpdates();
        super.checkLastLocation(LocationPoints);
    }


    /**
     * Método que pertenece a la interfaz.
     * @param i
     */
    @Override
    public void onConnectionSuspended(int i) {
        super.onConnectionSuspended(i);
    }

    /**
     * To connect, call connect() from the activity's onStart() method.
     */
    protected void onStart() {
        super.onStart();
    }


    /**
     * To disconnect, call disconnect() from the activity's onStop() method.
     */
    protected void onStop() {
        super.onStop();
    }


    /**
     * Método que pertenece a la interfaz.
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
    }

    /**
     * Actualiza la interfaz.
     */
    public void updateUI() {


        super.updateLocationPoints(LocationPoints);

        if (LocationPoints!=null) {

            Location mPrevLocation;

            int lastIndexLP = LocationPoints.size() - 1;
            int lastIndexTP = TimePassed.size() - 1;

            //Log.i("CURRENT LOCATION", mCurrentLocation.toString());

            if (LocationPoints.size() >= 2 && TimePassed.size()>=2) {
                mPrevLocation = LocationPoints.get(lastIndexLP - 1);
                float lastDistance = mPrevLocation.distanceTo(LocationPoints.get(lastIndexLP));
                distance = distance + lastDistance;
                M05_textview_km.setText(formatUtility.fmt(distance));

                double time = TimePassed.get(lastIndexTP-1)-TimePassed.get(lastIndexTP);
                velocidad = calculateSpeed(lastDistance,time);
                velocidadPromedio.add(velocidad);
                M05_textview_speed.setText(formatUtility.fmt((velocidad)));
            }

        }
    }

    public double calculateSpeed(float distance, double time) {
        return (double) distance / time;
    }





    /**
     * Cuando la actividad entra en pausa.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Cuando reanuda la actividad.
     */
    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onLocationChanged(Location location){
        super.onLocationChanged(location);
        super.getLocationPoints(LocationPoints);
        TimePassed.add(M05_textview_time.getBase() - SystemClock.elapsedRealtime());
        updateUI();
    }

    public void requestSportsListbyUser()
    {
        VolleySingleton.getInstance(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, VolleySingleton.getStringConn(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Collection<String> sports = new ArrayList<String>();
                            sports = gson.fromJson(response, new TypeToken<Collection<String>>(){}.getType());
                            Log.i("Nombre",sports.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(_view.getContext(), "Hola, no devolvio nada", Toast.LENGTH_LONG);
                    }
                });
// Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }



}

