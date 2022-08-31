package com.simpleplaces.api.controller;

import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceType;
import com.simpleplaces.api.dto.PlaceInfo;
import com.simpleplaces.api.enums.SortPlaces;
import com.simpleplaces.api.service.PlacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlacesController {

    private final PlacesService placesService;

    @GetMapping("/{id}")
    PlaceInfo getPlaceInfoById(@PathVariable String id) {
        return placesService.getPlaceInfoById(id);
    }

    @GetMapping("/{name}/info")
    PlaceInfo getPlaceInfoByName(@PathVariable String name) {
        return placesService.getPlaceInfoByName(name);
    }

    @GetMapping("/autocomplete/{input}")
    AutocompletePrediction[] getAutocompleteSuggestions(@PathVariable String input) {
        return placesService.getAutocompleteSuggestions(input);
    }

    @GetMapping("/type")
    List<PlaceInfo> getPlacesByType(@RequestParam(defaultValue = "CAFE") PlaceType placeType,
                                    @RequestParam(defaultValue = "RATING") SortPlaces sortPlaces) {
        return placesService.findPlacesByType(placeType, sortPlaces);
    }
}
