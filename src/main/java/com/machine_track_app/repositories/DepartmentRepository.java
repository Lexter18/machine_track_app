package com.machine_track_app.repositories;

import com.machine_track_app.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByCountryIdCountry(Long countryId);
}
