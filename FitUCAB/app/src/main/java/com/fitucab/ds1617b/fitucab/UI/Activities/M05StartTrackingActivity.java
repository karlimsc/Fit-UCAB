package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
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
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.Rest.VolleySingleton;
import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.Model.Sport;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M05.M05UrlConsul;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class M05StartTrackingActivity extends GeoLocalization implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private SimpleDateFormat sdfDate;
    private int idSport;


    public M05StartTrackingActivity() {

    }

    //Atributos para la interfaz

    private Chronometer M05_textview_time;
    private TextView    M05_textview_time_tag;
    private TextView    M05_textview_speed;
    private TextView    M05_textview_speed_tag;
    private TextView    M05_textview_km_tag;
    private TextView    M05_textview_km;
    private Button      M05_button_pause;
    private Button      M05_button_resume;
    private Button      M05_button_end;
    private long        timeWhenStopped = 0;

    Activit activity;

    private FormatUtility formatUtility = new FormatUtility();

    private String exception;

    private ArrayList<Location> LocationPoints;
    private ArrayList<Long> TimePassed;
    private ArrayList<Double> velocidadPromedio;
    private float mets;
    private float weight;
    SimpleDateFormat sdf;
    String currentDateandTime;

    private Sport sport;
    private User user;

    private float distance = 0;
    private double velocidad = 0;

    IpStringConnection baseIp = new IpStringConnection();

    Bundle mExtras;
    Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m05_start_tracking);

        mIntent = getIntent();

        initArguments();

        inflateInterface();

        // Update values using data stored in the Bundle.
        // updateValuesFromBundle(savedInstanceState);

        //Check for Location Permissions
        super.checkLocationPermission();

        //Creates GoogleAPIClient Instance
        super.instanceGoogleApiClient();

        //Creates a Location Request.
        super.createLocationRequest();

    }


    /**
     * Listen when the button is pressed and makes actions.
     */
    View.OnClickListener pause = new View.OnClickListener() {
        public void onClick(View v) {
            timeWhenStopped = M05_textview_time.getBase() - SystemClock.elapsedRealtime();
            M05_textview_time.stop();
            M05_button_end.setVisibility(View.VISIBLE);
            M05_button_pause.setVisibility(View.INVISIBLE);
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
            M05_button_end.setVisibility(View.INVISIBLE);
            M05_button_pause.setVisibility(View.VISIBLE);
        }
    };

    /**
     * Listen when the button is pressed and makes actions.
     */
    View.OnLongClickListener end = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
                String crar = createActivity();
                Log.i("RESCREAR",crar);
                //insertActivityRequest(activity);
            insertActivityRequest(activity);
            return false;
        }
    };


    /**Métodos Geolocalización**/


    /**
     * To request the last known location, call the getLastLocation() method,
     * passing it your instance of the GoogleApiClient object.
     *
     * @param connectionHint
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        super.startLocationUpdates();
        super.checkLastLocation(LocationPoints);
        if (LocationPoints!=null)
            activity.set_starsite(String.valueOf(LocationPoints.get(LocationPoints.size()-1).getLatitude())+","+
                    String.valueOf(LocationPoints.get(LocationPoints.size()-1).getLongitude()));
    }


    /**
     * Método que pertenece a la interfaz.
     *
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
     *
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

        if (LocationPoints != null) {

            Location mPrevLocation;

            int lastIndexLP = LocationPoints.size() - 1;
            int lastIndexTP = TimePassed.size()     - 1;

            if (LocationPoints.size() >= 2 && TimePassed.size() >= 2) {

                mPrevLocation       = LocationPoints.get(lastIndexLP - 1);
                float lastDistance  = mPrevLocation.distanceTo(LocationPoints.get(lastIndexLP));

                distance = distance + lastDistance;

                M05_textview_km.setText(formatUtility.fmt(distance));

                double time = TimePassed.get(lastIndexTP - 1) - TimePassed.get(lastIndexTP);
                velocidad   = calculateSpeed(lastDistance, time);
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

    /**
     * Cambio de locacion
     * @param location
     */

    @Override
    public void onLocationChanged(Location location) {

        super.onLocationChanged(location);
        super.getLocationPoints(LocationPoints);
        TimePassed.add(M05_textview_time.getBase() - SystemClock.elapsedRealtime());
        updateUI();

    }


    public void requestInsertActivity() {
        VolleySingleton.getInstance(this);
    }


    /**
     * Inicializa los elementos de la interfaz.
     */
    public void inflateInterface() {

        M05_textview_time        = (Chronometer) findViewById(R.id.tv_m05_tiempo);
        M05_textview_time_tag    = (TextView)    findViewById(R.id.tv_m05_tiempo_tag);
        M05_textview_speed       = (TextView)    findViewById(R.id.tv_m05_velocidad);
        M05_textview_speed_tag   = (TextView)    findViewById(R.id.tv_m05_velocidad_tag);
        M05_textview_km          = (TextView)    findViewById(R.id.tv_m05_km);
        M05_textview_km_tag      = (TextView)    findViewById(R.id.tv_m05_km_tag);
        M05_button_pause         = (Button)      findViewById(R.id.btn_m05_pause_track);
        M05_button_resume        = (Button)      findViewById(R.id.btn_m05_resume_track);
        M05_button_end           = (Button)      findViewById(R.id.btn_m05_end_track);

        M05_button_pause.setOnClickListener(pause);
        M05_button_resume.setOnClickListener(resume);
        M05_button_end.setOnLongClickListener(end);

        //Desde donde inicia el cronómetro.
        M05_textview_time.setBase(SystemClock.elapsedRealtime());
        M05_textview_time.start();
    }

    /**
     * Inicializa las variables de la clase.
     */
    public void initArguments() {

        sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        sdf = new SimpleDateFormat("dd/MM/yyyy%20HH:mm:ss");
        LocationPoints = new ArrayList<>();
        TimePassed = new ArrayList<>();
        velocidadPromedio = new ArrayList<>();
        currentDateandTime = sdf.format(new Date());

        activity = new Activit();

        activity.set_date(String.valueOf(currentDateandTime));
        activity.set_startime(String.valueOf(sdf.format(new Date())));
        mExtras = mIntent.getExtras();
        sport = (Sport) mExtras.get("sport");
        user = (User) mExtras.get("user");

        idSport = sport.getId();
        mets = sport.get_met();
        weight = user.get_weight();

        Log.i("SPORT",sport.getName());
        Log.i("SPORT",String.valueOf(sport.get_met()));
        Log.i("SPORT IDDD",String.valueOf(sport.getId()));
        Log.i("USER", String.valueOf(user.get_idUser()));
    }

    /**
     * Obtiene la fecha y hora actual.
     * @return fecha y hora actual.
     */
    public SimpleDateFormat getCurrentTime() {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        System.out.println(sdf.format(cal.getTime()));
        return sdf;

    }

    /**
     * Método que calcula la velocidad promedio durante la actividad.
     * @return Velocidad promedio.
     */
    public double calculateAverageSpeed(){
        double ave = 0;

        for (int i = 0; i<velocidadPromedio.size(); i++){
            ave = ave + velocidadPromedio.get(i);
        }

        return ave/velocidadPromedio.size();
    }

    public String createActivity(){
        try {
            activity.set_endsite(String.valueOf(LocationPoints.get(LocationPoints.size()-1).getLatitude())+","+
                                 String.valueOf(LocationPoints.get(LocationPoints.size()-1).getLongitude()));
            activity.set_endtime(String.valueOf(sdf.format(new Date())));
            activity.set_date(String.valueOf(sdfDate.format(new Date())));
            activity.set_km(distance);
            activity.set_calor(calculateCalories(mets,weight));
            activity.set_name(sport.getName());

            return "Objeto Creado";
        }
        catch (Exception e){
            return "NO CREÓ EL ACTIVITY" + e.toString();
        }
    }

    public float calculateCalories(float mets, float weight) {
        Log.i("METVCALO", String.valueOf(mets));
        Log.i("WEIGHTVCALO", String.valueOf(weight));
        float kcal = mets * weight * (float)(0.0175) * ((SystemClock.elapsedRealtime()-M05_textview_time.getBase())/60);

        return kcal;
    }



    public void insertActivityRequest(Activit activity){
        Log.i("TRACE", "INERT ACTIVITY REQUEST");

        //  M05UrlConsul m05IP = new M05UrlConsul();

        final String URL = baseIp.getIp() + "M05_ServicesActivity/"+M05UrlConsul._urlInsertAct(activity.get_startime(),
                activity.get_endtime(),activity.get_starsite(),activity.get_endsite(),activity.get_date(),
                String.valueOf(activity.get_km()),String.valueOf(activity.get_calor()), user.get_idUser(),sport.getId());

        Gson gson = new Gson();


        final StringRequest stringRequest = new StringRequest
                (Request.Method.GET, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.e("RESPONSE ", response);
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.i("no trajo nada","");

                    }
                });

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }


    public Activit getActivity(){
        return this.activity;
    }

}

