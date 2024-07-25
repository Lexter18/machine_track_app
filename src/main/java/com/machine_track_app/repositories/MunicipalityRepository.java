package com.machine_track_app.repositories;

import com.machine_track_app.entities.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
    List<Municipality> findByDepartmentIdDepartment(Long departmentId);
}
