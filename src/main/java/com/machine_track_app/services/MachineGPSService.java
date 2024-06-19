package com.machine_track_app.services;

import com.machine_track_app.entities.MachineGPS;
import com.machine_track_app.entities.ProviderGPS;

import java.util.List;
import java.util.Optional;

public interface MachineGPSService {
    List<MachineGPS> getAllMachineGPS();
    List<ProviderGPS> getAllProviderGPS();
    Optional<MachineGPS> getMachineGPSById(Long id);
    MachineGPS createMachineGPS(MachineGPS machineGPS);
    MachineGPS updateMachineGPS(Long id, MachineGPS machineGPS);
    void deleteMachineGPS(Long id);
}
