package com.machine_track_app.services.impl;

import com.machine_track_app.entities.WorkSupplies;
import com.machine_track_app.repositories.WorkSuppliesRepository;
import com.machine_track_app.services.WorkSuppliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkSuppliesServiceImpl implements WorkSuppliesService {

    private final WorkSuppliesRepository workSuppliesRepository;

    @Autowired
    public WorkSuppliesServiceImpl(WorkSuppliesRepository workSuppliesRepository) {
        this.workSuppliesRepository = workSuppliesRepository;
    }

    @Override
    public WorkSupplies createWorkSupplies(WorkSupplies workSupplies) {
        return workSuppliesRepository.save(workSupplies);
    }

    @Override
    public Optional<WorkSupplies> getWorkSuppliesById(Long workSuppliesId) {
        return workSuppliesRepository.findById(workSuppliesId);
    }

    @Override
    public WorkSupplies updateWorkSupplies(Long workSuppliesId, WorkSupplies updatedWorkSupplies) {
        return workSuppliesRepository.findById(workSuppliesId)
                .orElse(null); // Handle the case when the WorkSupplies with given ID is not found
    }

    @Override
    public void deleteWorkSupplies(Long workSuppliesId) {
        workSuppliesRepository.deleteById(workSuppliesId);
    }

    @Override
    public List<WorkSupplies> getAllWorkSuppliesByWorkDetail(Long idWorkDetail) {
        return workSuppliesRepository.findAllByWorkDetails_IdWorkDetail(idWorkDetail);
    }
}
