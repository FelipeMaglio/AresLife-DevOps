package com.fiap.areslife.repository;

import com.fiap.areslife.entity.Colonia;
import com.fiap.areslife.enums.StatusColonia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColoniaRepository extends JpaRepository<Colonia, Long> {
    List<Colonia> findByStatus(StatusColonia status);
    boolean existsByNome(String nome);
}
