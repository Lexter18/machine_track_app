package com.machine_track_app.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LocationDTO {

    private Long idLocation;
    private String name;


}
