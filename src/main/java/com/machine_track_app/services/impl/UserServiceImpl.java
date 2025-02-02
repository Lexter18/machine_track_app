package com.machine_track_app.services.impl;

import com.machine_track_app.dto.request.UserRequestPayload;
import com.machine_track_app.dto.response.*;
import com.machine_track_app.entities.*;
import com.machine_track_app.enums.IdentificationTypeEmployee;
import com.machine_track_app.enums.PositionsEnum;
import com.machine_track_app.enums.RolesEnum;
import com.machine_track_app.enums.StateApp;
import com.machine_track_app.exceptions.MachinTrackException;
import com.machine_track_app.exceptions.ValidationException;
import com.machine_track_app.repositories.EmployeeRepository;
import com.machine_track_app.repositories.UserRepository;
import com.machine_track_app.services.UserService;
import com.machine_track_app.services.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static ch.qos.logback.core.util.OptionHelper.isNullOrEmpty;
import static com.machine_track_app.enums.RolesEnum.OWNER;
import static com.machine_track_app.utils.MessageUtil.*;
import static com.machine_track_app.utils.SecurityUtils.getCurrentOwnerId;


@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAllOwnerUser() {
        var user = userRepository.findAllByRoleIdRole(OWNER.getRol());
        return user.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponsePayload initialRegistration(UserRequestPayload initialRegistration) {
        try {
            log.info("initialRegistration -> {}", initialRegistration);
            var errors = validateRegistration(initialRegistration);
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
    public ResponsePayload createUser(UserRequestPayload user) {
        try {
            log.info("createUser -> {}", user);
            var errors = validateRegistration(user);
            if (errors.isEmpty()) {
                var userCurrent = user.toBuilder()
                        .idState(StateApp.ACTIVE.getState())
                        .build();

                return userManager(userCurrent);

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

    private ResponsePayload userManager(UserRequestPayload user) {
        try {
            var date = LocalDateTime.now();
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
            return ResponsePayload.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                    .description(errorMessage)
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    private List<String> validateRegistration(UserRequestPayload registration) {
        List<Supplier<Optional<String>>> checks = List.of(
                () -> isNullOrEmpty(registration.getFirstName()) ? Optional.of(FIRST_NAME_REQUIRED) : Optional.empty(),
                () -> isNullOrEmpty(registration.getFirstSurname()) ? Optional.of(FIRST_LAST_NAME_REQUIRED) : Optional.empty(),
                () -> isNullOrEmpty(registration.getIdentification()) ? Optional.of(IDENTIFICATION_REQUIRED) : Optional.empty(),
                () -> isNullOrEmpty(registration.getIdentificationType()) ? Optional.of(TYPE_ID_REQUIRED) : Optional.empty(),
                () -> isNullOrEmpty(registration.getPhone()) ? Optional.of(PHONE_REQUIRED) : Optional.empty(),
                () -> isNullOrEmpty(registration.getUsername()) ? Optional.of(USER_REQUIRED) : Optional.empty(),
                () -> isNullOrEmpty(registration.getPassword()) ? Optional.of(PASSWORD_REQUIRED) : Optional.empty(),
                () -> isNullOrEmpty(registration.getEmail()) ? Optional.of(EMAIL_REQUIRED) : Optional.empty(),
                () -> Optional.ofNullable(registration.getIdMunicipality()).map(Object::toString)
                        .isPresent() ? Optional.empty() : Optional.of(MUNICIPALITY_REQUIRED),
                () -> userRepository.countByUserName(registration.getUsername()) > 0 ? Optional.of(USER_VALIDATION) : Optional.empty(),
                () -> employeeRepository.countByEmail(registration.getEmail()) > 0 ? Optional.of(EMAIL_VALIDATION) : Optional.empty()
        );

        return checks.stream()
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
