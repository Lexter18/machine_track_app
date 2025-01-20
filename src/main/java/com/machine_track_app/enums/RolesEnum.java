package com.machine_track_app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RolesEnum {

    ADMIN(1),
    OPERATOR(2),
    SUPERVISOR(3),
    OWNER(4);

    final Integer rol;
}
