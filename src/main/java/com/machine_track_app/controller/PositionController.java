package com.machine_track_app.controller;

import com.machine_track_app.dto.response.PositionDTO;
import com.machine_track_app.dto.response.StateDTO;
import com.machine_track_app.services.PositionsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/position")
public class PositionController {

    private final PositionsServices positionsServices;

    @Autowired
    public PositionController(PositionsServices positionsServices) {
        this.positionsServices = positionsServices;
    }

    @GetMapping()
    public ResponseEntity<List<?>> getAllPosition() {
        List<PositionDTO> state = positionsServices.getPositions();
        return ResponseEntity.ok(state);
    }
}
