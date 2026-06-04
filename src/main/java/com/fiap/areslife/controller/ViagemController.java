package com.fiap.areslife.controller;

import com.fiap.areslife.dto.request.ViagemRequest;
import com.fiap.areslife.dto.response.ViagemResponse;
import com.fiap.areslife.entity.ViagemTuristica;
import com.fiap.areslife.enums.StatusViagem;
import com.fiap.areslife.service.ViagemService;
import com.fiap.areslife.service.mapper.ViagemMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viagens")
@RequiredArgsConstructor
@Tag(name = "Viagens Turísticas", description = "Gerenciamento de viagens turísticas espaciais")
public class ViagemController {

    private final ViagemService viagemService;

    @GetMapping
    @Operation(summary = "Listar viagens (filtros: habitanteId, status)")
    public ResponseEntity<List<ViagemResponse>> listar(
            @RequestParam(required = false) Long habitanteId,
            @RequestParam(required = false) StatusViagem status) {

        return ResponseEntity.ok(
                viagemService.listar(habitanteId, status)
                        .stream()
                        .map(ViagemMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar viagem por ID")
    public ResponseEntity<ViagemResponse> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ViagemMapper.toResponse(
                        viagemService.buscarPorId(id)
                )
        );
    }

    @PostMapping
    @Operation(summary = "Reservar nova viagem turística")
    public ResponseEntity<ViagemResponse> reservar(
            @Valid @RequestBody ViagemRequest request) {

        return ResponseEntity.status(201).body(
                ViagemMapper.toResponse(
                        viagemService.reservar(request)
                )
        );
    }

    @PatchMapping("/{id}/iniciar")
    @Operation(summary = "Iniciar viagem")
    public ResponseEntity<ViagemResponse> iniciar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ViagemMapper.toResponse(
                        viagemService.iniciar(id)
                )
        );
    }

    @PatchMapping("/{id}/concluir")
    @Operation(summary = "Concluir viagem")
    public ResponseEntity<ViagemResponse> concluir(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ViagemMapper.toResponse(
                        viagemService.concluir(id)
                )
        );
    }


    @PatchMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar viagem")
    public ResponseEntity<ViagemResponse> cancelar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ViagemMapper.toResponse(
                        viagemService.cancelar(id)
                )
        );
    }
}
