package com.machine_track_app.repositories;

import com.machine_track_app.entities.WorkParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkParameterRepository extends JpaRepository<WorkParameter, Long> {
    List<WorkParameter> findAllByWork_IdWork(Long workId);
}
