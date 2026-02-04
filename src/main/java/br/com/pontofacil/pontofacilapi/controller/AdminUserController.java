package br.com.pontofacil.pontofacilapi.controller;

import br.com.pontofacil.pontofacilapi.dto.AtualizarUsuarioRequest;
import br.com.pontofacil.pontofacilapi.dto.CriarUsuarioRequest;
import br.com.pontofacil.pontofacilapi.dto.UserResponse;
import br.com.pontofacil.pontofacilapi.service.AdminUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/usuarios")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final AdminUserService service;

    @PostMapping
    public ResponseEntity<Void> criar(
            @RequestBody @Valid CriarUsuarioRequest request,
            Authentication authenticationh
            ){
        service.criarUsuario(authenticationh.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<UserResponse> listar(Authentication authentication){
        return service.listarUsuarios(authentication.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @RequestBody AtualizarUsuarioRequest request,
            Authentication authentication
            ){
        service.atualizarUsuario(authentication.getName(), id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id,
            Authentication authentication
            ){
        service.deletarUsuario(authentication.getName(), id);
        return ResponseEntity.noContent().build();
    }

}
