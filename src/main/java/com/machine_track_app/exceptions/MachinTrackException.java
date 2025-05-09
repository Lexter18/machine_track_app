package com.machine_track_app.exceptions;

public class MachinTrackException extends RuntimeException {

    public MachinTrackException(String message,Throwable cause) {
        super(message, cause);
    }

    public MachinTrackException(String message) {
        super(message);
    }

}
