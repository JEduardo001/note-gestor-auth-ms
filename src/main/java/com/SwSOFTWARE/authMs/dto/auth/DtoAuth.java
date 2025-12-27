package com.SwSOFTWARE.authMs.dto.auth;

import com.SwSOFTWARE.authMs.dto.role.DtoRole;

import java.time.LocalDateTime;
import java.util.List;

public record DtoAuth(
        Long id,
        String username,
        String email,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime disabledAt,
        List<DtoRole> roles
) {
}
