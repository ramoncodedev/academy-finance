package com.finance.academia.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AcademyResponse(
        Long id,
        String name,
        String cnpj,
        String email,
        String phone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
