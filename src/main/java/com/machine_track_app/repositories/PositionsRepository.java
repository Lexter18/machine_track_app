package com.machine_track_app.repositories;

import com.machine_track_app.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionsRepository extends JpaRepository<Position, Integer> {


}
