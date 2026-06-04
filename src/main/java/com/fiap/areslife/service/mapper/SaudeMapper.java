package com.fiap.areslife.service.mapper;

import com.fiap.areslife.dto.response.SaudeResponse;
import com.fiap.areslife.entity.SaudeHabitante;

public class SaudeMapper {

    public static SaudeResponse toResponse(SaudeHabitante saude) {

        return new SaudeResponse(
                saude.getId(),
                saude.getHabitante().getId(),
                saude.getSinaisVitais().getPressaoArterial(),
                saude.getSinaisVitais().getFrequenciaCardiaca(),
                saude.getSinaisVitais().getSaturacaoOxigenio(),
                saude.getSinaisVitais().getTemperaturaCorporal(),
                saude.getStatusSaude(),
                saude.getDataRegistro(),
                saude.getObservacoes()
        );
    }
}