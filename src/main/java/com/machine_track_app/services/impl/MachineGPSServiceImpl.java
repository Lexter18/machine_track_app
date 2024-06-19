package com.machine_track_app.services.impl;

import com.machine_track_app.entities.MachineGPS;
import com.machine_track_app.entities.ProviderGPS;
import com.machine_track_app.repositories.MachineGPSRepository;
import com.machine_track_app.repositories.ProviderGPSRepository;
import com.machine_track_app.services.MachineGPSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineGPSServiceImpl implements MachineGPSService {
    private final MachineGPSRepository machineGPSRepository;
    private final ProviderGPSRepository providerGPSRepository;

    @Autowired
    public MachineGPSServiceImpl(MachineGPSRepository machineGPSRepository,
                                 ProviderGPSRepository providerGPSRepository) {
        this.machineGPSRepository = machineGPSRepository;
        this.providerGPSRepository = providerGPSRepository;
    }

    @Override
    public List<MachineGPS> getAllMachineGPS() {
        return machineGPSRepository.findAll();
    }

    @Override
    public List<ProviderGPS> getAllProviderGPS() {
        return providerGPSRepository.findAll();
    }

    @Override
    public Optional<MachineGPS> getMachineGPSById(Long id) {
        return machineGPSRepository.findById(id);
    }

    @Override
    public MachineGPS createMachineGPS(MachineGPS machineGPS) {
        return machineGPSRepository.save(machineGPS);
    }

    @Override
    public MachineGPS updateMachineGPS(Long id, MachineGPS machineGPS) {
        return machineGPSRepository.save(machineGPS);
    }

    @Override
    public void deleteMachineGPS(Long id) {
        machineGPSRepository.deleteById(id);
    }
}
