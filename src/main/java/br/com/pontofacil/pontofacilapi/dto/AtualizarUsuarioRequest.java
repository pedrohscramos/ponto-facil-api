package br.com.pontofacil.pontofacilapi.dto;

public record AtualizarUsuarioRequest(
        String email,
        String role
) {
}
