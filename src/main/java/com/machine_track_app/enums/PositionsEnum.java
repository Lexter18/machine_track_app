package com.machine_track_app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PositionsEnum {
    OPERATOR(1),
    SUPERVISOR(2),
    ADMINISTRATOR(3);

    final int position;

}
