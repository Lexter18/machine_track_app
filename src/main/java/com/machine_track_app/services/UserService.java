package com.machine_track_app.services;

import com.machine_track_app.dto.request.InitialRegistrationRequestPayload;
import com.machine_track_app.dto.response.ResponsePayload;
import com.machine_track_app.dto.response.UserDTO;
import com.machine_track_app.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    ResponsePayload initialRegistration(InitialRegistrationRequestPayload user);

    List<UserDTO> getAllUsersByOwner();

    Optional<User> getUserById(Long id);

    User createUser(InitialRegistrationRequestPayload user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}




