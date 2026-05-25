package com.finance.academia.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AcademyRequest(
        @NotBlank
        @Size(min = 3, max = 100)
        String name,

        String cnpj,

        @NotBlank
        @Email
        String email,
        String phone
) {
}
