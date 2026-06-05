package com.fiap.areslife.repository;

import com.fiap.areslife.entity.AvaliacaoTreinamento;
import com.fiap.areslife.entity.AvaliacaoTreinamentoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoTreinamentoRepository
        extends JpaRepository<
                AvaliacaoTreinamento,
                AvaliacaoTreinamentoId> {
}
