package com.finance.academia.dto.request;

import com.finance.academia.model.enums.Role;
import jakarta.validation.constraints.*;

public record UsuarioRequest(

        @NotBlank
        @Size(min = 3, max = 100)
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String password,

        @NotNull
        Role role
) {
}