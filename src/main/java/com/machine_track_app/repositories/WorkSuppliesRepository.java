package com.machine_track_app.repositories;

import com.machine_track_app.entities.WorkSupplies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkSuppliesRepository extends JpaRepository<WorkSupplies, Long> {
    List<WorkSupplies> findAllByWorkDetails_IdWorkDetail(Long workDetailId);
}
