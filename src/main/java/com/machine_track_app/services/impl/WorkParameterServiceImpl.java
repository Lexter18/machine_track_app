package com.machine_track_app.services.impl;

import com.machine_track_app.entities.WorkParameter;
import com.machine_track_app.repositories.WorkParameterRepository;
import com.machine_track_app.services.WorkParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkParameterServiceImpl implements WorkParameterService {

    private final WorkParameterRepository workParameterRepository;

    @Autowired
    public WorkParameterServiceImpl(WorkParameterRepository workParameterRepository) {
        this.workParameterRepository = workParameterRepository;
    }

    @Override
    public Optional<WorkParameter> getWorkParameterById(Long workParameterId) {
        return workParameterRepository.findById(workParameterId);
    }

    @Override
    public List<WorkParameter> getAllWorkParametersByWork(Long idWork) {
        return workParameterRepository.findAllByWork_IdWork(idWork);
    }
}
