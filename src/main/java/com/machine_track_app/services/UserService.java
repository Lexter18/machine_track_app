package com.machine_track_app.services;

import com.machine_track_app.dto.request.UserRequestPayload;
import com.machine_track_app.dto.response.ResponsePayload;
import com.machine_track_app.dto.response.UserDTO;

import java.util.List;

public interface UserService {

    ResponsePayload initialRegistration(UserRequestPayload user);

    ResponsePayload createUser(UserRequestPayload user);

    ResponsePayload createOwner(UserRequestPayload user);

    List<UserDTO> getAllOwnerUser();

    List<UserDTO> getAllUsersByOwner();

    ResponsePayload updateUser(Long idUser, UserRequestPayload user);

}




