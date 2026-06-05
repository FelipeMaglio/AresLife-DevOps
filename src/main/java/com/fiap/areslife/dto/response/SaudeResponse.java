package com.fiap.areslife.dto.response;

import com.fiap.areslife.enums.StatusSaude;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SaudeResponse extends RepresentationModel<SaudeResponse> {

    private Long id;
    private Long habitanteId;

    private String pressaoArterial;
    private Integer frequenciaCardiaca;
    private BigDecimal saturacaoOxigenio;
    private BigDecimal temperaturaCorporal;

    private StatusSaude statusSaude;
    private LocalDateTime dataRegistro;
    private String observacoes;

    public SaudeResponse() {
    }

    public SaudeResponse(
            Long id,
            Long habitanteId,
            String pressaoArterial,
            Integer frequenciaCardiaca,
            BigDecimal saturacaoOxigenio,
            BigDecimal temperaturaCorporal,
            StatusSaude statusSaude,
            LocalDateTime dataRegistro,
            String observacoes) {

        this.id = id;
        this.habitanteId = habitanteId;
        this.pressaoArterial = pressaoArterial;
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.saturacaoOxigenio = saturacaoOxigenio;
        this.temperaturaCorporal = temperaturaCorporal;
        this.statusSaude = statusSaude;
        this.dataRegistro = dataRegistro;
        this.observacoes = observacoes;
    }

    public Long getId() { return id; }
    public Long getHabitanteId() { return habitanteId; }
    public String getPressaoArterial() { return pressaoArterial; }
    public Integer getFrequenciaCardiaca() { return frequenciaCardiaca; }
    public BigDecimal getSaturacaoOxigenio() { return saturacaoOxigenio; }
    public BigDecimal getTemperaturaCorporal() { return temperaturaCorporal; }
    public StatusSaude getStatusSaude() { return statusSaude; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }
    public String getObservacoes() { return observacoes; }
}
