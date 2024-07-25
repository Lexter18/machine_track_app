package com.machine_track_app.exceptions.config;

import com.machine_track_app.dto.response.ResponsePayload;
import com.machine_track_app.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CommonExceptionsHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        log.error(ex.getErrors().toString());
        var error = ResponsePayload.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .description(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errors(ex.getErrors())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleUnknownException(Exception ex) {
        log.error(ex.getMessage());
        var error = ResponsePayload.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .description(ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
