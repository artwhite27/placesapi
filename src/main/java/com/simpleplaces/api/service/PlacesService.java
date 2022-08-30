package com.simpleplaces.api.service;

import com.google.maps.GeoApiContext;
import com.google.maps.model.PlaceDetails;
import com.simpleplaces.api.client.PlacesApiClient;
import com.simpleplaces.api.dto.PlaceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlacesService {

    private final PlacesApiClient placesApiClient;
    private final GeoApiContext context;

    public PlaceInfo getPlaceInfoByName(String name) {

        String placeId = placesApiClient.findPlaceFromText(context, name).candidates[0].placeId;
        PlaceDetails result = placesApiClient.getPlaceDetails(context, placeId);

        return PlaceInfo.builder()
                .name(result.name)
                .rating(result.rating)
                .totalUserRatings(result.userRatingsTotal)
                .website(result.website == null ? null : result.website.toString())
                .build();
    }
}
