package com.machine_track_app.controller;

import com.machine_track_app.entities.WorkParameter;
import com.machine_track_app.services.WorkParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/work-parameters")
public class WorkParameterController {

    private final WorkParameterService workParameterService;

    @Autowired
    public WorkParameterController(WorkParameterService workParameterService) {
        this.workParameterService = workParameterService;
    }

    @GetMapping("/{workParameterId}")
    public ResponseEntity<WorkParameter> getWorkParameterById(@PathVariable Long workParameterId) {
        Optional<WorkParameter> workParameterOptional = workParameterService.getWorkParameterById(workParameterId);
        return workParameterOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/work/{idWork}")
    public ResponseEntity<List<WorkParameter>> getAllWorkParametersByWork(@PathVariable Long idWork) {
        List<WorkParameter> workParameters = workParameterService.getAllWorkParametersByWork(idWork);
        return ResponseEntity.ok(workParameters);
    }
}
