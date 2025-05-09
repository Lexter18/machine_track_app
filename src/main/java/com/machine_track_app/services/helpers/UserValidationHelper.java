package com.machine_track_app.services.helpers;

import com.machine_track_app.dto.request.UserRequestPayload;
import com.machine_track_app.repositories.EmployeeRepository;
import com.machine_track_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static ch.qos.logback.core.util.OptionHelper.isNullOrEmpty;
import static com.machine_track_app.utils.MessageUtil.*;

@Component
@RequiredArgsConstructor
public class UserValidationHelper {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    public List<String> validateRegistration(UserRequestPayload registration) {
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

    public List<String> validateUserUpdate(Long idUser, UserRequestPayload user) {
        List<Supplier<Optional<String>>> checks = new ArrayList<>();
        if (user.getUsername() != null) {
            checks.add(() ->
                    userRepository.countByUserNameIdUserNot(idUser, user.getUsername()) > 0
                            ? Optional.of(USER_VALIDATION)
                            : Optional.empty()
            );
        }

        if (user.getEmail() != null) {
            checks.add(() ->
                    employeeRepository.countByEmailIdEmployeeIsNot(user.getEmail(), user.getIdEmployee()) > 0
                            ? Optional.of(EMAIL_VALIDATION)
                            : Optional.empty()
            );
        }

        return checks.stream()
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
