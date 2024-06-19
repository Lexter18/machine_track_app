package com.machine_track_app.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MunicipalityDTO {

    private Long idMunicipality;
    private String name;
    private DepartmentDTO Department;


}
