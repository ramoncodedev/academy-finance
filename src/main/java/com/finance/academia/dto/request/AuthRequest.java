package com.finance.academia.dto.request;

public record AuthRequest(
        String email,
        String password
) {
}
