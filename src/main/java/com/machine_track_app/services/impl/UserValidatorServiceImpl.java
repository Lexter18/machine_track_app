package com.machine_track_app.services.impl;

import com.machine_track_app.entities.UserValidator;
import com.machine_track_app.repositories.UserValidatorRepository;
import com.machine_track_app.services.UserValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserValidatorServiceImpl implements UserValidatorService {

    private final UserValidatorRepository userValidatorRepository;

    @Autowired
    public UserValidatorServiceImpl(UserValidatorRepository userValidatorRepository) {
        this.userValidatorRepository = userValidatorRepository;
    }

    @Override
    public List<UserValidator> getAllUserValidators() {
        return userValidatorRepository.findAll();
    }

    @Override
    public Optional<UserValidator> getUserValidatorById(Long id) {
        return userValidatorRepository.findById(id);
    }

    @Override
    public UserValidator createUserValidator(UserValidator userValidator) {
        return userValidatorRepository.save(userValidator);
    }

    @Override
    public UserValidator updateUserValidator(Long id, UserValidator userValidator) {
        return userValidatorRepository.save(userValidator);
    }

    @Override
    public void deleteUserValidator(Long id) {
        userValidatorRepository.deleteById(id);
    }
}
