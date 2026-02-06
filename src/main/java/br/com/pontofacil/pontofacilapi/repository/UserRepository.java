package br.com.pontofacil.pontofacilapi.repository;

import br.com.pontofacil.pontofacilapi.entity.Empresa;
import br.com.pontofacil.pontofacilapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByEmpresa(Empresa empresa);
    boolean existsByEmail(String email);
    long countByEmpresa(Empresa empresa);
}
