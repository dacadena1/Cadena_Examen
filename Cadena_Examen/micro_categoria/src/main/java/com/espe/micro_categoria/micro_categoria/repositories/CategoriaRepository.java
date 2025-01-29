package com.espe.micro_categoria.micro_categoria.repositories;

import com.espe.micro_categoria.micro_categoria.models.entities.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
}
