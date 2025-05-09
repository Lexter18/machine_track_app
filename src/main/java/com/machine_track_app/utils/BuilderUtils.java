package com.machine_track_app.utils;

/**
* Utility for making conditional updates
* only if the value is not null.
*/
public class BuilderUtils {

    public static <T> T choose(T newValue, T originalValue) {
        return newValue != null ? newValue : originalValue;
    }

}
