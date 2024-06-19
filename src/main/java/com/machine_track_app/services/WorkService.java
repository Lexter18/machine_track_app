package com.machine_track_app.services;

import com.machine_track_app.entities.Work;
import com.machine_track_app.entities.WorkDetails;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface WorkService {

    Work createWork(Work work);
    List<Work> getAllWorks();
    Optional<Work> getWorkById(Long id);

    List<WorkDetails> getAllWorkDetails();
    WorkDetails getWorkDetailsById(Long workDetailsId);
    WorkDetails createWorkDetails(Long workId, WorkDetails workDetails);
    WorkDetails updateWorkDetails(Long workDetailsId, WorkDetails workDetails);
    void deleteWorkDetails(Long workDetailsId);
    Set<WorkDetails> getAllWorkDetailsByWork(Long workId);

}
