package com.simpleplaces.api.controller;

import com.simpleplaces.api.dto.PlaceInfo;
import com.simpleplaces.api.service.PlacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlacesController {

    private final PlacesService placesService;

    @GetMapping("/{name}")
    PlaceInfo getPlaceInfoByName(@PathVariable String name) {
        return placesService.getPlaceInfoByName(name);
    }

    @GetMapping("/autocomplete/{input}")
    Object getAutocompleteSuggestions(@PathVariable String input) {
        return placesService.getAutocompleteSuggestions(input);
    }
}
