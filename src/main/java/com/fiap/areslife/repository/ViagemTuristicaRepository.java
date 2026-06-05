package com.fiap.areslife.repository;

import com.fiap.areslife.entity.ViagemTuristica;
import com.fiap.areslife.enums.StatusViagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViagemTuristicaRepository extends JpaRepository<ViagemTuristica, Long> {
    List<ViagemTuristica> findByHabitanteId(Long habitanteId);
    List<ViagemTuristica> findByStatusViagem(StatusViagem status);
    List<ViagemTuristica> findByHabitanteIdAndStatusViagem(Long habitanteId, StatusViagem status);
}
