package com.machine_track_app.controller;

import com.machine_track_app.entities.MachineBrand;
import com.machine_track_app.entities.MachineType;
import com.machine_track_app.entities.MovementType;
import com.machine_track_app.services.MachineCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/machine_common")
public class MachineCommonController {

    private final MachineCommonService machineCommonService;

    @Autowired
    public MachineCommonController(MachineCommonService machineCommonService) {
        this.machineCommonService = machineCommonService;
    }

    @GetMapping("/machineTypes")
    public ResponseEntity<List<MachineType>> getAllMachineTypes() {
        List<MachineType> machineTypes = machineCommonService.getAllMachineTypes();
        return ResponseEntity.ok(machineTypes);
    }

    @GetMapping("/movementTypes")
    public ResponseEntity<List<MovementType>> getAllMovementTypes() {
        List<MovementType> movementTypes = machineCommonService.getAllMovementTypes();
        return ResponseEntity.ok(movementTypes);
    }

    @GetMapping("/machineBrands")
    public ResponseEntity<List<MachineBrand>> getAllMachineBrands() {
        List<MachineBrand> machineBrands = machineCommonService.getAllMachineBrands();
        return ResponseEntity.ok(machineBrands);
    }
}
