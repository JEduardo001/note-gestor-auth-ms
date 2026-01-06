package com.SwSOFTWARE.authMs.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record DtoCreateUser(
        UUID id,
        String name,
        LocalDateTime birthday,
        boolean active
) {
}
