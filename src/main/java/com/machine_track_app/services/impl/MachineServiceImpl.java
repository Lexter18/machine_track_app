package com.machine_track_app.services.impl;

import com.machine_track_app.entities.Machine;
import com.machine_track_app.repositories.MachineRepository;
import com.machine_track_app.services.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineServiceImpl implements MachineService {
    private final MachineRepository machineRepository;

    @Autowired
    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }


    @Override
    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    @Override
    public Optional<Machine> getMachineById(Long id) {
        return machineRepository.findById(id);
    }

    @Override
    public Machine createMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    public Machine updateMachine(Long id, Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    public void deleteMachine(Long id) {
        machineRepository.deleteById(id);
    }
}
