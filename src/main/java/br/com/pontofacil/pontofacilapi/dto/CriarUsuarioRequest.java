package br.com.pontofacil.pontofacilapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarUsuarioRequest(
        @Email(message = "deve ser igual a um e-mail válido")
        @NotBlank(message = "email é obrigatório")
        String email,

        @NotBlank(message = "senha é obrigatória")
        @Size(min = 6, message = "senha deve ter no mínimo 6 caracteres")
        @NotBlank String password,

        @NotBlank(message = "role é obrigatória")
        @NotBlank String role
) {
}
