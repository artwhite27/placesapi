package com.simpleplaces.api.service;

import com.google.maps.model.*;
import com.simpleplaces.api.client.PlacesApiClient;
import com.simpleplaces.api.dto.PlaceInfo;
import com.simpleplaces.api.enums.SortPlaces;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlacesService {

    private final PlacesApiClient placesApiClient;

    public PlaceInfo getPlaceInfoById(String id) {
        return getPlaceInfo(placesApiClient.getPlaceDetails(id));
    }

    public PlaceInfo getPlaceInfoByName(String name) {

        String placeId = placesApiClient.findPlaceFromText(name).candidates[0].placeId;
        PlaceDetails result = placesApiClient.getPlaceDetails(placeId);

        return getPlaceInfo(result);
    }

    public AutocompletePrediction[] getAutocompleteSuggestions(String input) {
        return placesApiClient.queryAutocomplete(input);
    }

    public List<PlaceInfo> findPlacesByType(PlaceType placeType, SortPlaces sortPlaces) {
        return Arrays.stream(placesApiClient.findPlacesByType(placeType).results)
                .map(this::getPlaceInfo)
                .sorted(getComparator(sortPlaces))
                .collect(Collectors.toList());
    }

    private Comparator<PlaceInfo> getComparator(SortPlaces sortPlaces) {
        return switch (sortPlaces) {
            case RATING -> Comparator.comparing(PlaceInfo::getRating)
                    .thenComparing(PlaceInfo::getTotalUserRatings).reversed();
            case TOTAL_USER_RATINGS -> Comparator.comparing(PlaceInfo::getTotalUserRatings)
                    .thenComparing(PlaceInfo::getRating).reversed();
        };
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
