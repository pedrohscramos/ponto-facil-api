package br.com.pontofacil.pontofacilapi.repository;

import br.com.pontofacil.pontofacilapi.entity.Empresa;
import br.com.pontofacil.pontofacilapi.entity.RegistroPonto;
import br.com.pontofacil.pontofacilapi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {

    Optional<RegistroPonto> findTopByUserOrderByDataHoraDesc(User user);

    List<RegistroPonto> findByUser_EmpresaOrderByDataHoraDesc(Empresa empresa);

    List<RegistroPonto> findByUserOrderByDataHoraDesc(User user);

    long countByUser_EmpresaAndDataHoraAfter(
            Empresa empresa,
            LocalDateTime dataHora
    );

    Page<RegistroPonto> findByUser_EmpresaAndDataHoraBetween(
            Empresa empresa,
            LocalDateTime inicio,
            LocalDateTime fim,
            Pageable pageable
    );

    Page<RegistroPonto> findByUser_Empresa(
            Empresa empresa,
            Pageable pageable
    );
}
