package com.machine_track_app.repositories;

import com.machine_track_app.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.machine_track_app.enums.StateApp.DELETED;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByOwner_IdOwner(Long ownerId);

    long countByEmailAndStateIdStateIsNot(String email, Integer excludedStateId);

    long countByEmailAndIdEmployeeIsNotAndStateIdStateIsNot(String email, Long IdEmployee, Integer excludedStateId);

    default long countByEmail(String email) {
        return countByEmailAndStateIdStateIsNot(email,
                DELETED.getState());
    }

    default long countByEmailIdEmployeeIsNot(String email, Long IdEmployee) {
        return countByEmailAndIdEmployeeIsNotAndStateIdStateIsNot(email,
                IdEmployee,
                DELETED.getState());
    }


}
