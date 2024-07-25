package com.machine_track_app.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OwnerDTO {

    private Long idOwner;
    private String owner;
    private String identification;
    private String identificationType;

}
