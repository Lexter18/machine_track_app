package com.machine_track_app.repositories;

import com.machine_track_app.entities.OwnerCustomers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerCustomerRepository extends JpaRepository<OwnerCustomers, Long> {

    void deleteByOwner_IdOwnerAndCustomer_IdCustomer(Long ownerId, Long customerId);

}
