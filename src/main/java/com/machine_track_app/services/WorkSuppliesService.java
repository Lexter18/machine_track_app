package com.machine_track_app.services;

import com.machine_track_app.entities.WorkSupplies;

import java.util.List;
import java.util.Optional;

public interface WorkSuppliesService {
    WorkSupplies createWorkSupplies(WorkSupplies workSupplies);
    Optional<WorkSupplies> getWorkSuppliesById(Long workSuppliesId);
    WorkSupplies updateWorkSupplies(Long workSuppliesId, WorkSupplies updatedWorkSupplies);
    void deleteWorkSupplies(Long workSuppliesId);
    List<WorkSupplies> getAllWorkSuppliesByWorkDetail(Long idWorkDetail);
}
