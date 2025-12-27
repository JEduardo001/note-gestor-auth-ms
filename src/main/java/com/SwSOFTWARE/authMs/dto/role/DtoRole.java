package com.SwSOFTWARE.authMs.dto.role;

import java.time.LocalDateTime;

public record DtoRole(
        Long id,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime disabledAt
) {
}
