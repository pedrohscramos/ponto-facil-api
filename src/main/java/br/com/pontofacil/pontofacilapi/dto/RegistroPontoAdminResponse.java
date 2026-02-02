package br.com.pontofacil.pontofacilapi.dto;

import java.time.LocalDateTime;

public record RegistroPontoAdminResponse(
        Long id,
        String emailUsuario,
        String tipo,
        LocalDateTime dataHora
) {
}
