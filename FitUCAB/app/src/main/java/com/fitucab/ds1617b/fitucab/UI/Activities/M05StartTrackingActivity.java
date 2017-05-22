package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.location.Location;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.fitucab.ds1617b.fitucab.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.Date;

public class M05StartTrackingActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private Chronometer M05_textview_time;
    private TextView M05_textview_time_tag;
    private TextView M05_textview_speed;
    private TextView M05_textview_speed_tag;
    private TextView M05_textview_km_tag;
    private TextView M05_textview_km;
    private Button M05_button_pause;
    private Button M05_button_resume;
    private long timeWhenStopped = 0;


    //Atributos para Geolocalización

    private static final String TAG = M05GetLocationActivity.class.getSimpleName();
    private static final int REQUEST_CHECK_SETTINGS = 100;
    private static final String REQUESTING_LOCATION_UPDATES_KEY = "requesting-location-updates";
    private static final String LOCATION_KEY = "location";
    private static final String LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string";
    private TextView latituteField;
    private TextView longitudeField;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private GoogleApiClient mGoogleClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private String mLastUpdateTime;
    private TextView mLastUpdateTimeTextView;
    private boolean mRequestingLocationUpdates;
    private LocationSettingsRequest mLocationSettingsRequest;
    private ArrayList<LatLng> LocationPoints;
    private Marker mCurrentLocationMarker;
    private Marker mLastLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m05_start_tracking);

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

        M05_textview_time.setBase(SystemClock.elapsedRealtime());
        M05_textview_time.start();

        mRequestingLocationUpdates = false;
        mLastUpdateTime = "";

        // Update values using data stored in the Bundle.
        updateValuesFromBundle(savedInstanceState);

        //Creates GoogleAPIClient Instance
        instanceGoogleAPIClient();

        //Creates a Location Request.
        createLocationRequest();

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
        CheckLastLocation();

        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    /**
     * Método que pertenece a la interfaz.
     * @param i
     */
    @Override
    public void onConnectionSuspended(int i) {

    }

    /**
     * To connect, call connect() from the activity's onStart() method.
     */
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }


    /**
     * To disconnect, call disconnect() from the activity's onStop() method.
     */
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    /**
     * Método que pertenece a la interfaz.
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    /**
     * Create the location request and set the parameters.
     */
    protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    /**
     * Prompt the User to Change Location Settings.
     */
    private void checkSettings() {
        /**
         * Add the location request that was created in the previous step.
         */
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        /**
         * Next check whether the current location settings are satisfied.
         */
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleClient,
                        builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(TAG, "All location settings are satisfied.");

                        //setLocationListener();
                        break;

                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user a dialog.
                        Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                "location settings ");
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(M05StartTrackingActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.e(TAG, e.getLocalizedMessage());
                        }
                        break;

                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        String errorMessage = "Location settings are inadequate, and cannot be " +
                                "fixed here. Fix in Settings.";
                        Log.e(TAG, errorMessage);
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't animateIn the dialog.
                        break;
                }
            }
        });
    }

    /**
     * Start Location Updates
     **/
    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    /**
     * Detecs if location changed.
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        Log.i("LOCATION", "CHANGE");
        mCurrentLocation = location;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        }
        updateUI();
    }

    /**
     * Actualiza la interfaz con los valores que va leyendo.
     */
    private void updateUI() {
        LocationPoints.add(new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude()));
    }


    /**
     * Gets Devices's last location.
     */
    private void CheckLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        if (mLastLocation != null) {
            LocationPoints.add(new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude()));
        } else {
            checkSettings();
            return;
        }
    }

    /**
     *Creates an instance of GoogleAPIClient.
     */
    public void instanceGoogleAPIClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    /**
     * Cuando se detenga la actividad.
     */
    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    /**
     * Stops location Updates.
     */
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    /**
     * Cuando re-arranca la actividad.
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected() && !mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    /**
     * Lee el estado anterior de la actividad.
     * @param savedInstanceState
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY,
                mRequestingLocationUpdates);
        savedInstanceState.putParcelable(LOCATION_KEY, mCurrentLocation);
        savedInstanceState.putString(LAST_UPDATED_TIME_STRING_KEY, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Lee el estado anterior de la actividad.
     * @param savedInstanceState
     */
    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // Update the value of mRequestingLocationUpdates from the Bundle, and
            // make sure that the Start Updates and Stop Updates buttons are
            // correctly enabled or disabled.
            if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(
                        REQUESTING_LOCATION_UPDATES_KEY);
                //setButtonsEnabledState();
            }

            // Update the value of mCurrentLocation from the Bundle and update the
            // UI to show the correct latitude and longitude.
            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
                // Since LOCATION_KEY was found in the Bundle, we can be sure that
                // mCurrentLocationis not null.
                mCurrentLocation = savedInstanceState.getParcelable(LOCATION_KEY);
            }

            // Update the value of mLastUpdateTime from the Bundle and update the UI.
            if (savedInstanceState.keySet().contains(LAST_UPDATED_TIME_STRING_KEY)) {
                mLastUpdateTime = savedInstanceState.getString(
                        LAST_UPDATED_TIME_STRING_KEY);
            }
            updateUI();
        }
    }
}
