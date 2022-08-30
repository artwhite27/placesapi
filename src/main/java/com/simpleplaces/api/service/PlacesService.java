package com.simpleplaces.api.service;

import com.google.maps.model.PlaceDetails;
import com.simpleplaces.api.client.PlacesApiClient;
import com.simpleplaces.api.dto.PlaceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class PlacesService {

    private final PlacesApiClient placesApiClient;

    public PlaceInfo getPlaceInfoByName(String name) {

        String placeId = placesApiClient.findPlaceFromText(name).candidates[0].placeId;
        PlaceDetails result = placesApiClient.getPlaceDetails(placeId);

        System.out.println(Arrays.toString(placesApiClient.queryAutocomplete(name)));
        return PlaceInfo.builder()
                .name(result.name)
                .rating(result.rating)
                .totalUserRatings(result.userRatingsTotal)
                .website(result.website == null ? null : result.website.toString())
                .build();
    }

    public Object getAutocompleteSuggestions(String input) {
        return placesApiClient.queryAutocomplete(input);
    }
}
