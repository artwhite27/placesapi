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

        return getPlaceInfo(result);
    }

    public AutocompletePrediction[] getAutocompleteSuggestions(String input) {
        return placesApiClient.queryAutocomplete(input);
    }

    public Set<PlaceInfo> findPlacesByType(PlaceType placeType) {
        return Arrays.stream(placesApiClient.findPlacesByType(placeType).results)
                .map(this::getPlaceInfo)
                .collect(Collectors.toSet());
    }

    private PlaceInfo getPlaceInfo(PlaceDetails item) {
        return PlaceInfo.builder()
                .placeId(item.placeId)
                .name(item.name)
                .address(item.adrAddress)
                .rating(item.rating)
                .totalUserRatings(item.userRatingsTotal)
                .website(item.website == null ? null : item.website.toString())
                .build();
    }

    private PlaceInfo getPlaceInfo(PlacesSearchResult item) {
        return PlaceInfo.builder()
                .placeId(item.placeId)
                .address(item.formattedAddress)
                .name(item.name)
                .rating(item.rating)
                .totalUserRatings(item.userRatingsTotal)
                .build();
    }
}
