package com.espe_competencias.demo.services;

import com.espe_competencias.demo.models.entities.Competencias;
import com.espe_competencias.demo.repositories.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenciaServicelmpl implements CompetenciaService {

    @Autowired
    private CompetenciaRepository repository;

    @Override
    public List<Competencias> findAll() {
        return (List<Competencias>) repository.findAll();
    }

    @Override
    public Optional<Competencias> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Competencias save(Competencias competencia) {
        return repository.save(competencia);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
