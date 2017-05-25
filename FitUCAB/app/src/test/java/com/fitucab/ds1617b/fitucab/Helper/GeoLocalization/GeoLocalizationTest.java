package com.fitucab.ds1617b.fitucab.Helper.GeoLocalization;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.Location;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by karo on 24/05/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class GeoLocalizationTest {


    @Mock
    GeoLocalization mGeolocalization;

    @Mock
    GoogleApiClient mGoogleApiClient;

    @Mock
    Location mPrevLocation;

    @Mock
    Location mPostLocation;

    @Test
    public void instanceGoogleApiClient() throws Exception {

    }


    @Test
    public void checkLastLocation() throws Exception {

    }

    @Test
    public void onLocationChanged() throws Exception {

    }

}