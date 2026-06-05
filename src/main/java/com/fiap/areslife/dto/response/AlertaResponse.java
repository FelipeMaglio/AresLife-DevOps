package com.fiap.areslife.dto.response;

import com.fiap.areslife.enums.SeveridadeAlerta;
import com.fiap.areslife.enums.StatusAlerta;
import com.fiap.areslife.enums.TipoAlerta;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

public class AlertaResponse extends RepresentationModel<AlertaResponse> {

    private Long id;
    private Long coloniaId;
    private TipoAlerta tipoAlerta;
    private String descricao;
    private SeveridadeAlerta severidade;
    private StatusAlerta status;
    private LocalDateTime dataEmissao;
    private LocalDateTime resolvidoEm;

    public AlertaResponse() {
    }

    public AlertaResponse(
            Long id,
            Long coloniaId,
            TipoAlerta tipoAlerta,
            String descricao,
            SeveridadeAlerta severidade,
            StatusAlerta status,
            LocalDateTime dataEmissao,
            LocalDateTime resolvidoEm) {

        this.id = id;
        this.coloniaId = coloniaId;
        this.tipoAlerta = tipoAlerta;
        this.descricao = descricao;
        this.severidade = severidade;
        this.status = status;
        this.dataEmissao = dataEmissao;
        this.resolvidoEm = resolvidoEm;
    }

    public Long getId() { return id; }
    public Long getColoniaId() { return coloniaId; }
    public TipoAlerta getTipoAlerta() { return tipoAlerta; }
    public String getDescricao() { return descricao; }
    public SeveridadeAlerta getSeveridade() { return severidade; }
    public StatusAlerta getStatus() { return status; }
    public LocalDateTime getDataEmissao() { return dataEmissao; }
    public LocalDateTime getResolvidoEm() { return resolvidoEm; }
}