package com.machine_track_app.controller;

import com.machine_track_app.dto.request.InitialRegistrationRequestPayload;
import com.machine_track_app.dto.response.RoleDTO;
import com.machine_track_app.dto.response.UserDTO;
import com.machine_track_app.services.RoleService;
import com.machine_track_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/byOwner")
    public ResponseEntity<List<?>> getAllUsersByOwner() {
        List<UserDTO> users = userService.getAllUsersByOwner();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/byRole/{idRole}")
    public ResponseEntity<List<?>> getAllUserByRole(@PathVariable Integer idRole) {
        List<UserDTO> users = userService.getAllOwnerUser(idRole);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<?>> getAllRoles() {
        List<RoleDTO> roles = roleService.getRoles();
        return ResponseEntity.ok(roles);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        return userService.getUserById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PostMapping("/initialRegistration")
    public ResponseEntity<?> initialRegistration(@RequestBody InitialRegistrationRequestPayload initialRegistration) {
        var createdUser = userService.initialRegistration(initialRegistration);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//        User updatedUser = userService.updateUser(id, user);
//        if (updatedUser != null) {
//            return ResponseEntity.ok(updatedUser);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }

}
