package com.espe.micro_categoria.micro_categoria.services;

import com.espe.micro_categoria.micro_categoria.models.entities.CompetenciaCategoria;
import java.util.List;
import java.util.Optional;

public interface CompetenciaCategoriaService {

    CompetenciaCategoria asociarCategoriaACompetencia(Long competenciaId, Long categoriaId);

    List<CompetenciaCategoria> listarCategoriasDeCompetencia(Long competenciaId);

    List<CompetenciaCategoria> listarCompetenciasDeCategoria(Long categoriaId);

    void removerCategoriaDeCompetencia(Long competenciaId, Long categoriaId);
}
