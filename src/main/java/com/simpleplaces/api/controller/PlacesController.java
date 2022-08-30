package com.simpleplaces.api.controller;

import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.PlaceType;
import com.simpleplaces.api.dto.PlaceInfo;
import com.simpleplaces.api.service.PlacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlacesController {

    private final PlacesService placesService;

    @GetMapping("/{name}/info")
    PlaceInfo getPlaceInfoByName(@PathVariable String name) {
        return placesService.getPlaceInfoByName(name);
    }

    @GetMapping("/autocomplete/{input}")
    AutocompletePrediction[] getAutocompleteSuggestions(@PathVariable String input) {
        return placesService.getAutocompleteSuggestions(input);
    }

    @GetMapping("/type")
    Set<PlaceInfo> getPlacesByType(@RequestParam PlaceType placeType) {
        return placesService.findPlacesByType(placeType);
    }
}
