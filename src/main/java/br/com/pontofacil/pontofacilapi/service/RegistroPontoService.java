package br.com.pontofacil.pontofacilapi.service;

import br.com.pontofacil.pontofacilapi.entity.RegistroPonto;
import br.com.pontofacil.pontofacilapi.entity.User;
import br.com.pontofacil.pontofacilapi.repository.RegistroPontoRepository;
import br.com.pontofacil.pontofacilapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistroPontoService {

    private final RegistroPontoRepository repository;
    private final UserRepository userRepository;

    public RegistroPonto registrar(String email, String tipo){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        repository.findTopByUserOrderByDataHoraDesc(user)
                .ifPresent(ultimo -> {
                    if (ultimo.getTipo().equals(tipo)) {
                        throw new IllegalStateException(
                                "Não é possível registrar dois pontos consecutivos do mesmo tipo."
                        );
                    }
                });
        RegistroPonto novoRegistro = new RegistroPonto();
        novoRegistro.setUser(user);
        novoRegistro.setTipo(tipo);
        novoRegistro.setDataHora(LocalDateTime.now());

        return repository.save(novoRegistro);
    }
}
