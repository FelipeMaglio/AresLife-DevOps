package com.fiap.areslife.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "avaliacoes_treinamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvaliacaoTreinamento {

    @EmbeddedId
    private AvaliacaoTreinamentoId id;

    @ManyToOne
    @MapsId("habitanteId")
    @JoinColumn(name = "id_habitante")
    private Habitante habitante;

    @ManyToOne
    @MapsId("treinamentoId")
    @JoinColumn(name = "id_treinamento")
    private Treinamento treinamento;

    private Integer nota;

    private String comentario;
}
