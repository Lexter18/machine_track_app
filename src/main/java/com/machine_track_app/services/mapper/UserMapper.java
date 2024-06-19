package com.machine_track_app.services.mapper;

import com.machine_track_app.dto.response.*;
import com.machine_track_app.entities.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static UserDTO toUserDto(User user) {
        return UserDTO.builder()
                .idUser(user.getIdUser())
                .userName(user.getUserName())
                .employee(toEmployeeDto(user.getEmployee()))
                .state(toStateDto(user.getState()))
                .role(toRoleDto(user.getRole()))
                .build();
    }

    public static EmpleyeeDTO toEmployeeDto(Employee employee) {
        return EmpleyeeDTO.builder()
                .idEmployee(employee.getIdEmployee())
                .firstName(employee.getFirstName())
                .secondSurname(employee.getSecondSurname())
                .middleName(employee.getMiddleName())
                .firstSurname(employee.getFirstSurname())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .creation(employee.getCreation())
                .modification(employee.getModification())
                .identificationType(employee.getIdentificationType().name())
                .identification(employee.getIdentification())
                .municipality(toMunicipalityDto(employee.getMunicipality()))
                .state(toStateDto(employee.getState()))
                .build();
    }

    public static StateDTO toStateDto(State state) {
        return StateDTO.builder()
                .idState(state.getIdState())
                .state(state.getState())
                .build();
    }

    public static RoleDTO toRoleDto(UserRole role) {
        return RoleDTO.builder()
                .idRole(role.getIdRole())
                .role(role.getRole())
                .build();
    }

    public static MunicipalityDTO toMunicipalityDto(Municipality municipality) {
        return MunicipalityDTO.builder()
                .idMunicipality(municipality.getIdMunicipality())
                .name(municipality.getName())
                .Department(DepartmentDTO.builder()
                        .idDepartment(municipality.getDepartment().getIdDepartment())
                        .name(municipality.getDepartment().getName())
                        .country(LocationDTO.builder()
                                .idLocation(municipality.getDepartment().getCountry().getIdCountry())
                                .name(municipality.getDepartment().getCountry().getName())
                                .build())
                        .build())
                .build();
    }
}
