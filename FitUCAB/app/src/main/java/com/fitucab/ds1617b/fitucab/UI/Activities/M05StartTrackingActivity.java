package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.FormatUtility;
import com.fitucab.ds1617b.fitucab.Helper.GeoLocalization.GeoLocalization;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.Helper.ManagePreferences;
import com.fitucab.ds1617b.fitucab.Helper.Rest.VolleySingleton;
import com.fitucab.ds1617b.fitucab.Model.Activit;
import com.fitucab.ds1617b.fitucab.Model.Global;
import com.fitucab.ds1617b.fitucab.Model.Sport;
import com.fitucab.ds1617b.fitucab.Model.User;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M05.M05UrlConsul;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class M05StartTrackingActivity extends GeoLocalization implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private SimpleDateFormat sdfDate;

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


    public M05StartTrackingActivity(Sport sport, User user){
        this.sport  = sport;
        this.user   = user;
        this.mets   = sport.getMets();
        this.weight = user.get_weight();

    }


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
            otroInsert(activity);
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
        activity.set_starsite(LocationPoints.get(0).toString());
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

        sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        sdf = new SimpleDateFormat("HH:mm:ss");
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

        Log.i("SPORT",sport.getName());
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
            activity.set_endsite(LocationPoints.get(LocationPoints.size()-1).toString());
            activity.set_endtime(String.valueOf(sdf.format(new Date())));
            activity.set_date(getCurrentTime().toString());
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
        float kcal = mets * weight;
        return kcal;
    }



    public void insertActivityRequest(final Activit activity){
        Log.i("TRACE", "INERT ACTIVITY REQUEST");

        //  M05UrlConsul m05IP = new M05UrlConsul();

        final String URL = baseIp.getIp() + "M05_ServicesSport/insertActivity";

        Gson gson = new Gson();


        String jsonActivity = gson.toJson(activity);

        Log.i("START", String.valueOf(activity.get_starsite()));
        Log.i("CALOR", String.valueOf(activity.get_calor()));
        Log.i("DATE", String.valueOf(activity.get_date()));
        Log.i("ENDSITE", String.valueOf(activity.get_endsite()));
        Log.i("ENDTIME", String.valueOf(activity.get_endtime()));
        Log.i("KM", String.valueOf(activity.get_km()));
        Log.i("NAME", String.valueOf(activity.get_name()));
        Log.i("STARTTIME", String.valueOf(activity.get_startime()));


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    Toast.makeText(M05StartTrackingActivity.this,response,Toast.LENGTH_LONG).show();
                }
            },
            new Response.ErrorListener() {
                @Override
                 public void onErrorResponse(VolleyError error) {
                    Toast.makeText(M05StartTrackingActivity.this,"NO INSERTÓ" + error.toString(),Toast.LENGTH_LONG).show();
                 }
             }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put ("activitystarttime",activity.get_startime());
                params.put ("activityendtime",activity.get_endtime());
                params.put("activitydate", activity.get_date());
                params.put("activitykm",String.valueOf(activity.get_km()));
                params.put("activitycalor", String.valueOf(activity.get_calor()));
                params.put("activitystartsite", activity.get_starsite());
                params.put("activityendsite", activity.get_endsite());
                params.put("fk_registry", String.valueOf(user.get_idUser()));
                params.put("fk_sport", String.valueOf(sport.getId()));
                params.put("fk_training", String.valueOf(1));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }

        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    /*private void makeRequestInsert(Training entrenamiento , String nivelEntrenamiento, String tipoEntrenamiento,
                                   int tiempo, int tipo) {

        IpStringConnection ip = new IpStringConnection();
        String url = ip.getIp() + "M05_S/createTrainingPredefinedTime";
        url = url + "?nombreEntrenamiento=" + entrenamiento.getTrainingName() + "&nivelEntrenamiento=" + nivelEntrenamiento;
        url = url + "&tipoEntrenamiento=" + tipoEntrenamiento + "&calorias=" + entrenamiento.getTrainingCalories();
        url = url + "&periodicidad=" + entrenamiento.getTrainingPeriod() + "&tiempo=" + tiempo + "&deporte=" + tipo;
        url = url + "&userId=" + ManagePreferences.getIdUser( getContext() );
        RequestQueue queue = Volley.newRequestQueue( getContext() );
        //Se hace la peticion y lo devuelve en String Request
        StringRequest stringRequest = new StringRequest( Request.Method.GET , url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){

                        boolean confirmacion = true;
                        confirmacion = Boolean.parseBoolean(response);

                        //Confimo si pudo insertar en la bdd
                        if ( confirmacion == true){

                            Toast.makeText( getContext() , R.string.M06_entrenamiento_creado_exito, Toast.LENGTH_SHORT).show();
                            _callBack.onSwap( "M06HomeTrainingFragment" , null );

                        }else{

                            Toast.makeText( getContext() , R.string.M06_entrenamiento_creado_fallo, Toast.LENGTH_SHORT).show();

                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText( getContext() , "Error connection" , Toast.LENGTH_SHORT).show();

            }
        });
        // Add the request to the RequestQueue.
        queue.add( stringRequest );
    }*/

    public void otroInsert(Activit activity){
        final String URL = baseIp.getIp() + "M05_ServicesActivity/insertActivity";
// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put ("horainicial",activity.get_startime());
        params.put ("horafinal",activity.get_endtime());
        params.put("fecha", activity.get_date());
        params.put("km",String.valueOf(activity.get_km()));
        params.put("calorias", String.valueOf(activity.get_calor()));
        params.put("lugarinicial", activity.get_starsite());
        params.put("lugarfinal", activity.get_endsite());
        params.put("idReg", String.valueOf(user.get_idUser()));
        params.put("idSpo", String.valueOf(sport.getId()));

        JsonObjectRequest request_json = new JsonObjectRequest(Request.Method.POST,URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("INSTÖ","PUES Sí");
                            throw new JSONException("I am Exception Alpha!");
                            //Process os success response

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed
        VolleySingleton.getInstance(this).addToRequestQueue(request_json);

    }

}

