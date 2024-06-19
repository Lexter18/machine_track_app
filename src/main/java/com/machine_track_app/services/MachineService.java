package com.machine_track_app.services;

import com.machine_track_app.entities.Machine;

import java.util.List;
import java.util.Optional;

public interface MachineService {
    List<Machine> getAllMachines();
    Optional<Machine> getMachineById(Long id);
    Machine createMachine(Machine machine);
    Machine updateMachine(Long id, Machine machine);
    void deleteMachine(Long id);
}
