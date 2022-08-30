package com.simpleplaces.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceInfo {
    private String placeId;
    private String name;
    private String address;
    private String website;
    private Float rating;
    private Integer totalUserRatings;
}
