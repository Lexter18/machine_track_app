package com.machine_track_app.controller;

import com.machine_track_app.dto.request.UserRequestPayload;
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
@RequestMapping("/api/v1/users")
public class UserController {


    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/owners")
    public ResponseEntity<List<?>> getAllOwnerUser() {
        List<UserDTO> users = userService.getAllOwnerUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/byOwner")
    public ResponseEntity<List<?>> getAllUsersByOwner() {
        List<UserDTO> users = userService.getAllUsersByOwner();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<?>> getAllRoles() {
        List<RoleDTO> roles = roleService.getRoles();
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/initialRegistration")
    public ResponseEntity<?> initialRegistration(@RequestBody UserRequestPayload initialRegistration) {
        var createdUser = userService.initialRegistration(initialRegistration);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/owner")
    public ResponseEntity<?> ownerManager(@RequestBody UserRequestPayload user) {
        var createdUser = userService.createOwner(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/user")
    public ResponseEntity<?> userManager(@RequestBody UserRequestPayload user) {
        var createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/owner/{idUser}")
    public ResponseEntity<?> updateOwner(@PathVariable Long idUser, @RequestBody UserRequestPayload user) {
        var result = userService.updateUser(idUser, user);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

}
