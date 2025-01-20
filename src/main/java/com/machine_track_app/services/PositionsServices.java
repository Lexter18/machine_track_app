package com.machine_track_app.services;

import com.machine_track_app.dto.response.PositionDTO;

import java.util.List;

public interface PositionsServices {

    List<PositionDTO> getPositions();

}
