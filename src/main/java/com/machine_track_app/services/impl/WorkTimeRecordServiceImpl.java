package com.machine_track_app.services.impl;

import com.machine_track_app.entities.WorkTimeRecord;
import com.machine_track_app.repositories.WorkTimeRecordRepository;
import com.machine_track_app.services.WorkTimeRecordService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkTimeRecordServiceImpl implements WorkTimeRecordService {

    private final WorkTimeRecordRepository workTimeRecordRepository;

    @Autowired
    public WorkTimeRecordServiceImpl(WorkTimeRecordRepository workTimeRecordRepository) {
        this.workTimeRecordRepository = workTimeRecordRepository;
    }

    @Override
    public WorkTimeRecord createWorkTimeRecord(WorkTimeRecord workTimeRecord) {
        return workTimeRecordRepository.save(workTimeRecord);
    }

    @Override
    public WorkTimeRecord getWorkTimeRecordById(Long workTimeRecordId) {
        return workTimeRecordRepository.findById(workTimeRecordId)
                .orElseThrow(() -> new EntityNotFoundException("WorkTimeRecord not found with ID: " + workTimeRecordId));
    }

    @Override
    public List<WorkTimeRecord> getAllWorkTimeRecords() {
        return workTimeRecordRepository.findAll();
    }

    @Override
    public WorkTimeRecord updateWorkTimeRecord(Long workTimeRecordId, WorkTimeRecord updatedWorkTimeRecord) {
        WorkTimeRecord existingWorkTimeRecord = getWorkTimeRecordById(workTimeRecordId);
        // Implementa la lógica de actualización según tus requisitos
        // Puedes usar algo como ModelMapper para copiar propiedades si es necesario
        // Ejemplo: modelMapper.map(updatedWorkTimeRecord, existingWorkTimeRecord);
        return workTimeRecordRepository.save(existingWorkTimeRecord);
    }

    @Override
    public void deleteWorkTimeRecord(Long workTimeRecordId) {
        workTimeRecordRepository.deleteById(workTimeRecordId);
    }

    @Override
    public List<WorkTimeRecord> getWorkTimeRecordsByWorkDetails(Long workDetailsId) {
        return workTimeRecordRepository.findByWorkDetails_IdWorkDetail(workDetailsId);
    }
}
