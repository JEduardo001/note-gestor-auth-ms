package com.SwSOFTWARE.authMs.service;

import com.SwSOFTWARE.authMs.entity.RoleEntity;
import com.SwSOFTWARE.authMs.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public List<RoleEntity> getRolesByIds(List<Long> idRoles){
        return roleRepository.findAllById(idRoles);
    }
}
