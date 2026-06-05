package com.fiap.areslife.service.mapper;

import com.fiap.areslife.dto.response.RecursoResponse;
import com.fiap.areslife.entity.Recurso;

public class RecursoMapper {

    public static RecursoResponse toResponse(Recurso recurso) {

        return new RecursoResponse(
                recurso.getId(),
                recurso.getColonia().getId(),
                recurso.getTipoRecurso(),
                recurso.getQuantidade(),
                recurso.getUnidade(),
                recurso.getNivelCritico(),
                recurso.getNivelMaximo()
        );
    }
}