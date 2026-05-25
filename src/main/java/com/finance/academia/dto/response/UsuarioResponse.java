package com.finance.academia.dto.response;

import com.finance.academia.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UsuarioResponse(

        Long academyId,
        String name,
        String email,
        String password,
        Role role,
        LocalDateTime createdAt
) {
}
