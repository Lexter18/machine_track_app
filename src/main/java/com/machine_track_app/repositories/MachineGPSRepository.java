package com.machine_track_app.repositories;

import com.machine_track_app.entities.MachineGPS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineGPSRepository extends JpaRepository<MachineGPS, Long> {
}
