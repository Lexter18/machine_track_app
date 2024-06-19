package com.machine_track_app.services;

import com.machine_track_app.entities.UserValidator;

import java.util.List;
import java.util.Optional;

public interface  UserValidatorService {

    List<UserValidator> getAllUserValidators();
    Optional<UserValidator> getUserValidatorById(Long id);
    UserValidator createUserValidator(UserValidator userValidator);
    UserValidator updateUserValidator(Long id, UserValidator userValidator);
    void deleteUserValidator(Long id);

}
