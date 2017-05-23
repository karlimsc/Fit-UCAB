package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
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
import android.widget.Toast;

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
import com.google.android.gms.maps.GoogleMap;
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
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    //private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private String mLastUpdateTime;
    private boolean mRequestingLocationUpdates;
    private LocationSettingsRequest mLocationSettingsRequest;
    private ArrayList<Location> LocationPoints;
    private float distance = 0;
    private Marker mCurrentLocationMarker;
    private Marker mLastLocationMarker;
    private LocationManager locationManager;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private GoogleMap mMap;
    private LocationRequest mLocationRequest = new LocationRequest();

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

        //Desde donde inicia.
        M05_textview_time.setBase(SystemClock.elapsedRealtime());
        M05_textview_time.start();

        mRequestingLocationUpdates = false;
        mLastUpdateTime = "";

        // Update values using data stored in the Bundle.
       // updateValuesFromBundle(savedInstanceState);

        //Check for Location Permissions
        checkLocationPermission();

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
        startLocationUpdates();
        CheckLastLocation();

       /* startLocationUpdates();
        if (mRequestingLocationUpdates) {
            Log.i("TRACE","Start Location Updates");
            startLocationUpdates();
        }*/

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
        Log.i("TRACE","Crate Location Request");

        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    /**
     * Prompt the User to Change Location Settings.

    private void checkSettings() {
        /**
         * Add the location request that was created in the previous step.

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        /**
         * Next check whether the current location settings are satisfied.

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
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
    }*/

    /**
     * Start Location Updates
     **/
    protected void startLocationUpdates() {
        Log.i("TRACE","Start Location Updates Método");
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mGoogleApiClient!=null)
                if (mLocationRequest!=null)
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                    mLocationRequest, this);
                else
                    Log.i("TRACE","location request null");
            else
                    Log.i("TRACE","googl api null");
        }
        else {
            Log.i("TRACE","No hay persmiso para location updats");
            checkLocationPermission();
        }
    }


    /**
     * Actualiza la interfaz.
     */
    private void updateUI() {

        Location mPrevLocation;
        int lastIndex = LocationPoints.size()-1;

        Log.i("CURRENT LOCATION", mCurrentLocation.toString());

        //M05_textview_km.setText("");

        if (LocationPoints.size() >= 2) {
            mPrevLocation = LocationPoints.get(lastIndex - 1);
            distance = distance + mPrevLocation.distanceTo(mCurrentLocation);
            M05_textview_km.setText(String.valueOf(distance));
        }
    }


    /**
     * Gets Devices's last logation.
     */
    private void CheckLastLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            Log.i("TRACE","Check Location Tiene Permissions");
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

            if (mLastLocation != null) {
                Log.i("LAST LOCATION",mLastLocation.toString());
                LocationPoints.add(mLastLocation);
            } else {
                Log.i("TRACE","Last Location es null.");

            }
        }
        else {
            Log.i("TRACE", "No hay persmiso para last location");
            checkLocationPermission();
        }
    }

    public void instanceGoogleAPIClient() {
        Log.i("TRACE","Instant Google API Client");
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected() && !mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY,
                mRequestingLocationUpdates);
        savedInstanceState.putParcelable(LOCATION_KEY, mCurrentLocation);
        savedInstanceState.putString(LAST_UPDATED_TIME_STRING_KEY, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
    }

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

    /**
     * Detecs if location changed.
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        Log.i("LOCATION", "CHANGE");
        mCurrentLocation = location;
        LocationPoints.add(mCurrentLocation);
        updateUI();
    }

    public boolean checkLocationPermission(){
        Log.i("TRACE","Check Location Permissions");
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            Log.i("TRACE","No los tiene");
            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                Log.i("TRACE","Tiene que explicar al usuario");
                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
                Log.i("TRACE","Pide el permiso");
            }
            else {
                // No explanation needed, we can request the permission.
                Log.i("TRACE","Pedir sin explicar");
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        }
        else
        {
            Log.i("TRACE","Sí los tiene");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,                                            String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            instanceGoogleAPIClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }
}

