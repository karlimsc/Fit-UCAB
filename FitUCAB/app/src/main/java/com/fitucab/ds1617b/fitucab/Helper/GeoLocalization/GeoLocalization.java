package com.fitucab.ds1617b.fitucab.Helper.GeoLocalization;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

/**
 * Created by karo on 23/05/17.
 */

public class GeoLocalization extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    //Constantes.
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Location mCurrentLocation;
    private ArrayList<Location> LocationPoints=new ArrayList<Location>();
    private LocationRequest mLocationRequest = new LocationRequest();

    /**
     * To request the last known location, call the getLastLocation() method,
     * passing it your instance of the GoogleApiClient object.
     * @param connectionHint
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        startLocationUpdates();
        checkLastLocation(LocationPoints);
    }


    /**
     * Método que pertenece a la interfaz.
     * @param i
     */
    @Override
    public void onConnectionSuspended(int i) {

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
    public void createLocationRequest() {
        Log.i("TRACE","Crate Location Request");
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    /**
     * Start Location Updates
     **/
    public void startLocationUpdates() {
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
     * Actualiza la el array de posiciones..
     */
    public void updateLocationPoints(ArrayList<Location> LocationPoints) {

        Log.i("CURRENT LOCATION UPDATE", "UPDATELOCATIONPOINTS");
        if (LocationPoints!=null && mCurrentLocation !=null) {
            LocationPoints.add(mCurrentLocation);
            Log.i("CURRENT LOCATION UPDATE", mCurrentLocation.toString());
        }

    }

    /**
     * Gets Devices's last logation and store it in an ArrayList.
     */
     public void checkLastLocation(ArrayList<Location> LocationPoints) {
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

    /**
     * Crea una nuva instancia de Google API.
     */
    public void instanceGoogleApiClient() {
        Log.i("TRACE","Instant Google API Client");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    /**
     * Detener las actualizaciones de Localización.
     */
    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    /**
     * Detecs if location changed.
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        Log.i("ONLOCATIONCHANGE", String.valueOf(location));
        mCurrentLocation = location;
        if(mCurrentLocation!=null)
            updateLocationPoints(LocationPoints);
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

    /**
     * Resultado de la solicitud de permisos al usuario.
     * @param requestCode Código de solicitud.
     * @param permissions Permisos.
     * @param grantResults Otorgar permisos.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
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
                            instanceGoogleApiClient();
                        }
                        //mMap.setMyLocationEnabled(true);
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

    public void getLocationPoints(ArrayList<Location> locationPoints) {
        locationPoints = LocationPoints;
    }
}
