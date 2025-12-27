package com.SwSOFTWARE.authMs.mapper;

import com.SwSOFTWARE.authMs.dto.role.DtoRole;
import com.SwSOFTWARE.authMs.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    DtoRole toDto(RoleEntity role);
}
