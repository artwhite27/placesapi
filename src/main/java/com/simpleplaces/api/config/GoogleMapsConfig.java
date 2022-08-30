package com.simpleplaces.api.config;

import com.google.maps.GeoApiContext;

public class GoogleMapsConfig {

    public static GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey("AIzaSyAe6ko0reJ61scXRmtKRaaRLpF1olcct5U")
                .build();
    }
}
