package com.machine_track_app.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class UserRequestPayload {

    private String firstName;
    private String middleName;
    private String firstSurname;
    private String secondSurname;
    private String identification;
    private String identificationType;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Long idOwner;
    private Integer idRole;
    private Long idMunicipality;
    private Integer idPosition;
    private int idState;

}
