package com.simpleplaces.api.service;

import com.google.maps.model.*;
import com.simpleplaces.api.client.PlacesApiClient;
import com.simpleplaces.api.dto.PlaceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlacesService {

    private final PlacesApiClient placesApiClient;

    public PlaceInfo getPlaceInfoByName(String name) {

        String placeId = placesApiClient.findPlaceFromText(name).candidates[0].placeId;
        PlaceDetails result = placesApiClient.getPlaceDetails(placeId);

        return PlaceInfo.builder()
                .placeId(placeId)
                .name(result.name)
                .address(result.adrAddress)
                .rating(result.rating)
                .totalUserRatings(result.userRatingsTotal)
                .website(result.website == null ? null : result.website.toString())
                .build();
    }

    public AutocompletePrediction[] getAutocompleteSuggestions(String input) {
        return placesApiClient.queryAutocomplete(input);
    }

    public Set<PlaceInfo> findPlacesByType(PlaceType placeType) {
        return Arrays.stream(placesApiClient.findPlacesByType(placeType).results)
                .map(item -> PlaceInfo.builder()
                        .placeId(item.placeId)
                        .address(item.formattedAddress)
                        .name(item.name)
                        .rating(item.rating)
                        .totalUserRatings(item.userRatingsTotal)
                        .build())
                .collect(Collectors.toSet());
    }
}
