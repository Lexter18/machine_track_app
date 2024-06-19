package com.machine_track_app.services.impl;

import com.machine_track_app.entities.Work;
import com.machine_track_app.entities.WorkDetails;
import com.machine_track_app.repositories.WorkDetailsRepository;
import com.machine_track_app.repositories.WorkRepository;
import com.machine_track_app.services.WorkService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;
    private final WorkDetailsRepository workDetailsRepository;

    @Autowired
    public WorkServiceImpl(WorkRepository workRepository, WorkDetailsRepository workDetailsRepository) {
        this.workRepository = workRepository;
        this.workDetailsRepository = workDetailsRepository;
    }

    @Override
    public Work createWork(Work work) {
        return workRepository.save(work);
    }

    @Override
    public List<Work> getAllWorks() {
        return workRepository.findAll();
    }

    @Override
    public Optional<Work> getWorkById(Long id) {
        return workRepository.findById(id);
    }

    @Override
    public List<WorkDetails> getAllWorkDetails() {
        return workDetailsRepository.findAll();
    }

    @Override
    public WorkDetails getWorkDetailsById(Long workDetailsId) {
        return workDetailsRepository.findById(workDetailsId)
                .orElseThrow(() -> new EntityNotFoundException("WorkDetails not found with ID: " + workDetailsId));
    }

    @Override
    public Set<WorkDetails> getAllWorkDetailsByWork(Long workId) {
        return workDetailsRepository.findAllByWork_IdWork(workId);
    }

    @Override
    public WorkDetails createWorkDetails(Long workId, WorkDetails workDetails) {
        Work work = workRepository.findById(workId)
                .orElseThrow(() -> new EntityNotFoundException("Work not found with ID: " + workId));

        return  workDetailsRepository.save(workDetails);

    }

    @Override
    public WorkDetails updateWorkDetails(Long workDetailsId, WorkDetails workDetails) {
        WorkDetails existingWorkDetails = getWorkDetailsById(workDetailsId);
        return workDetailsRepository.save(existingWorkDetails);
    }

    @Override
    public void deleteWorkDetails(Long workDetailsId) {
        workDetailsRepository.deleteById(workDetailsId);
    }
}








