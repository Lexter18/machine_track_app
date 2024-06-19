package com.machine_track_app.controller;

import com.machine_track_app.entities.MachineGPS;
import com.machine_track_app.entities.ProviderGPS;
import com.machine_track_app.services.MachineGPSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machinegps")
public class MachineGPSController {
    private final MachineGPSService machineGPSService;

    @Autowired
    public MachineGPSController(MachineGPSService machineGPSService) {
        this.machineGPSService = machineGPSService;
    }

    @GetMapping
    public ResponseEntity<List<MachineGPS>> getAllMachineGPS() {
        List<MachineGPS> machineGPSList = machineGPSService.getAllMachineGPS();
        return ResponseEntity.ok(machineGPSList);
    }

    @GetMapping("/providergps")
    public ResponseEntity<List<ProviderGPS>> getAllProviderGPS() {
        List<ProviderGPS> providerGPSList = machineGPSService.getAllProviderGPS();
        return ResponseEntity.ok(providerGPSList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MachineGPS> getMachineGPSById(@PathVariable Long id) {
        return machineGPSService.getMachineGPSById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MachineGPS> createMachineGPS(@RequestBody MachineGPS machineGPS) {
        MachineGPS createdMachineGPS = machineGPSService.createMachineGPS(machineGPS);
        return ResponseEntity.ok(createdMachineGPS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MachineGPS> updateMachineGPS(@PathVariable Long id, @RequestBody MachineGPS machineGPS) {
        return machineGPSService.getMachineGPSById(id)
                .map(existingMachineGPS -> {
                    MachineGPS updatedMachineGPS = machineGPSService.updateMachineGPS(id, machineGPS);
                    return ResponseEntity.ok(updatedMachineGPS);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMachineGPS(@PathVariable Long id) {
        machineGPSService.deleteMachineGPS(id);
        return ResponseEntity.noContent().build();
    }
}
