package com.SwSOFTWARE.authMs.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoLogin(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
