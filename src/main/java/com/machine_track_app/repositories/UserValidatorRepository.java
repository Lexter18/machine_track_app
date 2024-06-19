package com.machine_track_app.repositories;

import com.machine_track_app.entities.UserValidator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserValidatorRepository extends JpaRepository<UserValidator, Long> {
}
