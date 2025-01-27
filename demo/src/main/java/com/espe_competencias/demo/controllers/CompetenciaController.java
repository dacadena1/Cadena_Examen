package com.espe_competencias.demo.controllers;

import com.espe_competencias.demo.models.entities.Competencias;
import com.espe_competencias.demo.services.CompetenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/competencias")
public class CompetenciaController {

    @Autowired
    private CompetenciaService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Competencias competencia, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(competencia));
    }

    @GetMapping
    public List<Competencias> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competencias> buscarPorId(@PathVariable Long id) {
        Optional<Competencias> competenciaOptional = service.findById(id);
        if (competenciaOptional.isPresent()) {
            return ResponseEntity.ok(competenciaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Competencias competencia, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validarErrores(result);
        }

        Optional<Competencias> competenciaOptional = service.findById(id);
        if (competenciaOptional.isPresent()) {
            Competencias competenciaDB = competenciaOptional.get();
            competenciaDB.setNombre(competencia.getNombre());
            competenciaDB.setDescripcion(competencia.getDescripcion());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(competenciaDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Competencias> competenciaOptional = service.findById(id);
        if (competenciaOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validarErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(
                error -> errores.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errores);
    }
}
