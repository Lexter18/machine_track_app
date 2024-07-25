package com.machine_track_app.services.impl;

import com.machine_track_app.dto.response.RoleDTO;
import com.machine_track_app.repositories.RoleRepository;
import com.machine_track_app.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> getRoles() {
        var roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> RoleDTO.builder()
                        .idRole(role.getIdRole())
                        .role(role.getRole())
                        .roleDescription(role.getRoleDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
