package br.com.pontofacil.pontofacilapi.controller;

import br.com.pontofacil.pontofacilapi.dto.CriarUsuarioRequest;
import br.com.pontofacil.pontofacilapi.service.AdminUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/usuarios")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final AdminUserService service;

    public ResponseEntity<Void> criar(
            @RequestBody @Valid CriarUsuarioRequest request,
            Authentication authenticationh
            ){
        service.criarUsuario(authenticationh.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
