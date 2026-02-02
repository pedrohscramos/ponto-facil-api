package br.com.pontofacil.pontofacilapi.controller;

import br.com.pontofacil.pontofacilapi.dto.RegistroPontoAdminResponse;
import br.com.pontofacil.pontofacilapi.service.AdminPontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/pontos")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminPontoController {

    private final AdminPontoService service;

    public List<RegistroPontoAdminResponse> listar(Authentication authentication){
        return service.listarPontosDaEmpresa(authentication.getName());
    }
}
