package com.SwSOFTWARE.authMs.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoUpdateRole(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotNull
        boolean active
) {
}

