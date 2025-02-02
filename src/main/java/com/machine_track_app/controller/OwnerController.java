package com.machine_track_app.controller;

import com.machine_track_app.dto.response.OwnerDTO;
import com.machine_track_app.dto.response.PositionDTO;
import com.machine_track_app.services.OwnerService;
import com.machine_track_app.services.PositionsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping()
    public ResponseEntity<List<?>> getAllOwners() {
        List<OwnerDTO> state = ownerService.getAllOwners();
        return ResponseEntity.ok(state);
    }
}
