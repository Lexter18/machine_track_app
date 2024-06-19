package com.machine_track_app.controller;

import com.machine_track_app.entities.Work;
import com.machine_track_app.entities.WorkDetails;
import com.machine_track_app.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/works")
public class WorkController {
    private final WorkService workService;

    @Autowired
    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @PostMapping
    public ResponseEntity<Work> createWork(@RequestBody Work work) {
        Work createdWork = workService.createWork(work);
        return new ResponseEntity<>(createdWork, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Work>> getAllWorks() {
        List<Work> works = workService.getAllWorks();
        return new ResponseEntity<>(works, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Work> getWorkById(@PathVariable Long id) {
        Optional<Work> work = workService.getWorkById(id);
        return work.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{workId}/details")
    public ResponseEntity<Set<WorkDetails>> getAllWorkDetailsByWork(@PathVariable Long workId) {
        Set<WorkDetails> workDetailsList = workService.getAllWorkDetailsByWork(workId);
        return new ResponseEntity<>(workDetailsList, HttpStatus.OK);
    }

    @GetMapping("/details/{workDetailsId}")
    public ResponseEntity<WorkDetails> getWorkDetailsById(@PathVariable Long workDetailsId) {
        WorkDetails workDetails = workService.getWorkDetailsById(workDetailsId);
        return new ResponseEntity<>(workDetails, HttpStatus.OK);
    }

    @PostMapping("/{workId}/details")
    public ResponseEntity<WorkDetails> createWorkDetails(@PathVariable Long workId, @RequestBody WorkDetails workDetails) {
        WorkDetails createdWorkDetails = workService.createWorkDetails(workId, workDetails);
        return new ResponseEntity<>(createdWorkDetails, HttpStatus.CREATED);
    }

    @PutMapping("/details/{workDetailsId}")
    public ResponseEntity<WorkDetails> updateWorkDetails(@PathVariable Long workDetailsId, @RequestBody WorkDetails updatedWorkDetails) {
        WorkDetails updatedWorkDetailsEntity = workService.updateWorkDetails(workDetailsId, updatedWorkDetails);
        return new ResponseEntity<>(updatedWorkDetailsEntity, HttpStatus.OK);
    }

    @DeleteMapping("/details/{workDetailsId}")
    public ResponseEntity<Void> deleteWorkDetails(@PathVariable Long workDetailsId) {
        workService.deleteWorkDetails(workDetailsId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
