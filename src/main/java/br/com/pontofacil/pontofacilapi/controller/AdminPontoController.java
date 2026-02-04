package br.com.pontofacil.pontofacilapi.controller;

import br.com.pontofacil.pontofacilapi.dto.RegistroPontoAdminResponse;
import br.com.pontofacil.pontofacilapi.service.AdminPontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/pontos")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminPontoController {

    private final AdminPontoService service;

    @GetMapping
    public Page<RegistroPontoAdminResponse> listar(
            Authentication authentication,
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer ano,
            @PageableDefault(size = 10, sort = "dataHora", direction = Sort.Direction.DESC) Pageable pageable){
        return service.listarPontos(authentication.getName(), mes, ano, pageable);
    }
}
