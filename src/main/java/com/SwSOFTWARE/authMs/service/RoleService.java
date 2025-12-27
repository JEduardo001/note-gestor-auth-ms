package com.SwSOFTWARE.authMs.service;

import com.SwSOFTWARE.authMs.dto.auth.DtoAuth;
import com.SwSOFTWARE.authMs.dto.role.DtoCreateRole;
import com.SwSOFTWARE.authMs.dto.role.DtoRole;
import com.SwSOFTWARE.authMs.dto.role.DtoUpdateRole;
import com.SwSOFTWARE.authMs.entity.RoleEntity;
import com.SwSOFTWARE.authMs.exception.role.RoleNameAlreadyInUseException;
import com.SwSOFTWARE.authMs.exception.role.RoleNotFoundException;
import com.SwSOFTWARE.authMs.mapper.RoleMapper;
import com.SwSOFTWARE.authMs.repository.RoleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository,RoleMapper roleMapper){
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<DtoRole> getAllRole(Integer page, Integer size){
        Pageable s = PageRequest.of(page,size);
        return roleRepository.findAll(s).map(roleMapper::toDto).getContent();
    }

    public RoleEntity getRoleById(Long id){
        return roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }

    public List<RoleEntity> getEntityRolesByIds(List<Long> idRoles){
        return roleRepository.findAllById(idRoles);
    }

    public DtoRole getRole(Long id){
        return roleMapper.toDto(getRoleById(id));
    }

    public DtoRole createRole(DtoCreateRole request){

        if(roleRepository.existsByName(request.name())){
            throw new RoleNameAlreadyInUseException();
        }

        RoleEntity role = RoleEntity.builder()
                .name(request.name())
                .active(request.active())
                .createdAt(LocalDateTime.now())
                .disabledAt( (request.active()) ? null : LocalDateTime.now() )
                .build();

        return roleMapper.toDto(roleRepository.save(role));
    }

    public DtoRole updateRole(DtoUpdateRole request){
        RoleEntity role = getRoleById(request.id());

        if(roleRepository.existsByNameAndIdNot(request.name(),request.id())){
            throw new RoleNameAlreadyInUseException();
        }

        role.setName(request.name());
        role.setActive(request.active());
        role.setDisabledAt( (request.active()) ? null : LocalDateTime.now());

       return roleMapper.toDto(roleRepository.save(role));
    }
}
