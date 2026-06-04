package com.fiap.areslife.repository;

import com.fiap.areslife.entity.LogSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogSistemaRepository extends JpaRepository<LogSistema, Long> {
    List<LogSistema> findByTabelaAfetada(String tabelaAfetada);
    List<LogSistema> findByDataOperacaoBetween(LocalDateTime inicio, LocalDateTime fim);
    List<LogSistema> findByOperacaoContainingIgnoreCase(String operacao);
}
