package com.espe.micro_categoria.micro_categoria.repositories;

import com.espe.micro_categoria.micro_categoria.models.entities.CompetenciaCategoria;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetenciaCategoriaRepository extends JpaRepository<CompetenciaCategoria, Long> {

    // Buscar todas las categorías asociadas a una competencia
    List<CompetenciaCategoria> findByCompetenciaId(Long competenciaId);

    // Buscar todas las competencias asociadas a una categoría
    List<CompetenciaCategoria> findByCategoriaId(Long categoriaId);

    // Eliminar una relación específica entre una competencia y una categoría
    @Transactional
    void deleteByCompetenciaIdAndCategoriaId(Long competenciaId, Long categoriaId);
}
