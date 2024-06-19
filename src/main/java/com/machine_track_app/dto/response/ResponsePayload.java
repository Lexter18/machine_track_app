package com.machine_track_app.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePayload {

    private final String code;
    private final String description;
    private final Integer statusCode;
    private final List<String> errors;

}
