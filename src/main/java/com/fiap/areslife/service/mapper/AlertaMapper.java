package com.fiap.areslife.service.mapper;

import com.fiap.areslife.dto.response.AlertaResponse;
import com.fiap.areslife.entity.Alerta;

public class AlertaMapper {

    public static AlertaResponse toResponse(Alerta alerta) {

        return new AlertaResponse(
                alerta.getId(),
                alerta.getColonia().getId(),
                alerta.getTipoAlerta(),
                alerta.getDescricao(),
                alerta.getSeveridade(),
                alerta.getStatus(),
                alerta.getDataEmissao(),
                alerta.getResolvidoEm()
        );
    }
}
