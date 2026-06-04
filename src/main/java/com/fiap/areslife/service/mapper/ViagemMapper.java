package com.fiap.areslife.service.mapper;

import com.fiap.areslife.dto.response.ViagemResponse;
import com.fiap.areslife.entity.ViagemTuristica;

public class ViagemMapper {

    public static ViagemResponse toResponse(ViagemTuristica viagem) {

        return new ViagemResponse(
                viagem.getId(),
                viagem.getHabitante().getId(),
                viagem.getColonia().getId(),
                viagem.getDataPartida(),
                viagem.getDataRetorno(),
                viagem.getStatusViagem(),
                viagem.getValor(),
                viagem.getPacote()
        );
    }
}
