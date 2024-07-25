package com.machine_track_app.controller;

import com.machine_track_app.dto.response.StateDTO;
import com.machine_track_app.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/state")
public class StateController {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping()
    public ResponseEntity<List<?>> getAllState() {
        List<StateDTO> state = stateService.getState();
        return ResponseEntity.ok(state);
    }
}
