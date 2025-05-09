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
    private Long idEmployee;
    private Integer idRole;
    private Long idMunicipality;
    private Integer idPosition;
    private Integer idState;
    private String ownerName;
    private String ownerIdentificationType;
    private String ownerIdentification;

}
