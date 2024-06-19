package com.machine_track_app.controller;

import com.machine_track_app.entities.Customer;
import com.machine_track_app.entities.Owner;
import com.machine_track_app.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        Owner createdOwner = ownerService.createOwner(owner);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable Long id) {
        Owner owner = ownerService.getOwner(id);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        Owner updatedOwner = ownerService.updateOwner(id, owner);
        return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/customers")
    public ResponseEntity<Set<Customer>> getCustomersByOwner(@PathVariable Long id) {
        Set<Customer> customers = ownerService.getCustomersByOwner(id);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/{ownerId}/associate/{customerId}")
    public ResponseEntity<Owner> associateCustomer(
            @PathVariable Long ownerId,
            @PathVariable Long customerId
    ) {
        ownerService.associateCustomer(ownerId, customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{ownerId}/dissociate/{customerId}")
    public ResponseEntity<Owner> dissociateCustomer(
            @PathVariable Long ownerId,
            @PathVariable Long customerId
    ) {
        ownerService.dissociateCustomer(ownerId, customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
