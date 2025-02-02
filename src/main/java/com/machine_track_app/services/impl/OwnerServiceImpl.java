package com.machine_track_app.services.impl;

import com.machine_track_app.dto.response.OwnerDTO;
import com.machine_track_app.repositories.OwnerRepository;
import com.machine_track_app.services.OwnerService;
import com.machine_track_app.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.machine_track_app.enums.StateApp.ACTIVE;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<OwnerDTO> getAllOwners() {
        var owners = ownerRepository.findAllByState(ACTIVE.getState());
        return owners.stream()
                .map(UserMapper::toOwnerDto)
                .collect(Collectors.toList());
    }
}
