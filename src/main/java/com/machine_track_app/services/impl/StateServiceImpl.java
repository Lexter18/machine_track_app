package com.machine_track_app.services.impl;

import com.machine_track_app.dto.response.RoleDTO;
import com.machine_track_app.dto.response.StateDTO;
import com.machine_track_app.repositories.StateRepository;
import com.machine_track_app.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Autowired
    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<StateDTO> getState() {
        var states = stateRepository.findAll();
        return states.stream()
                .map(state -> StateDTO.builder()
                        .idState(state.getIdState())
                        .state(state.getState())
                        .build())
                .collect(Collectors.toList());
    }
}
