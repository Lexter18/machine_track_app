package com.machine_track_app.services;

import com.machine_track_app.entities.Customer;
import com.machine_track_app.entities.Owner;

import java.util.List;
import java.util.Set;

public interface OwnerService {
    Owner createOwner(Owner owner);
    Owner getOwner(Long id);
    List<Owner> getAllOwners();
    Owner updateOwner(Long id, Owner owner);
    void deleteOwner(Long id);
    Set<Customer> getCustomersByOwner(Long ownerId);
    void associateCustomer(Long ownerId, Long customerId);
    void dissociateCustomer(Long ownerId, Long customerId);
}
