package br.com.pontofacil.pontofacilapi.entity;

import br.com.pontofacil.pontofacilapi.enums.PlanoEmpresa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empresas")
@Getter
@Setter
public class Empresa extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String slug;

    @Enumerated(EnumType.STRING)
    private PlanoEmpresa plano;
}
