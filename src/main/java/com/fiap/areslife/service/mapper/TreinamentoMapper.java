package com.fiap.areslife.service.mapper;

import com.fiap.areslife.dto.response.TreinamentoResponse;
import com.fiap.areslife.entity.Treinamento;

public class TreinamentoMapper {

    public static TreinamentoResponse toResponse(Treinamento treinamento) {

        return new TreinamentoResponse(
                treinamento.getId(),
                treinamento.getHabitante().getId(),
                treinamento.getTipoTreinamento(),
                treinamento.getCargaHoraria(),
                treinamento.getStatus(),
                treinamento.getDataInicio(),
                treinamento.getDataConclusao(),
                treinamento.getNotaFinal()
        );
    }
}