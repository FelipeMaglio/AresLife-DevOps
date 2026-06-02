package com.fiap.areslife.repository;

import com.fiap.areslife.entity.Habitante;
import com.fiap.areslife.enums.StatusHabitante;
import com.fiap.areslife.enums.TipoHabitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitanteRepository extends JpaRepository<Habitante, Long> {
    List<Habitante> findByColoniaId(Long coloniaId);
    List<Habitante> findByColoniaIdAndTipo(Long coloniaId, TipoHabitante tipo);
    long countByColoniaIdAndStatus(Long coloniaId, StatusHabitante status);
    List<Habitante> findByColoniaIdAndStatus(Long coloniaId, StatusHabitante status);
}
