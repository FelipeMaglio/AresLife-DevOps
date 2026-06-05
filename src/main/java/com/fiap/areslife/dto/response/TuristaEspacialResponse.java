package com.fiap.areslife.dto.response;

import com.fiap.areslife.enums.Localizacao;
import com.fiap.areslife.enums.StatusTurista;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class TuristaEspacialResponse extends RepresentationModel<TuristaEspacialResponse> {
    private Long id;
    private String nome;
    private Integer idade;
    private String pais;
    private Localizacao destino;
    private StatusTurista status;

    public TuristaEspacialResponse() {}

    public TuristaEspacialResponse(
            Long id,
            String nome,
            Integer idade,
            String pais,
            Localizacao destino,
            StatusTurista status) {

        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.pais = pais;
        this.destino = destino;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Integer getIdade() { return idade; }
    public String getPais() { return pais; }
    public Localizacao getDestino() { return destino; }
    public StatusTurista getStatus() { return status; }}
