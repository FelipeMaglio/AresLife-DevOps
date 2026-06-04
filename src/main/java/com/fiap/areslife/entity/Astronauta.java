package com.fiap.areslife.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("ASTRONAUTA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Astronauta extends Habitante {

    @Column(name = "missao_atual", length = 100)
    private String missaoAtual;
}
