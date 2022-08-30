package com.simpleplaces.api.config;

import com.google.maps.GeoApiContext;

public class GoogleMapsConfig {

    public static GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey("AIzaSyDyHpDodACdeT6c9p8So06g943Vgo9e-Hc")
                .build();
    }
}
