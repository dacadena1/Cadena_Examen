package com.espe.micro_categoria.micro_categoria.services;

import com.espe.micro_categoria.micro_categoria.models.entities.Categoria;
import com.espe.micro_categoria.micro_categoria.models.entities.CompetenciaCategoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> findAll();

    Optional<Categoria> findById(Long id);

    Categoria save(Categoria categoria);

    void deleteById(Long id);

    // Métodos para gestionar la relación con Competencia
    CompetenciaCategoria asociarCategoriaACompetencia(Long competenciaId, Long categoriaId);

    List<CompetenciaCategoria> listarCategoriasDeCompetencia(Long competenciaId);

    List<CompetenciaCategoria> listarCompetenciasDeCategoria(Long categoriaId);

    void removerCategoriaDeCompetencia(Long competenciaId, Long categoriaId);
}
