package br.com.pontofacil.pontofacilapi.service;

import br.com.pontofacil.pontofacilapi.dto.RegistroPontoAdminResponse;
import br.com.pontofacil.pontofacilapi.entity.Empresa;
import br.com.pontofacil.pontofacilapi.entity.User;
import br.com.pontofacil.pontofacilapi.repository.RegistroPontoRepository;
import br.com.pontofacil.pontofacilapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPontoService {

    private final RegistroPontoRepository registroPontoRepository;
    private final UserRepository userRepository;

    public List<RegistroPontoAdminResponse> listarPontosDaEmpresa(String emailAdmin){
        User admin = userRepository.findByEmail(emailAdmin)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Empresa empresa = admin.getEmpresa();

        return registroPontoRepository
                .findByUser_EmpresaOrderByDataHoraDesc(empresa)
                .stream()
                .map(p -> new RegistroPontoAdminResponse(
                        p.getId(),
                        p.getUser().getEmail(),
                        p.getTipo(),
                        p.getDataHora()
                ))
                .toList();
    }
}
