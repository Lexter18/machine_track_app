package com.machine_track_app.services.impl;

import com.machine_track_app.dto.request.UserRequestPayload;
import com.machine_track_app.dto.response.*;
import com.machine_track_app.entities.*;
import com.machine_track_app.enums.IdentificationTypeEmployee;
import com.machine_track_app.enums.RolesEnum;
import com.machine_track_app.enums.StateApp;
import com.machine_track_app.exceptions.MachinTrackException;
import com.machine_track_app.exceptions.ValidationException;
import com.machine_track_app.repositories.EmployeeRepository;
import com.machine_track_app.repositories.UserRepository;
import com.machine_track_app.services.UserService;
import com.machine_track_app.services.helpers.UserValidationHelper;
import com.machine_track_app.services.mapper.UserMapper;
import com.machine_track_app.utils.BuilderUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.machine_track_app.enums.RolesEnum.*;
import static com.machine_track_app.enums.StateApp.DELETED;
import static com.machine_track_app.utils.LogUtils.infoJson;
import static com.machine_track_app.utils.MessageUtil.*;
import static com.machine_track_app.utils.SecurityUtils.getCurrentOwnerId;
import static com.machine_track_app.utils.SecurityUtils.getCurrentRol;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationHelper userValidationHelper;


    @Override
    public List<UserDTO> getAllOwnerUser() {
        var user = userRepository.findAllByRoleIdRole(OWNER.getRol());
        return user.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsersByOwner() {
        var idOwner = getCurrentOwnerId();
        List<Integer> roles = List.of(2, 3);
        var user = userRepository.findActiveUsersByOwnerExcludingStatesAndRoles(idOwner, DELETED.getState(),
                DELETED.getState(),
                roles);

        return user.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponsePayload initialRegistration(UserRequestPayload initialRegistration) {
        try {
            log.info("initialRegistration -> {}", infoJson(initialRegistration));
            var errors = userValidationHelper.validateRegistration(initialRegistration);
            if (errors.isEmpty()) {
                var user = initialRegistration.toBuilder()
                        .idState(StateApp.DISABLED.getState())
                        .idRole(OWNER.getRol())
                        .build();

                return userManager(user);
            }

            throw new ValidationException(errors);

        } catch (ValidationException ex) {
            log.error(DATA_VALIDATION_ERROR);
            throw ex;
        } catch (Exception ex) {
            var msn = "Error executing initial user registration. " + ex.getMessage();
            log.error(msn, ex);
            throw new MachinTrackException(msn, ex);
        }
    }

    @Override
    @Transactional
    public ResponsePayload createOwner(UserRequestPayload user) {
        try {
            log.info("create owner -> {}", infoJson(user));
            var errors = userValidationHelper.validateRegistration(user);
            if (errors.isEmpty()) {
                return userManager(user);
            }

            throw new ValidationException(errors);

        } catch (ValidationException ex) {
            log.error(DATA_VALIDATION_ERROR);
            throw ex;
        } catch (Exception ex) {
            var msn = "Error executing user registration " + ex.getMessage();
            log.error(msn.concat(ex.getMessage()));
            throw new MachinTrackException(msn, ex);
        }
    }

    @Override
    @Transactional
    public ResponsePayload createUser(UserRequestPayload user) {
        try {
            log.info("create user -> {}", infoJson(user));
            var errors = userValidationHelper.validateRegistration(user);
            if (errors.isEmpty()) {
                var idOwner = getCurrentOwnerId();
                var u = user.toBuilder()
                        .idOwner(idOwner)
                        .build();

                return userManager(u);
            }

            throw new ValidationException(errors);

        } catch (ValidationException ex) {
            log.error(DATA_VALIDATION_ERROR);
            throw ex;
        } catch (Exception ex) {
            var msn = "Error executing user creation.  " + ex.getMessage();
            log.error(msn, ex);
            throw new MachinTrackException(msn, ex);
        }
    }

    @Override
    @Transactional
    public ResponsePayload updateUser(Long idUser, UserRequestPayload user) {
        try {
            log.info("Starting update of user with ID {} body -> {} ", idUser, infoJson(user));

            var errors = userValidationHelper.validateUserUpdate(idUser, user);
            if (errors.isEmpty()) {
                var currentUser = getUserByRole(idUser);

                var date = LocalDateTime.now();
                State state = currentUser.getState();
                State updateState = State.builder()
                        .idState(BuilderUtils.choose(user.getIdState(), state.getIdState()))
                        .build();

                UserRole role = currentUser.getRole();
                UserRole updateRole = UserRole.builder()
                        .idRole(BuilderUtils.choose(user.getIdRole(), role.getIdRole()))
                        .build();

                Employee employee = currentUser.getEmployee();
                Municipality municipality = Municipality.builder()
                        .idMunicipality(BuilderUtils.choose(user.getIdMunicipality(), employee.getMunicipality().getIdMunicipality()))
                        .build();

                var owner = Optional.ofNullable(employee.getOwner())
                        .map(Owner::getIdOwner)
                        .orElse(null);

                Owner updateOwner = Owner.builder()
                        .idOwner(BuilderUtils.choose(user.getIdOwner(), owner))
                        .build();

                var identificationType = BuilderUtils.choose(user.getIdentificationType(),
                        employee.getIdentificationType().name());

                Employee updateEmployee = Employee.builder()
                        .idEmployee(employee.getIdEmployee())
                        .firstName(BuilderUtils.choose(user.getFirstName(), employee.getFirstName()))
                        .middleName(BuilderUtils.choose(user.getMiddleName(), employee.getMiddleName()))
                        .firstSurname(BuilderUtils.choose(user.getFirstSurname(), employee.getFirstSurname()))
                        .secondSurname(BuilderUtils.choose(user.getSecondSurname(), employee.getSecondSurname()))
                        .email(BuilderUtils.choose(user.getEmail(), employee.getEmail()))
                        .phone(BuilderUtils.choose(user.getPhone(), employee.getPhone()))
                        .creation(employee.getCreation())
                        .modification(date)
                        .identification(BuilderUtils.choose(user.getIdentification(), employee.getIdentification()))
                        .identificationType(IdentificationTypeEmployee.valueOf(identificationType))
                        .state(updateState)
                        .municipality(municipality)
                        .owner(updateOwner)
                        .ownerInitialDetail(employee.getOwnerInitialDetail())
                        .build();

                var currentEmployee = employeeRepository.save(updateEmployee);
                User updatedUser = User.builder()
                        .idUser(currentUser.getIdUser())
                        .userName(BuilderUtils.choose(user.getUsername(), currentUser.getUserName()))
                        .password(currentUser.getPassword())
                        .creation(currentUser.getCreation())
                        .modification(date)
                        .state(updateState)
                        .role(updateRole)
                        .employee(currentEmployee)
                        .build();

                userRepository.save(updatedUser);
                log.info("User {} was edited successfully", updatedUser.getUserName());

                return ResponsePayload.builder()
                        .code(HttpStatus.OK.getReasonPhrase())
                        .description("Usuario actualizado correctamente.")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            }

            throw new ValidationException(errors);


        } catch (MachinTrackException ex) {
            var msg = "No se encontró el Usuario";
            log.warn("{} {}", msg, ex.getMessage());
            return ResponsePayload.builder()
                    .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .description(msg)
                    .statusCode(HttpStatus.NOT_FOUND.value())
                    .build();

        } catch (ValidationException ex) {
            log.error(DATA_VALIDATION_ERROR);
            throw ex;
        } catch (Exception ex) {
            var msg = "Error al actualizar el Usuario: " + ex.getMessage();
            log.error(msg, ex);
            throw new MachinTrackException(msg, ex);
        }
    }

    private ResponsePayload userManager(UserRequestPayload user) {
        try {
            var date = LocalDateTime.now();
            var ownerDetail = Stream.of(user.getOwnerName(), user.getOwnerIdentificationType(), user.getOwnerIdentification())
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining(" "));

            UserRole role = UserRole.builder()
                    .idRole(user.getIdRole())
                    .build();

            State state = State.builder()
                    .idState(user.getIdState())
                    .build();

            Municipality municipality = Municipality.builder()
                    .idMunicipality(user.getIdMunicipality())
                    .build();

            var owner = Optional.ofNullable(user.getIdOwner())
                    .map(id -> Owner.builder().idOwner(id).build())
                    .orElse(null);

            Employee employee = Employee.builder()
                    .firstName(user.getFirstName())
                    .middleName(user.getMiddleName())
                    .firstSurname(user.getFirstSurname())
                    .secondSurname(user.getSecondSurname())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .creation(date)
                    .identification(user.getIdentification())
                    .identificationType(IdentificationTypeEmployee.valueOf(user.getIdentificationType()))
                    .state(state)
                    .municipality(municipality)
                    .owner(owner)
                    .ownerInitialDetail(ownerDetail)
                    .build();

            var currentEmployee = employeeRepository.save(employee);
            String passwordBCrypt = passwordEncoder.encode(user.getPassword());
            User newUser = User.builder()
                    .userName(user.getUsername())
                    .password(passwordBCrypt)
                    .creation(date)
                    .state(state)
                    .employee(currentEmployee)
                    .role(role)
                    .build();

            userRepository.save(newUser);
            log.info("User {} successfully registered.", newUser.getUserName());
            return ResponsePayload.builder()
                    .code(HttpStatus.CREATED.getReasonPhrase())
                    .description(USER_CREATION_SUCCESSFUL)
                    .statusCode(HttpStatus.CREATED.value())
                    .build();

        } catch (Exception ex) {
            var errorMessage = USER_CREATION_FAILED + ex.getMessage();
            log.error(errorMessage, ex);
            throw new MachinTrackException(errorMessage, ex);
        }
    }

    private User getUserByRole(Long idUser) {
        var roleName = getRoleNameById(getCurrentRol()) ;
        if (RolesEnum.ADMIN.name().equals(roleName)) {
            return userRepository.findByIdUser(idUser)
                    .orElseThrow(() -> new MachinTrackException("User not found"));
        }

        if (RolesEnum.OWNER.name().equals(roleName)) {
            Long ownerId = getCurrentOwnerId();
            return userRepository.findByIdUserAndEmployee_Owner_IdOwner(idUser, ownerId)
                    .orElseThrow(() -> new MachinTrackException("User not found"));
        }

        throw new MachinTrackException("Rol no autorizado para esta operación");
    }

}
