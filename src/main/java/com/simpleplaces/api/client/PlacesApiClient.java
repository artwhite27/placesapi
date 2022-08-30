package com.simpleplaces.api.client;

import com.google.maps.*;
import com.google.maps.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PlacesApiClient {

    private final GeoApiContext context;

    public PlaceDetails getPlaceDetails(String placeId) {
        return (PlaceDetails)
                method.apply(PlacesApi.placeDetails(context, placeId));
    }

    public FindPlaceFromText findPlaceFromText(String name) {
        return (FindPlaceFromText)
                method.apply(PlacesApi.findPlaceFromText(context, name, FindPlaceFromTextRequest.InputType.TEXT_QUERY));
    }

    public AutocompletePrediction[] queryAutocomplete(String input) {
        return (AutocompletePrediction[])
                method.apply(PlacesApi.queryAutocomplete(context, input));
    }

    public PlacesSearchResponse findPlacesByType(PlaceType placeType) {
        return (PlacesSearchResponse)
                method.apply(PlacesApi.textSearchQuery(context, placeType));
    }

    Function<PendingResult<?>, Object> method = (o) -> {
        try {
            return o.await();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    };
}
