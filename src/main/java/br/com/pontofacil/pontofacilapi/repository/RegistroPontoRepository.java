package br.com.pontofacil.pontofacilapi.repository;

import br.com.pontofacil.pontofacilapi.entity.RegistroPonto;
import br.com.pontofacil.pontofacilapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {

    Optional<RegistroPonto> findTopByUserOrderByDataHoraDesc(User user);

    List<RegistroPonto> findByUserOrderByDataHoraDesc(User user);
}
