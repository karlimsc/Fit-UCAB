package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fitucab.ds1617b.fitucab.Helper.FormatUtility;
import com.fitucab.ds1617b.fitucab.Helper.GeoLocalization.GeoLocalization;
import com.fitucab.ds1617b.fitucab.Helper.Rest.M05_RequestList;
import com.fitucab.ds1617b.fitucab.Helper.Rest.VolleySingleton;
import com.fitucab.ds1617b.fitucab.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONObject;

import java.util.ArrayList;

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
    private long timeWhenStopped = 0;
    private FormatUtility formatUtility = new FormatUtility();


    //Atributos para Geolocalización

    private ArrayList<Location> LocationPoints;
    private float distance = 0;
    private LocationRequest mLocationRequest = new LocationRequest();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m05_start_tracking);
        M05_RequestList requestList = new M05_RequestList(this.getApplicationContext(),
                "http://10.0.2.2:8888/Prueba_war_exploded/M05_ServicesSport/getSport?idSpo=1");

        LocationPoints = new ArrayList<>();

        M05_textview_time = (Chronometer) findViewById(R.id.tv_m05_tiempo);
        M05_textview_time_tag = (TextView) findViewById(R.id.tv_m05_tiempo_tag);
        M05_textview_speed = (TextView) findViewById(R.id.tv_m05_velocidad);
        M05_textview_speed_tag = (TextView) findViewById(R.id.tv_m05_velocidad_tag);
        M05_textview_km = (TextView) findViewById(R.id.tv_m05_km);
        M05_textview_km_tag = (TextView) findViewById(R.id.tv_m05_km_tag);
        M05_button_pause = (Button) findViewById(R.id.btn_m05_pause_track);
        M05_button_resume = (Button) findViewById(R.id.btn_m05_resume_track);
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
        makeRequest();

    }

    /**
     * Listen when the button is pressed and makes actions.
     */
    View.OnClickListener pause = new View.OnClickListener() {
        public void onClick(View v) {
            timeWhenStopped = M05_textview_time.getBase() - SystemClock.elapsedRealtime();
            M05_textview_time.stop();
            M05_button_resume.setVisibility(View.VISIBLE);

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
     * Create the location request and set the parameters.
     */



    /**
     * Actualiza la interfaz.
     */
    public void updateUI() {


        super.updateLocationPoints(LocationPoints);

        if (LocationPoints!=null) {

            Location mPrevLocation;

            int lastIndex = LocationPoints.size() - 1;

            //Log.i("CURRENT LOCATION", mCurrentLocation.toString());

            if (LocationPoints.size() >= 2) {
                mPrevLocation = LocationPoints.get(lastIndex - 1);
                distance = distance + mPrevLocation.distanceTo(LocationPoints.get(lastIndex));
                M05_textview_km.setText(formatUtility.fmt(distance));
            }

        }
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
        updateUI();
    }

    public void makeRequest()
    {

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, VolleySingleton.getStringConn(), (String)null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i("RESPONSE",response.toString());
                                M05_textview_speed_tag.setText(response.toString());
                                // mTxtDisplay.setText("Response: " + response.toString());
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.i("no trajo nada","");

                    }
                });
// Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }

}

