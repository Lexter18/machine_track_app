package com.machine_track_app.repositories;

import com.machine_track_app.entities.MovementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementTypeRepository extends JpaRepository<MovementType, Integer> {

}
