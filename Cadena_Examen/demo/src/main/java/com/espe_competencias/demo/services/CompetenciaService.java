package com.espe_competencias.demo.services;

import com.espe_competencias.demo.models.entities.Competencias;

import java.util.List;
import java.util.Optional;

public interface CompetenciaService {

    List<Competencias> findAll();

    Optional<Competencias> findById(Long id);

    Competencias save(Competencias competencia);

    void deleteById(Long id);
}
