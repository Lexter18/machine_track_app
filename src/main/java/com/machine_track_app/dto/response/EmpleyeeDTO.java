package com.machine_track_app.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpleyeeDTO {

    private Long idEmployee;
    private String identification;
    private String firstName;
    private String middleName;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private String phone;
    private MunicipalityDTO municipality;
    private LocalDateTime creation;
    private LocalDateTime modification;
    private String identificationType;
    private StateDTO state;
    private PositionDTO position;


}
