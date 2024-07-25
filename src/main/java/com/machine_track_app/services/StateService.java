package com.machine_track_app.services;

import com.machine_track_app.dto.response.RoleDTO;
import com.machine_track_app.dto.response.StateDTO;

import java.util.List;

public interface StateService {

    List<StateDTO> getState();

}
