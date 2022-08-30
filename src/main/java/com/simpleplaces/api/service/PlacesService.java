package com.simpleplaces.api.service;

import com.google.maps.FindPlaceFromTextRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.PlaceDetails;
import com.simpleplaces.api.config.GoogleMapsConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PlacesService {

    public Object getPlaceInfoByName(String name) throws IOException, InterruptedException, ApiException {

        GeoApiContext context = GoogleMapsConfig.geoApiContext();
        FindPlaceFromText results = PlacesApi.findPlaceFromText(context, name,
                FindPlaceFromTextRequest.InputType.TEXT_QUERY).await();

        String placeId = results.candidates[0].placeId;
        System.out.println(placeId);
        PlaceDetails result = PlacesApi.placeDetails(context, placeId).await();
        System.out.println(result);
        System.out.println(result.rating);
        return result;
    }
}
