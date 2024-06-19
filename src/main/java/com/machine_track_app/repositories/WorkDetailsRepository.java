package com.machine_track_app.repositories;

import com.machine_track_app.entities.WorkDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface WorkDetailsRepository extends JpaRepository<WorkDetails, Long> {
    Set<WorkDetails> findAllByWork_IdWork(Long workId);
}
