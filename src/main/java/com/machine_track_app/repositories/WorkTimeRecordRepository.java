package com.machine_track_app.repositories;

import com.machine_track_app.entities.WorkTimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTimeRecordRepository extends JpaRepository<WorkTimeRecord, Long> {
    List<WorkTimeRecord> findByWorkDetails_IdWorkDetail(Long workDetailsId);
}
