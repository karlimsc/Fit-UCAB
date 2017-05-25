package com.fitucab.ds1617b.fitucab.Helper.GeoLocalization;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by karo on 23/05/17.
 *
 * Firmas de los métodos necesarios para implementar la Geolocalozación.
 */

public interface GeoLocationInterface {


    /**
     * Método encargado de crear solicitudes de localización al sistema.
     */
    void createLocationRequest();

    /**
     * Método encargado de iniciar las actualizaciones de localización.
     */
    void startLocationUpdates();

    /**
     * Metodo encargado de actualizar la interfaz con las localizaciones obtenidas.
     */
    void updateUI();

    /**
     * Método para obtener la última localización del dispositivo.
     */
    void checkLastLocation();

    /**
     * Método que crea una instancia de la clase GoogleAPIClient.
     */
    void instanceGoogleApiClient();

    /**
     * Método que detiene las actualizaciones de localización.
     */
    void stopLocationUpdates();


    /**
     * Método que valida si la app tiene los permisos necesarios para solicitar localización del
     * dispositivo. En caso de que no los tenga los solicita.
     * @return true si son garantizados os permisos y false en caso contrario.
     */
    boolean checkLocationPermission();
}
