package com.machine_track_app.controller;

import com.machine_track_app.entities.UserValidator;
import com.machine_track_app.services.UserValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/uservalidators")
public class UserValidatorController {

    private final UserValidatorService userValidatorService;

    @Autowired
    public UserValidatorController(UserValidatorService userValidatorService) {
        this.userValidatorService = userValidatorService;
    }

    @GetMapping
    public ResponseEntity<List<UserValidator>> getAllUserValidators() {
        List<UserValidator> userValidatorList = userValidatorService.getAllUserValidators();
        return ResponseEntity.ok(userValidatorList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserValidator> getUserValidatorById(@PathVariable Long id) {
        return userValidatorService.getUserValidatorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserValidator> createUserValidator(@RequestBody UserValidator userValidator) {
        UserValidator createdUserValidator = userValidatorService.createUserValidator(userValidator);
        return ResponseEntity.ok(createdUserValidator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserValidator> updateUserValidator(@PathVariable Long id, @RequestBody UserValidator userValidator) {
        return userValidatorService.getUserValidatorById(id)
                .map(existingUserValidator -> {
                    UserValidator updatedUserValidator = userValidatorService.updateUserValidator(id, userValidator);
                    return ResponseEntity.ok(updatedUserValidator);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserValidator(@PathVariable Long id) {
        userValidatorService.deleteUserValidator(id);
        return ResponseEntity.noContent().build();
    }
}
