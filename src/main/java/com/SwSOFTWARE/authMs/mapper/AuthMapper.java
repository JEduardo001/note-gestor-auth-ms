package com.SwSOFTWARE.authMs.mapper;

import com.SwSOFTWARE.authMs.dto.auth.DtoAuth;
import com.SwSOFTWARE.authMs.entity.AuthEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface AuthMapper {
    DtoAuth toDto(AuthEntity auth);
}
