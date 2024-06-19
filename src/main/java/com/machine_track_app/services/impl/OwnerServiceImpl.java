package com.machine_track_app.services.impl;

import com.machine_track_app.entities.Customer;
import com.machine_track_app.entities.Owner;
import com.machine_track_app.entities.OwnerCustomers;
import com.machine_track_app.repositories.CustomerRepository;
import com.machine_track_app.repositories.OwnerCustomerRepository;
import com.machine_track_app.repositories.OwnerRepository;
import com.machine_track_app.services.OwnerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final CustomerRepository customerRepository;
    private final OwnerCustomerRepository ownerCustomerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository, CustomerRepository customerRepository,
                            OwnerCustomerRepository ownerCustomerRepository) {
        this.ownerRepository = ownerRepository;
        this.customerRepository = customerRepository;
        this.ownerCustomerRepository = ownerCustomerRepository;
    }

    @Override
    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner getOwner(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);

        if (optionalOwner.isPresent()) {
            return optionalOwner.get();
        } else {
            throw new EntityNotFoundException("Owner with ID " + id + " not found");
        }
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner updateOwner(Long id, Owner owner) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);

        if (optionalOwner.isPresent()) {
            Owner ownerToUpdate = optionalOwner.get();

            return ownerRepository.save(ownerToUpdate);
        } else {
            throw new EntityNotFoundException("Owner with ID " + id + " not found");
        }
    }

    @Override
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Set<Customer> getCustomersByOwner(Long ownerId) {
        return ownerRepository.findCustomersByOwnerId(ownerId);
    }

    @Override
    public void associateCustomer(Long ownerId, Long customerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + ownerId));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + customerId));

        boolean isAlreadyAssociated = owner.getOwnerCustomers().stream()
                .anyMatch(oc -> oc.getCustomer().getIdCustomer().equals(customerId));

        if (!isAlreadyAssociated) {
            OwnerCustomers ownerCustomer = OwnerCustomers.builder()
                    .owner(owner)
                    .customer(customer)
                    .state(1)
                    .build();
            ownerCustomerRepository.save(ownerCustomer);
        }
    }

    @Override
    public void dissociateCustomer(Long ownerId, Long customerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + ownerId));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + customerId));

        owner.getOwnerCustomers().removeIf(oc -> oc.getCustomer().getIdCustomer().equals(customerId));
        ownerCustomerRepository.deleteByOwner_IdOwnerAndCustomer_IdCustomer(ownerId, customerId);
    }


}
