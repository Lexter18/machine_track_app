package com.machine_track_app.controller;

import com.machine_track_app.entities.WorkTimeRecord;
import com.machine_track_app.services.WorkTimeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-time-records")
public class WorkTimeRecordController {

    private final WorkTimeRecordService workTimeRecordService;

    @Autowired
    public WorkTimeRecordController(WorkTimeRecordService workTimeRecordService) {
        this.workTimeRecordService = workTimeRecordService;
    }

    @PostMapping
    public ResponseEntity<WorkTimeRecord> createWorkTimeRecord(@RequestBody WorkTimeRecord workTimeRecord) {
        WorkTimeRecord createdWorkTimeRecord = workTimeRecordService.createWorkTimeRecord(workTimeRecord);
        return new ResponseEntity<>(createdWorkTimeRecord, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkTimeRecord> getWorkTimeRecordById(@PathVariable Long id) {
        WorkTimeRecord workTimeRecord = workTimeRecordService.getWorkTimeRecordById(id);
        return ResponseEntity.ok(workTimeRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkTimeRecord> updateWorkTimeRecord(
            @PathVariable Long id,
            @RequestBody WorkTimeRecord updatedWorkTimeRecord
    ) {
        WorkTimeRecord updatedWorkTimeRecordResult = workTimeRecordService.updateWorkTimeRecord(id, updatedWorkTimeRecord);
        return ResponseEntity.ok(updatedWorkTimeRecordResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkTimeRecord(@PathVariable Long id) {
        workTimeRecordService.deleteWorkTimeRecord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/work-details/{workDetailsId}")
    public ResponseEntity<List<WorkTimeRecord>> getWorkTimeRecordsByWorkDetails(
            @PathVariable Long workDetailsId
    ) {
        List<WorkTimeRecord> workTimeRecords = workTimeRecordService.getWorkTimeRecordsByWorkDetails(workDetailsId);
        return ResponseEntity.ok(workTimeRecords);
    }
}
