package com.fiap.areslife.controller;

import com.fiap.areslife.dto.request.TuristaEspacialRequest;
import com.fiap.areslife.dto.response.TuristaEspacialResponse;
import com.fiap.areslife.entity.TuristaEspacial;
import com.fiap.areslife.enums.Localizacao;
import com.fiap.areslife.enums.StatusTurista;
import com.fiap.areslife.service.TuristaEspacialService;
import com.fiap.areslife.service.mapper.HabitanteMapper;
import com.fiap.areslife.service.mapper.TuristaEspacialMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turistas")
@RequiredArgsConstructor
@Tag(name = "Turistas Espaciais", description = "Gerenciamento de turistas espaciais")
    public class TuristaEspacialController {

    private final TuristaEspacialService passageiroService;


    @Operation(summary = "Listar passageiros (filtros: destino, status)")
    @GetMapping
    public ResponseEntity<List<TuristaEspacialResponse>> listar(
            @RequestParam(required = false) Localizacao destino,
            @RequestParam(required = false) StatusTurista status) {

        return ResponseEntity.ok(
                passageiroService.listar(destino, status)
                        .stream()
                        .map(TuristaEspacialMapper::toResponse)
                        .toList()
        );
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar passageiro por ID")
    public ResponseEntity<TuristaEspacialResponse> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(
                TuristaEspacialMapper.toResponse(
                        passageiroService.buscarPorId(id)
                )
        );
    }


    @Operation(summary = "Registrar novo passageiro")
    @PostMapping
    public ResponseEntity<TuristaEspacialResponse> registrar(
            @Valid @RequestBody TuristaEspacialRequest request) {

        return ResponseEntity.status(201).body(
                TuristaEspacialMapper.toResponse(
                        passageiroService.registrar(request)
                )
        );
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualizar status do passageiro")
    public ResponseEntity<TuristaEspacialResponse> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusTurista novoStatus) {
        return ResponseEntity.ok(TuristaEspacialMapper.toResponse(passageiroService.atualizarStatus(id,novoStatus)));
    }

    @PatchMapping("/{id}/vincular")
    @Operation(summary = "Vincular passageiro a um habitante da colônia")
    public ResponseEntity<TuristaEspacialResponse> vincular(
            @PathVariable Long id,
            @RequestParam Long habitanteId) {
        return ResponseEntity.ok(TuristaEspacialMapper.toResponse(passageiroService.vincularHabitante(id, habitanteId)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar turista espacial")
    public ResponseEntity<TuristaEspacialResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TuristaEspacialRequest request) {

        return ResponseEntity.ok(
                TuristaEspacialMapper.toResponse(
                        passageiroService.atualizar(id, request)
                )
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover passageiro")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        passageiroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
