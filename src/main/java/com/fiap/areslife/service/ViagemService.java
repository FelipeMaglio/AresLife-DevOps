package com.fiap.areslife.service;

import com.fiap.areslife.dto.request.ViagemRequest;
import com.fiap.areslife.entity.Colonia;
import com.fiap.areslife.entity.Habitante;
import com.fiap.areslife.entity.Turista;
import com.fiap.areslife.entity.ViagemTuristica;
import com.fiap.areslife.enums.*;
import com.fiap.areslife.exception.BusinessException;
import com.fiap.areslife.exception.ResourceNotFoundException;
import com.fiap.areslife.repository.HabitanteRepository;
import com.fiap.areslife.repository.TreinamentoRepository;
import com.fiap.areslife.repository.ViagemTuristicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViagemService {

    private final ViagemTuristicaRepository viagemRepository;
    private final HabitanteRepository habitanteRepository;
    private final ColoniaService coloniaService;
    private final TreinamentoRepository treinamentoRepository;

    public List<ViagemTuristica> listar(Long habitanteId, StatusViagem status) {
        if (habitanteId != null && status != null) {
            return viagemRepository.findByHabitanteIdAndStatusViagem(habitanteId, status);
        } else if (habitanteId != null) {
            return viagemRepository.findByHabitanteId(habitanteId);
        } else if (status != null) {
            return viagemRepository.findByStatusViagem(status);
        }
        return viagemRepository.findAll();
    }

    public ViagemTuristica buscarPorId(Long id) {
        return viagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viagem não encontrada com id: " + id));
    }

    @Transactional
    public ViagemTuristica reservar(ViagemRequest request) {
        Habitante habitante = habitanteRepository.findById(request.habitanteId())
                .orElseThrow(() -> new ResourceNotFoundException("Habitante não encontrado com id: " + request.habitanteId()));

        // Regra: somente TURISTA pode viajar
        if (!(habitante instanceof Turista)) {
            throw new BusinessException("Somente turistas podem reservar viagens turísticas.");
        }

        // Regra: turista deve ter pelo menos 1 treinamento CONCLUIDO
        boolean temTreinamento = treinamentoRepository.existsByHabitanteIdAndStatus(
                request.habitanteId(), StatusTreinamento.CONCLUIDO);
        if (!temTreinamento) {
            throw new BusinessException("O turista precisa ter pelo menos 1 treinamento concluído antes de viajar.");
        }

        if (request.dataRetorno().isBefore(request.dataPartida())) {
            throw new BusinessException("Data de retorno não pode ser anterior à data de partida.");
        }

        Colonia colonia = coloniaService.findOrThrow(request.coloniaId());
        BigDecimal valor = calcularValor(colonia.getLocalizacao(), request.pacote(),
                request.dataPartida(), request.dataRetorno());

        ViagemTuristica viagem = ViagemTuristica.builder()
                .habitante(habitante)
                .colonia(colonia)
                .dataPartida(request.dataPartida())
                .dataRetorno(request.dataRetorno())
                .statusViagem(StatusViagem.RESERVADA)
                .valor(valor)
                .pacote(request.pacote())
                .build();

        return viagemRepository.save(viagem);
    }

    @Transactional
    public ViagemTuristica iniciar(Long id) {
        ViagemTuristica viagem = buscarPorId(id);
        if (viagem.getStatusViagem() != StatusViagem.RESERVADA) {
            throw new BusinessException("Somente viagens com status RESERVADA podem ser iniciadas.");
        }
        viagem.setStatusViagem(StatusViagem.EM_ANDAMENTO);
        return viagemRepository.save(viagem);
    }

    @Transactional
    public ViagemTuristica concluir(Long id) {
        ViagemTuristica viagem = buscarPorId(id);
        if (viagem.getStatusViagem() != StatusViagem.EM_ANDAMENTO) {
            throw new BusinessException("Somente viagens EM_ANDAMENTO podem ser concluídas.");
        }
        viagem.setStatusViagem(StatusViagem.CONCLUIDA);

        // Atualizar data de saída do habitante
        Habitante habitante = viagem.getHabitante();
        habitante.setDataSaida(LocalDate.now());
        habitanteRepository.save(habitante);

        return viagemRepository.save(viagem);
    }

    @Transactional
    public ViagemTuristica cancelar(Long id) {
        ViagemTuristica viagem = buscarPorId(id);
        if (viagem.getStatusViagem() != StatusViagem.RESERVADA) {
            throw new BusinessException("Somente viagens RESERVADAS podem ser canceladas.");
        }
        viagem.setStatusViagem(StatusViagem.CANCELADA);
        return viagemRepository.save(viagem);
    }

    // ---- helpers ----

    private BigDecimal calcularValor(Localizacao localizacao, PacoteViagem pacote,
                                     LocalDate partida, LocalDate retorno) {
        BigDecimal base = localizacao == Localizacao.MARTE
                ? new BigDecimal("2000000")
                : new BigDecimal("500000");

        BigDecimal multiplicador = switch (pacote) {
            case BASICO -> BigDecimal.ONE;
            case PREMIUM -> new BigDecimal("1.5");
            case VIP -> new BigDecimal("2.5");
        };

        long dias = ChronoUnit.DAYS.between(partida, retorno);
        if (dias <= 0) dias = 1;

        return base.multiply(multiplicador).multiply(BigDecimal.valueOf(dias));
    }
}
