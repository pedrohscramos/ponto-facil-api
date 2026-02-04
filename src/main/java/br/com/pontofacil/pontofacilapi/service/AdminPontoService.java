package br.com.pontofacil.pontofacilapi.service;

import br.com.pontofacil.pontofacilapi.dto.RegistroPontoAdminResponse;
import br.com.pontofacil.pontofacilapi.entity.Empresa;
import br.com.pontofacil.pontofacilapi.entity.RegistroPonto;
import br.com.pontofacil.pontofacilapi.entity.User;
import br.com.pontofacil.pontofacilapi.repository.RegistroPontoRepository;
import br.com.pontofacil.pontofacilapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPontoService {

    private final RegistroPontoRepository registroPontoRepository;
    private final UserRepository userRepository;

    public Page<RegistroPontoAdminResponse> listarPontos(
            String emailAdmin,
            Integer mes,
            Integer ano,
            Pageable pageable){

        User admin = userRepository.findByEmail(emailAdmin)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Empresa empresa = admin.getEmpresa();

        Page<RegistroPonto> page;

        if(mes != null && ano != null){

            LocalDateTime inicio = LocalDate
                    .of(ano, mes, 1)
                    .atStartOfDay();

            LocalDateTime fim = inicio
                    .plusMonths(1)
                    .minusSeconds(1);

            page = registroPontoRepository
                    .findByUser_EmpresaAndDataHoraBetween(
                            empresa,
                            inicio,
                            fim,
                            pageable
                    );

        } else {
            page = registroPontoRepository
                    .findByUser_Empresa(empresa, pageable);
        }

       return page.map(p -> new RegistroPontoAdminResponse(
               p.getId(),
               p.getUser().getEmail(),
               p.getTipo(),
               p.getDataHora()
       ));
    }
}
