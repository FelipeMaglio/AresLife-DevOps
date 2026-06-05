package com.fiap.areslife.dto.response;

import com.fiap.areslife.enums.StatusTreinamento;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TreinamentoResponse extends RepresentationModel<TreinamentoResponse> {

    private Long id;
    private Long habitanteId;
    private String tipoTreinamento;
    private Integer cargaHoraria;
    private StatusTreinamento status;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
    private BigDecimal notaFinal;

    public TreinamentoResponse() {
    }

    public TreinamentoResponse(
            Long id,
            Long habitanteId,
            String tipoTreinamento,
            Integer cargaHoraria,
            StatusTreinamento status,
            LocalDate dataInicio,
            LocalDate dataConclusao,
            BigDecimal notaFinal) {

        this.id = id;
        this.habitanteId = habitanteId;
        this.tipoTreinamento = tipoTreinamento;
        this.cargaHoraria = cargaHoraria;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataConclusao = dataConclusao;
        this.notaFinal = notaFinal;
    }

    public Long getId() { return id; }
    public Long getHabitanteId() { return habitanteId; }
    public String getTipoTreinamento() { return tipoTreinamento; }
    public Integer getCargaHoraria() { return cargaHoraria; }
    public StatusTreinamento getStatus() { return status; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataConclusao() { return dataConclusao; }
    public BigDecimal getNotaFinal() { return notaFinal; }
}