package com.machine_track_app.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long idUser;
    private String userName;
    private StateDTO state;
    private EmpleyeeDTO employee;
    private RoleDTO role;

}
