package com.fiap.areslife.service;

import com.fiap.areslife.dto.request.ColoniaRequest;
import com.fiap.areslife.dto.response.ColoniaResponse;
import com.fiap.areslife.entity.Alerta;
import com.fiap.areslife.entity.Colonia;
import com.fiap.areslife.enums.*;
import com.fiap.areslife.exception.BusinessException;
import com.fiap.areslife.exception.ResourceNotFoundException;
import com.fiap.areslife.repository.AlertaRepository;
import com.fiap.areslife.repository.ColoniaRepository;
import com.fiap.areslife.repository.HabitanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColoniaService {

    private final ColoniaRepository coloniaRepository;
    private final HabitanteRepository habitanteRepository;
    private final AlertaRepository alertaRepository;

    public List<ColoniaResponse> listarTodas() {
        return coloniaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ColoniaResponse buscarPorId(Long id) {
        Colonia colonia = findOrThrow(id);
        return toResponse(colonia);
    }

    @Transactional
    public ColoniaResponse criar(ColoniaRequest request) {
        Colonia colonia = Colonia.builder()
                .nome(request.nome())
                .localizacao(request.localizacao())
                .capacidadeMax(request.capacidadeMax())
                .status(StatusColonia.ATIVA)
                .dataFundacao(request.dataFundacao())
                .descricao(request.descricao())
                .build();
        return toResponse(coloniaRepository.save(colonia));
    }

    @Transactional
    public ColoniaResponse atualizar(Long id, ColoniaRequest request) {
        Colonia colonia = findOrThrow(id);

        boolean mudouParaEmergencia =
                request.localizacao() != null
                && colonia.getStatus() != StatusColonia.EMERGENCIA;

        colonia.setNome(request.nome());
        colonia.setLocalizacao(request.localizacao());
        colonia.setCapacidadeMax(request.capacidadeMax());
        colonia.setDataFundacao(request.dataFundacao());
        colonia.setDescricao(request.descricao());

        Colonia saved = coloniaRepository.save(colonia);

        // Regra: ao mudar para EMERGENCIA, criar alerta automático
        if (mudouParaEmergencia && saved.getStatus() == StatusColonia.EMERGENCIA) {
            criarAlertaEmergencia(saved);
        }

        return toResponse(saved);
    }

    @Transactional
    public void deletar(Long id) {
        Colonia colonia = findOrThrow(id);
        long habitantesAtivos = habitanteRepository.countByColoniaIdAndStatus(id, StatusHabitante.ATIVO);
        if (habitantesAtivos > 0) {
            throw new BusinessException(
                    "Não é possível deletar a colônia pois existem " + habitantesAtivos + " habitante(s) ativo(s)."
            );
        }
        coloniaRepository.delete(colonia);
    }

    // Método interno usado por outros services
    public Colonia findOrThrow(Long id) {
        return coloniaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colônia não encontrada com id: " + id));
    }

    private void criarAlertaEmergencia(Colonia colonia) {
        Alerta alerta = Alerta.builder()
                .colonia(colonia)
                .tipoAlerta(TipoAlerta.EMERGENCIA)
                .descricao("Colônia " + colonia.getNome() + " entrou em estado de EMERGÊNCIA.")
                .severidade(SeveridadeAlerta.CRITICA)
                .dataEmissao(LocalDateTime.now())
                .status(StatusAlerta.ABERTO)
                .build();
        alertaRepository.save(alerta);
    }

    private ColoniaResponse toResponse(Colonia colonia) {
        long total = habitanteRepository.countByColoniaIdAndStatus(colonia.getId(), StatusHabitante.ATIVO);
        return new ColoniaResponse(
                colonia.getId(),
                colonia.getNome(),
                colonia.getLocalizacao(),
                colonia.getCapacidadeMax(),
                colonia.getStatus(),
                colonia.getDataFundacao(),
                colonia.getDescricao(),
                total
        );
    }
}
