package com.fiap.areslife.controller;

import com.fiap.areslife.entity.LogSistema;
import com.fiap.areslife.service.LogSistemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
@Tag(name = "Logs do Sistema", description = "Auditoria e rastreabilidade de operações")
public class LogSistemaController {

    private final LogSistemaService logService;

    @GetMapping
    @Operation(summary = "Listar todos os logs do sistema")
    public ResponseEntity<List<LogSistema>> listarTodos() {
        return ResponseEntity.ok(logService.listarTodos());
    }

    @GetMapping("/tabela/{tabela}")
    @Operation(summary = "Buscar logs por tabela afetada")
    public ResponseEntity<List<LogSistema>> porTabela(@PathVariable String tabela) {
        return ResponseEntity.ok(logService.buscarPorTabela(tabela));
    }

    @GetMapping("/periodo")
    @Operation(summary = "Buscar logs por período")
    public ResponseEntity<List<LogSistema>> porPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(logService.buscarPorPeriodo(inicio, fim));
    }
}
