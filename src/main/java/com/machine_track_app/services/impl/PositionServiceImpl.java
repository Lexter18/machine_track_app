package com.machine_track_app.services.impl;

import com.machine_track_app.dto.response.PositionDTO;
import com.machine_track_app.dto.response.StateDTO;
import com.machine_track_app.repositories.PositionsRepository;
import com.machine_track_app.repositories.StateRepository;
import com.machine_track_app.services.PositionsServices;
import com.machine_track_app.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionsServices {

    private final PositionsRepository positionsRepository;

    @Autowired
    public PositionServiceImpl(PositionsRepository positionsRepository) {
        this.positionsRepository = positionsRepository;
    }


    @Override
    public List<PositionDTO> getPositions() {
        var positions = positionsRepository.findAll();
        return positions.stream()
                .map(position -> PositionDTO.builder()
                        .idPosition(position.getIdPosition())
                        .position(position.getPosition())
                        .build())
                .collect(Collectors.toList());
    }
}
