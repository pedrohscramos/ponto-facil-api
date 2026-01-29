package br.com.pontofacil.pontofacilapi.controller;

import br.com.pontofacil.pontofacilapi.dto.RegistroPontoRequest;
import br.com.pontofacil.pontofacilapi.dto.RegistroPontoResponse;
import br.com.pontofacil.pontofacilapi.service.RegistroPontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/meus")
    public List<RegistroPontoResponse> meusPontos(
            Authentication authentication
    ) {
        String email = authentication.getName();
        return service.listarMeusPontos(email);
    }
}
