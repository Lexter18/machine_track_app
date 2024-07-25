package com.machine_track_app.services.impl;

import com.machine_track_app.dto.request.InitialRegistrationRequestPayload;
import com.machine_track_app.dto.response.*;
import com.machine_track_app.entities.*;
import com.machine_track_app.enums.IdentificationTypeEmployee;
import com.machine_track_app.enums.PositionsEnum;
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
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static ch.qos.logback.core.util.OptionHelper.isNullOrEmpty;
import static com.machine_track_app.utils.ConstantsUtils.ROLE_OWNER;
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
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsersByOwner() {
        var idOwner = getCurrentOwnerId();
        var user = userRepository.findByEmployee_Owner(idOwner);
        return user.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllOwnerUser(Integer idRole) {
        var user = userRepository.findAllByRoleIdRole(idRole);
        return user.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponsePayload initialRegistration(InitialRegistrationRequestPayload initialRegistration) {
        try {
            log.info("initialRegistration -> {}", initialRegistration);

            var errors = validateRegistration(initialRegistration);
            if (errors.isEmpty()) {
                var date = LocalDateTime.now();

                UserRole role = UserRole.builder()
                        .idRole(ROLE_OWNER)
                        .build();

                State state = State.builder()
                        .idState(StateApp.DISABLED.getState())
                        .build();

                Municipality municipality = Municipality.builder()
                        .idMunicipality(initialRegistration.getIdMunicipality())
                        .build();

                Position position = Position.builder()
                        .idPosition(PositionsEnum.ADMINISTRATOR.getPosition())
                        .build();

                Employee employee = Employee.builder()
                        .firstName(initialRegistration.getFirstName())
                        .middleName(initialRegistration.getMiddleName())
                        .firstSurname(initialRegistration.getFirstSurname())
                        .secondSurname(initialRegistration.getSecondSurname())
                        .email(initialRegistration.getEmail())
                        .phone(initialRegistration.getPhone())
                        .creation(date)
                        .identification(initialRegistration.getIdentification())
                        .identificationType(IdentificationTypeEmployee.valueOf(initialRegistration.getIdentificationType()))
                        .state(state)
                        .municipality(municipality)
                        .position(position)
                        .build();

                var currentEmployee = employeeRepository.save(employee);
                String passwordBCrypt = passwordEncoder.encode(initialRegistration.getPassword());
                User u = User.builder()
                        .userName(initialRegistration.getUsername())
                        .password(passwordBCrypt)
                        .creation(date)
                        .state(state)
                        .employee(currentEmployee)
                        .role(role)
                        .build();

                userRepository.save(u);
                return ResponsePayload.builder()
                        .code(HttpStatus.CREATED.getReasonPhrase())
                        .description(SUCCESSFUL_PROCESS_MESSAGE)
                        .statusCode(HttpStatus.CREATED.value())
                        .build();

            }

            throw new ValidationException(errors);

        } catch (ValidationException ex) {
            log.error(DATA_VALIDATION_ERROR);
            throw ex;
        } catch (Exception ex) {
            var msn = "Error ejecutando el registro inicial de usuarios ";
            log.error(msn.concat(ex.getMessage()));
            throw new MachinTrackException(msn, ex);
        }
    }

    private List<String> validateRegistration(InitialRegistrationRequestPayload registration) {
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
