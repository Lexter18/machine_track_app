package com.machine_track_app.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepartmentDTO {

    private Long idDepartment;
    private String name;

    @JsonProperty("country")
    private LocationDTO country;

}
