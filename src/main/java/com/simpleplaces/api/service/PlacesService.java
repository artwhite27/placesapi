package com.simpleplaces.api.service;

import com.google.maps.FindPlaceFromTextRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.PlaceDetails;
import com.simpleplaces.api.config.GoogleMapsConfig;
import com.simpleplaces.api.dto.PlaceInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PlacesService {

    public PlaceInfo getPlaceInfoByName(String name)
            throws IOException, InterruptedException, ApiException {

        GeoApiContext context = GoogleMapsConfig.geoApiContext();
        String placeId = getPlaceId(name, context);
        PlaceDetails result = PlacesApi.placeDetails(context, placeId).await();
        return PlaceInfo.builder()
                .name(result.name)
                .rating(result.rating)
                .totalUserRatings(result.userRatingsTotal)
                .website(result.website == null ? null : result.website.toString())
                .build();
    }

    private String getPlaceId(String name, GeoApiContext context)
            throws IOException, InterruptedException, ApiException {

        FindPlaceFromText results = PlacesApi.findPlaceFromText(context, name,
                FindPlaceFromTextRequest.InputType.TEXT_QUERY).await();

        return results.candidates[0].placeId;
    }
}
