package br.com.pontofacil.pontofacilapi.controller;

import br.com.pontofacil.pontofacilapi.dto.RegistroPontoRequest;
import br.com.pontofacil.pontofacilapi.service.RegistroPontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pontos")
@RequiredArgsConstructor
public class RegistroPontoController {

    private final RegistroPontoService service;

    @PostMapping
    public ResponseEntity<?> registrar(
            @RequestBody RegistroPontoRequest request,
            Authentication authentication
            ) {

        String email = authentication.getName();

        service.registrar(email, request.tipo());

        return ResponseEntity.ok().build();
    }
}
