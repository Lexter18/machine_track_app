package com.machine_track_app.repositories;

import com.machine_track_app.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRole, Integer> {

}
