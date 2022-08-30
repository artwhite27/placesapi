package com.simpleplaces.api.client;

import com.google.maps.FindPlaceFromTextRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.PlaceDetails;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class PlacesApiClient {

    public PlaceDetails getPlaceDetails(GeoApiContext context, String placeId) {
        try {
            return PlacesApi.placeDetails(context, placeId).await();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public FindPlaceFromText findPlaceFromText(GeoApiContext context, String name) {
        try {
            return PlacesApi.findPlaceFromText(context, name,
                    FindPlaceFromTextRequest.InputType.TEXT_QUERY).await();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
