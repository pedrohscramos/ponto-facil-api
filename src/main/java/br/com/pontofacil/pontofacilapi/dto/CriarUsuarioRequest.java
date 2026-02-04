package br.com.pontofacil.pontofacilapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CriarUsuarioRequest(
        @Email(message = "deve ser igual a um e-mail válido")
        @NotBlank(message = "email é obrigatório")
        String email,

        @NotBlank String password,
        @NotBlank String role
) {
}
