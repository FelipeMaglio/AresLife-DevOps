package com.fiap.areslife.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoTreinamentoId
        implements Serializable {

    private Long habitanteId;

    private Long treinamentoId;
}
