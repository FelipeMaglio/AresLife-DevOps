package com.fiap.areslife.controller;

import com.fiap.areslife.dto.request.SaudeRequest;
import com.fiap.areslife.dto.response.SaudeResponse;
import com.fiap.areslife.entity.SaudeHabitante;
import com.fiap.areslife.service.SaudeService;
import com.fiap.areslife.service.mapper.SaudeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitantes/{habitanteId}/saude")
@RequiredArgsConstructor
@Tag(name = "Saúde", description = "Monitoramento de saúde dos habitantes")
public class SaudeController {

    private final SaudeService saudeService;


    @Operation(summary = "Registrar sinais vitais do habitante")
    @PostMapping
    public ResponseEntity<SaudeResponse> criar(
            @PathVariable Long habitanteId,
            @Valid @RequestBody SaudeRequest request) {

        return ResponseEntity.status(201).body(
                SaudeMapper.toResponse(
                        saudeService.criar(habitanteId, request)
                )
        );
    }

    @GetMapping("/historico")
    @Operation(summary = "Histórico de saúde paginado")
    public ResponseEntity<Page<SaudeHabitante>> historico(
            @PathVariable Long habitanteId,
            @PageableDefault(size = 10, sort = "dataRegistro") Pageable pageable) {
        return ResponseEntity.ok(saudeService.historico(habitanteId, pageable));
    }


    @Operation(summary = "Último registro de saúde")
    @GetMapping("/ultimo")
    public ResponseEntity<SaudeResponse> ultimoRegistro(
            @PathVariable Long habitanteId) {

        return ResponseEntity.ok(
                SaudeMapper.toResponse(
                        saudeService.ultimoRegistro(habitanteId)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<SaudeResponse>> listar(
            @PathVariable Long habitanteId) {

        return ResponseEntity.ok(
                saudeService.listar(habitanteId)
                        .stream()
                        .map(SaudeMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaudeResponse> buscarPorId(
            @PathVariable Long habitanteId,
            @PathVariable Long id) {

        return ResponseEntity.ok(
                SaudeMapper.toResponse(
                        saudeService.buscarPorId(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaudeResponse> atualizar(
            @PathVariable Long habitanteId,
            @PathVariable Long id,
            @Valid @RequestBody SaudeRequest request) {

        return ResponseEntity.ok(
                SaudeMapper.toResponse(
                        saudeService.atualizar(id, request)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long habitanteId,
            @PathVariable Long id) {

        saudeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
