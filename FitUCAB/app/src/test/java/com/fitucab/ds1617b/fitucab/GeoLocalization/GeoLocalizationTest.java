package com.fitucab.ds1617b.fitucab.GeoLocalization;

import com.fitucab.ds1617b.fitucab.Helper.GeoLocalization.GeoLocalization;
import com.google.android.gms.common.api.GoogleApiClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.Location;
import org.mockito.runners.MockitoJUnitRunner;

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