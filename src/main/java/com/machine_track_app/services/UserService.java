package com.machine_track_app.services;

import com.machine_track_app.dto.request.UserRequestPayload;
import com.machine_track_app.dto.response.ResponsePayload;
import com.machine_track_app.dto.response.UserDTO;

import java.util.List;

public interface UserService {

    ResponsePayload initialRegistration(UserRequestPayload user);

    List<UserDTO> getAllUsersByOwner();

    List<UserDTO> getAllOwnerUser();

}




