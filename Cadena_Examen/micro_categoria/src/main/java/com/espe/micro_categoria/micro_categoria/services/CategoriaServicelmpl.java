package com.espe.micro_categoria.micro_categoria.services;

import com.espe.micro_categoria.micro_categoria.models.entities.Categoria;
import com.espe.micro_categoria.micro_categoria.models.entities.CompetenciaCategoria;
import com.espe.micro_categoria.micro_categoria.repositories.CategoriaRepository;
import com.espe.micro_categoria.micro_categoria.repositories.CompetenciaCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicelmpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CompetenciaCategoriaRepository competenciaCategoriaRepository;

    // Métodos CRUD para Categoria
    @Override
    public List<Categoria> findAll() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    // Métodos para gestionar la relación con Competencia
    @Override
    public CompetenciaCategoria asociarCategoriaACompetencia(Long competenciaId, Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        CompetenciaCategoria competenciaCategoria = new CompetenciaCategoria();
        competenciaCategoria.setCompetenciaId(competenciaId);
        competenciaCategoria.setCategoria(categoria);

        return competenciaCategoriaRepository.save(competenciaCategoria);
    }

    @Override
    public List<CompetenciaCategoria> listarCategoriasDeCompetencia(Long competenciaId) {
        return competenciaCategoriaRepository.findByCompetenciaId(competenciaId);
    }

    @Override
    public List<CompetenciaCategoria> listarCompetenciasDeCategoria(Long categoriaId) {
        return competenciaCategoriaRepository.findByCategoriaId(categoriaId);
    }

    @Override
    public void removerCategoriaDeCompetencia(Long competenciaId, Long categoriaId) {
        competenciaCategoriaRepository.deleteByCompetenciaIdAndCategoriaId(competenciaId, categoriaId);
    }
}
