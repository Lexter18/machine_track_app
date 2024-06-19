package com.machine_track_app.services;

import com.machine_track_app.entities.WorkParameter;

import java.util.List;
import java.util.Optional;

public interface WorkParameterService {
    Optional<WorkParameter> getWorkParameterById(Long workParameterId);
    List<WorkParameter> getAllWorkParametersByWork(Long idWork);
}
