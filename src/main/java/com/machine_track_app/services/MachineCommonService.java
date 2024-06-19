package com.machine_track_app.services;

import com.machine_track_app.entities.MachineBrand;
import com.machine_track_app.entities.MachineType;
import com.machine_track_app.entities.MovementType;

import java.util.List;

public interface MachineCommonService {

    List<MachineType> getAllMachineTypes();
    List<MovementType> getAllMovementTypes();
    List<MachineBrand> getAllMachineBrands();

}
