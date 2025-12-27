package com.SwSOFTWARE.authMs.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DtoCreateRole(
        @NotBlank
        String name,
        @NotNull
        boolean active
) {
}
