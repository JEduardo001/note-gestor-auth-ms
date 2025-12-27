package com.SwSOFTWARE.authMs.dto.auth;


import jakarta.validation.constraints.*;

import java.util.List;

public record DtoCreateUser(
        @NotBlank
        String username,
        @NotBlank
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_\\-]).{8,35}$", // 1 upperCase, 1 lower case, 1 number 1 symbol
                message = "Password must contain upper, lower, number and special character"

        )
        String password,
        String passwordRepeat,
        @Email
        String email,
        @NotNull
        boolean active,
        @NotNull
        List<Long> idRoles
) {
}
