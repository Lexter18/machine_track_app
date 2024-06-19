package com.machine_track_app.repositories;

import com.machine_track_app.entities.Customer;
import com.machine_track_app.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query("SELECT oc.customer FROM OwnerCustomers oc WHERE oc.owner.idOwner = :ownerId")
    Set<Customer> findCustomersByOwnerId(Long ownerId);

}
