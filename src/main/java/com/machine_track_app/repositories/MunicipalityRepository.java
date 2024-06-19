package com.machine_track_app.repositories;

import com.machine_track_app.entities.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
    List<Municipality> findByDepartmentIdDepartment(Long departmentId);
}
