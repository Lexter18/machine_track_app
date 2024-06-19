package com.machine_track_app.controller;

import com.machine_track_app.entities.WorkSupplies;
import com.machine_track_app.services.WorkSuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-supplies")
public class WorkSuppliesController {

    private final WorkSuppliesService workSuppliesService;

    @Autowired
    public WorkSuppliesController(WorkSuppliesService workSuppliesService) {
        this.workSuppliesService = workSuppliesService;
    }

    @PostMapping
    public ResponseEntity<WorkSupplies> createWorkSupplies(@RequestBody WorkSupplies workSupplies) {
        WorkSupplies createdWorkSupplies = workSuppliesService.createWorkSupplies(workSupplies);
        return new ResponseEntity<>(createdWorkSupplies, HttpStatus.CREATED);
    }

    @GetMapping("/{workSuppliesId}")
    public ResponseEntity<WorkSupplies> getWorkSuppliesById(@PathVariable Long workSuppliesId) {
        return workSuppliesService.getWorkSuppliesById(workSuppliesId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{workSuppliesId}")
    public ResponseEntity<WorkSupplies> updateWorkSupplies(
            @PathVariable Long workSuppliesId,
            @RequestBody WorkSupplies updatedWorkSupplies) {
        WorkSupplies result = workSuppliesService.updateWorkSupplies(workSuppliesId, updatedWorkSupplies);
        return (result != null) ?
                ResponseEntity.ok(result) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{workSuppliesId}")
    public ResponseEntity<Void> deleteWorkSupplies(@PathVariable Long workSuppliesId) {
        workSuppliesService.deleteWorkSupplies(workSuppliesId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/work-detail/{workDetailId}")
    public ResponseEntity<List<WorkSupplies>> getAllWorkSuppliesByWorkDetail(@PathVariable Long workDetailId) {
        List<WorkSupplies> workSuppliesList = workSuppliesService.getAllWorkSuppliesByWorkDetail(workDetailId);
        return ResponseEntity.ok(workSuppliesList);
    }
}
