package br.com.pontofacil.pontofacilapi.dto;

import jakarta.validation.constraints.Email;

public record AtualizarUsuarioRequest(
        @Email(message = "deve ser um email válido")
        String email,
        String role
) {
}
