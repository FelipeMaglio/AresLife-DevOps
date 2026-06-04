package com.fiap.areslife.controller;

import com.fiap.areslife.dto.request.TreinamentoRequest;
import com.fiap.areslife.entity.Treinamento;
import com.fiap.areslife.enums.StatusTreinamento;
import com.fiap.areslife.service.TreinamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fiap.areslife.dto.response.TreinamentoResponse;
import com.fiap.areslife.service.mapper.TreinamentoMapper;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/treinamentos")
@RequiredArgsConstructor
@Tag(name = "Treinamentos", description = "Gerenciamento de treinamentos dos habitantes")
public class TreinamentoController {

    private final TreinamentoService treinamentoService;

    @GetMapping
    @Operation(summary = "Listar treinamentos (filtros: habitanteId, status)")
    public ResponseEntity<List<TreinamentoResponse>> listar(
            @RequestParam(required = false) Long habitanteId,
            @RequestParam(required = false) StatusTreinamento status) {

        return ResponseEntity.ok(
                treinamentoService.listar(habitanteId, status)
                        .stream()
                        .map(TreinamentoMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar treinamento por ID")
    public ResponseEntity<TreinamentoResponse> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                TreinamentoMapper.toResponse(
                        treinamentoService.buscarPorId(id)
                )
        );
    }

    @PostMapping
    @Operation(summary = "Criar novo treinamento")
    public ResponseEntity<TreinamentoResponse> criar(
            @Valid @RequestBody TreinamentoRequest request) {

        return ResponseEntity.status(201).body(
                TreinamentoMapper.toResponse(
                        treinamentoService.criar(request)
                )
        );
    }

    @PatchMapping("/{id}/iniciar")
    @Operation(summary = "Iniciar treinamento")
    public ResponseEntity<TreinamentoResponse> iniciar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                TreinamentoMapper.toResponse(
                        treinamentoService.iniciar(id)
                )
        );
    }

    @PatchMapping("/{id}/concluir")
    @Operation(summary = "Concluir treinamento com nota final")
    public ResponseEntity<TreinamentoResponse> concluir(
            @PathVariable Long id,
            @RequestParam BigDecimal notaFinal) {

        return ResponseEntity.ok(
                TreinamentoMapper.toResponse(
                        treinamentoService.concluir(id, notaFinal)
                )
        );
    }
    @GetMapping("/pendentes/colonia/{coloniaId}")
    @Operation(summary = "Listar treinamentos pendentes de uma colônia")
    public ResponseEntity<List<TreinamentoResponse>> pendentesPorColonia(
            @PathVariable Long coloniaId) {

        return ResponseEntity.ok(
                treinamentoService.listarPendentesPorColonia(coloniaId)
                        .stream()
                        .map(TreinamentoMapper::toResponse)
                        .toList()
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover treinamento")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        treinamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
