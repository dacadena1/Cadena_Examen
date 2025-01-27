package com.espe_competencias.demo.repositories;

import com.espe_competencias.demo.models.entities.Competencias;
import org.springframework.data.repository.CrudRepository;

public interface CompetenciaRepository extends CrudRepository<Competencias, Long> {
}
