package com.machine_track_app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum RolesEnum {

    ADMIN(1, List.of(1, 4)),
    OPERATOR(2, List.of()),
    SUPERVISOR(3, List.of()),
    OWNER(4, List.of(2, 3, 4));

    final Integer rol;
    private final List<Integer> visibleRolIds;

    public static List<Integer> listVisibleRolByIds(Integer roleId) {
        return Arrays.stream(values())
                .filter(role -> role.rol.equals(roleId))
                .findFirst()
                .map(RolesEnum::getVisibleRolIds)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado: " + roleId));
    }

    public static String getRoleNameById(Integer roleId) {
        return Arrays.stream(values())
                .filter(role -> role.rol.equals(roleId))
                .findFirst()
                .map(Enum::name)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado: " + roleId));
    }
}
