package com.machine_track_app.services;

import com.machine_track_app.entities.WorkTimeRecord;

import java.util.List;

public interface WorkTimeRecordService {

    WorkTimeRecord createWorkTimeRecord(WorkTimeRecord workTimeRecord);
    WorkTimeRecord getWorkTimeRecordById(Long id);
    List<WorkTimeRecord> getAllWorkTimeRecords();
    WorkTimeRecord updateWorkTimeRecord(Long id, WorkTimeRecord updatedWorkTimeRecord);
    void deleteWorkTimeRecord(Long id);

    List<WorkTimeRecord> getWorkTimeRecordsByWorkDetails(Long workDetailsId);
}
