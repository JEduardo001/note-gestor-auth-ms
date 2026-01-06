package com.SwSOFTWARE.authMs.dto.auth;

import com.SwSOFTWARE.authMs.dto.role.DtoRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record DtoUpdateAuth(
        @NotNull
        UUID id ,
        @NotBlank
        String username,
        @Email
        String email,
        @NotNull
        boolean active,
        @NotNull
        List<Long> idRoles
) {
}
