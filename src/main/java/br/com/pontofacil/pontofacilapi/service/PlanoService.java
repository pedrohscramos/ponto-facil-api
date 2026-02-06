package br.com.pontofacil.pontofacilapi.service;

import br.com.pontofacil.pontofacilapi.entity.Empresa;
import br.com.pontofacil.pontofacilapi.enums.PlanoEmpresa;
import br.com.pontofacil.pontofacilapi.exception.RegraNegocioException;
import br.com.pontofacil.pontofacilapi.repository.RegistroPontoRepository;
import br.com.pontofacil.pontofacilapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PlanoService {

    private final UserRepository userRepository;
    private final RegistroPontoRepository registroPontoRepository;

    public void validarCriacaoUsuario(Empresa empresa) {

        PlanoEmpresa plano = empresa.getPlano();

        long totalUsuarios = userRepository.countByEmpresa(empresa);

        if (totalUsuarios >= plano.getMaxUsuarios()) {
            throw new RegraNegocioException(
                    "Limite de usuários do plano " + plano + " atingido"
            );
        }
    }

    public void validarRegistroPonto(Empresa empresa) {

        PlanoEmpresa plano = empresa.getPlano();

        LocalDateTime inicioMes = LocalDateTime.now()
                .withDayOfMonth(1)
                .withHour(0).withMinute(0).withSecond(0);

        long totalMes = registroPontoRepository
                .countByUser_EmpresaAndDataHoraAfter(empresa, inicioMes);

        if (totalMes >= plano.getMaxRegistrosMes()) {
            throw new RegraNegocioException(
                    "Limite mensal de registros do plano " + plano + " atingido"
            );
        }
    }
}
