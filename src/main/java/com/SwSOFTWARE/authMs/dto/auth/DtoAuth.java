package com.SwSOFTWARE.authMs.dto.auth;

import com.SwSOFTWARE.authMs.dto.role.DtoRole;

import java.util.List;

public record DtoAuth(
        Long id,
        String username,
        String password,
        String email,
        boolean active,
        List<DtoRole> roles
) {
}
