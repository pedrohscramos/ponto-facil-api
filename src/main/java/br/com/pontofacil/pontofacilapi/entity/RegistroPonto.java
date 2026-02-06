package br.com.pontofacil.pontofacilapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Table(name = "registros_ponto")
@Getter
@Setter
public class RegistroPonto extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    private LocalDateTime dataHora;

    @Column(length = 10)
    private String tipo;
}
