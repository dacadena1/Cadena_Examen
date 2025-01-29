package com.espe.micro_categoria.micro_categoria.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date creadoEn;

    @PrePersist
    protected void onCreate() {
        this.creadoEn = new Date();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }
}
