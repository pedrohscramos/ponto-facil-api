package br.com.pontofacil.pontofacilapi.dto;

import java.time.LocalDateTime;

public record RegistroPontoResponse(Long id, String tipo, LocalDateTime dataHora) {
}
