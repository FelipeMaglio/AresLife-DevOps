package com.fiap.areslife.service;

import com.fiap.areslife.dto.request.AlertaResolverRequest;
import com.fiap.areslife.entity.Alerta;
import com.fiap.areslife.enums.SeveridadeAlerta;
import com.fiap.areslife.enums.StatusAlerta;
import com.fiap.areslife.enums.TipoAlerta;
import com.fiap.areslife.exception.ResourceNotFoundException;
import com.fiap.areslife.repository.AlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;

    public List<Alerta> listar(Long coloniaId, SeveridadeAlerta severidade, StatusAlerta status) {
        if (coloniaId != null && status != null) {
            return alertaRepository.findByColoniaIdAndStatus(coloniaId, status);
        } else if (coloniaId != null) {
            return alertaRepository.findByColoniaId(coloniaId);
        } else if (severidade != null && status != null) {
            return alertaRepository.findBySeveridadeAndStatus(severidade, status);
        }
        return alertaRepository.findAll();
    }

    public Alerta buscarPorId(Long id) {
        return alertaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alerta não encontrado com id: " + id));
    }

    @Transactional
    public Alerta resolver(Long id, AlertaResolverRequest request) {
        Alerta alerta = buscarPorId(id);
        alerta.setStatus(StatusAlerta.RESOLVIDO);
        alerta.setResolvidoEm(LocalDateTime.now());
        alerta.setDescricao(alerta.getDescricao() + " | Resolução: " + request.observacao());
        return alertaRepository.save(alerta);
    }

    @Transactional
    public int resolverLotePorColonia(Long coloniaId, TipoAlerta tipoAlerta) {
        List<Alerta> alertas = alertaRepository.findByColoniaIdAndTipoAlertaAndStatusNot(
                coloniaId, tipoAlerta, StatusAlerta.RESOLVIDO);

        alertas.forEach(a -> {
            a.setStatus(StatusAlerta.RESOLVIDO);
            a.setResolvidoEm(LocalDateTime.now());
        });

        alertaRepository.saveAll(alertas);
        return alertas.size();
    }
}
