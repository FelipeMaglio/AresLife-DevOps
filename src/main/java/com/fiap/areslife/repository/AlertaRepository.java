package com.fiap.areslife.repository;

import com.fiap.areslife.entity.Alerta;
import com.fiap.areslife.enums.SeveridadeAlerta;
import com.fiap.areslife.enums.StatusAlerta;
import com.fiap.areslife.enums.TipoAlerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByColoniaId(Long coloniaId);
    List<Alerta> findByColoniaIdAndStatus(Long coloniaId, StatusAlerta status);
    List<Alerta> findByColoniaIdAndTipoAlertaAndStatusNot(Long coloniaId, TipoAlerta tipo, StatusAlerta status);
    List<Alerta> findBySeveridadeAndStatus(SeveridadeAlerta severidade, StatusAlerta status);
}
