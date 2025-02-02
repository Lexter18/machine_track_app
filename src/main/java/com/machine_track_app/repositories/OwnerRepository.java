package com.machine_track_app.repositories;

import com.machine_track_app.entities.Country;
import com.machine_track_app.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findAllByState(int state);

}
