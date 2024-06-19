package com.machine_track_app.services.impl;

import com.machine_track_app.entities.MachineBrand;
import com.machine_track_app.entities.MachineType;
import com.machine_track_app.entities.MovementType;
import com.machine_track_app.repositories.MachineBrandRepository;
import com.machine_track_app.repositories.MachineTypeRepository;
import com.machine_track_app.repositories.MovementTypeRepository;
import com.machine_track_app.services.MachineCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineCommonServiceImpl implements MachineCommonService {

    private final MachineTypeRepository machineTypeRepository;
    private final MovementTypeRepository movementTypeRepository;
    private final MachineBrandRepository machineBrandRepository;

    @Autowired
    public MachineCommonServiceImpl(
            MachineTypeRepository machineTypeRepository,
            MovementTypeRepository movementTypeRepository,
            MachineBrandRepository machineBrandRepository) {
        this.machineTypeRepository = machineTypeRepository;
        this.movementTypeRepository = movementTypeRepository;
        this.machineBrandRepository = machineBrandRepository;
    }

    @Override
    public List<MachineType> getAllMachineTypes() {
        return machineTypeRepository.findAll();
    }

    @Override
    public List<MovementType> getAllMovementTypes() {
        return movementTypeRepository.findAll();
    }

    @Override
    public List<MachineBrand> getAllMachineBrands() {
        return machineBrandRepository.findAll();
    }
}
