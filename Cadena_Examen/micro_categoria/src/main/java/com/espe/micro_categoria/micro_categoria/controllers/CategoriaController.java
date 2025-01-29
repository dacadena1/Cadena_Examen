package com.espe.micro_categoria.micro_categoria.controllers;

import com.espe.micro_categoria.micro_categoria.models.entities.Categoria;
import com.espe.micro_categoria.micro_categoria.models.entities.CompetenciaCategoria;
import com.espe.micro_categoria.micro_categoria.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(categoria));
    }

    // Listar todas las categorías
    @GetMapping
    public List<Categoria> listar() {
        return service.findAll();
    }

    // Buscar una categoría por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = service.findById(id);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok(categoriaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Modificar una categoría por su ID
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@RequestBody Categoria categoria, @PathVariable Long id) {
        Optional<Categoria> categoriaOptional = service.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoriaDB = categoriaOptional.get();
            categoriaDB.setNombre(categoria.getNombre());
            categoriaDB.setDescripcion(categoria.getDescripcion());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(categoriaDB));
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar una categoría por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = service.findById(id);
        if (categoriaOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 1️⃣ Asociar una categoría a una competencia
    @PostMapping("/{categoriaId}/competencias/{competenciaId}")
    public ResponseEntity<CompetenciaCategoria> asociarCategoriaACompetencia(
            @PathVariable Long competenciaId, @PathVariable Long categoriaId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.asociarCategoriaACompetencia(competenciaId, categoriaId));
    }

    // 2️⃣ Listar categorías de una competencia
    @GetMapping("/competencias/{competenciaId}/categorias")
    public ResponseEntity<List<CompetenciaCategoria>> listarCategoriasDeCompetencia(@PathVariable Long competenciaId) {
        return ResponseEntity.ok(service.listarCategoriasDeCompetencia(competenciaId));
    }

    // 3️⃣ Listar competencias de una categoría
    @GetMapping("/{categoriaId}/competencias")
    public ResponseEntity<List<CompetenciaCategoria>> listarCompetenciasDeCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(service.listarCompetenciasDeCategoria(categoriaId));
    }

    // 4️⃣ Remover una categoría de una competencia
    @DeleteMapping("/{categoriaId}/competencias/{competenciaId}")
    public ResponseEntity<Void> removerCategoriaDeCompetencia(@PathVariable Long competenciaId, @PathVariable Long categoriaId) {
        service.removerCategoriaDeCompetencia(competenciaId, categoriaId);
        return ResponseEntity.noContent().build();
    }
}
