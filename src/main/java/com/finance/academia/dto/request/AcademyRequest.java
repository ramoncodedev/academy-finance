package com.finance.academia.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AcademyRequest(
      @NotBlank String name,
       @NotBlank String cnpj,
        @NotBlank String email,
        @NotBlank String phone
) {
}
