package com.fiap.areslife.entity;

import com.fiap.areslife.enums.Localizacao;
import com.fiap.areslife.enums.StatusTurista;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "turistas_espaciais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TuristaEspacial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_turistas_espaciais")
    @SequenceGenerator(
            name = "seq_turistas_espaciais",
            sequenceName = "SEQ_TURISTAS_ESPACIAIS",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String pais;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Localizacao destino;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTurista status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HABITANTE")
    private Habitante habitante;

    @Column(name = "DATA_CADASTRO")
    private LocalDate dataCadastro;
}
