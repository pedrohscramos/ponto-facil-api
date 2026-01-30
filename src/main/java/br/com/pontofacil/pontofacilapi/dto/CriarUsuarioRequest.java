package br.com.pontofacil.pontofacilapi.dto;

import jakarta.validation.constraints.NotBlank;

public record CriarUsuarioRequest(
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String role
) {
}
