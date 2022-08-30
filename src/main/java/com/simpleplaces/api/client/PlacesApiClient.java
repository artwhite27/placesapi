package com.simpleplaces.api.client;

import com.google.maps.FindPlaceFromTextRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class PlacesApiClient {

    private final GeoApiContext context;

    public PlaceDetails getPlaceDetails(String placeId) {
        try {
            return PlacesApi.placeDetails(context, placeId).await();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public FindPlaceFromText findPlaceFromText(String name) {
        try {
            return PlacesApi.findPlaceFromText(context, name,
                    FindPlaceFromTextRequest.InputType.TEXT_QUERY).await();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public AutocompletePrediction[] queryAutocomplete(String input) {
        try {
            return PlacesApi.queryAutocomplete(context, input).await();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public PlacesSearchResponse findPlacesByType(PlaceType placeType) {
        try {
            return PlacesApi.textSearchQuery(context, placeType).await();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
