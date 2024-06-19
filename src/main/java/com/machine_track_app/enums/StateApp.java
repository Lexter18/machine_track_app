package com.machine_track_app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StateApp {
    ACTIVE(1),
    DISABLED(2),
    DELETED(3);

    final int state;

}
