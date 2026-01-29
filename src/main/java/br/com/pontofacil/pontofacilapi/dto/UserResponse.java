package br.com.pontofacil.pontofacilapi.dto;

public record UserResponse(
        Long id,
        String email,
        String role
) {
}
