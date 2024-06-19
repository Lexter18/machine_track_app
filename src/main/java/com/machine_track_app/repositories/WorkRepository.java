package com.machine_track_app.repositories;

import com.machine_track_app.entities.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long> {

}
