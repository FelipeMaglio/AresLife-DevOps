package com.fiap.areslife.dto.response;

import com.fiap.areslife.enums.PacoteViagem;
import com.fiap.areslife.enums.StatusViagem;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ViagemResponse extends RepresentationModel<ViagemResponse> {

    private Long id;
    private Long habitanteId;
    private Long coloniaId;
    private LocalDate dataPartida;
    private LocalDate dataRetorno;
    private StatusViagem statusViagem;
    private BigDecimal valor;
    private PacoteViagem pacote;

    public ViagemResponse() {
    }

    public ViagemResponse(
            Long id,
            Long habitanteId,
            Long coloniaId,
            LocalDate dataPartida,
            LocalDate dataRetorno,
            StatusViagem statusViagem,
            BigDecimal valor,
            PacoteViagem pacote) {

        this.id = id;
        this.habitanteId = habitanteId;
        this.coloniaId = coloniaId;
        this.dataPartida = dataPartida;
        this.dataRetorno = dataRetorno;
        this.statusViagem = statusViagem;
        this.valor = valor;
        this.pacote = pacote;
    }

    public Long getId() { return id; }
    public Long getHabitanteId() { return habitanteId; }
    public Long getColoniaId() { return coloniaId; }
    public LocalDate getDataPartida() { return dataPartida; }
    public LocalDate getDataRetorno() { return dataRetorno; }
    public StatusViagem getStatusViagem() { return statusViagem; }
    public BigDecimal getValor() { return valor; }
    public PacoteViagem getPacote() { return pacote; }
}