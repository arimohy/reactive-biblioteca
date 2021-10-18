package com.example.reactivebiblioteca.model;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class RecursoDTO {
    private String id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String tipoRecurso;
    @NotBlank
    private String tematica;
    private String disponible;
    private LocalDate fechaprestamo;
    @NotBlank
    private Long nroEjemplares;
    private Long nroEjemplaresPrestados;

    public RecursoDTO() {
    }

    public RecursoDTO(String titulo, String tipoRecurso, String tematica, Long nroEjemplares) {
        this.titulo = titulo;
        this.tipoRecurso = tipoRecurso;
        this.tematica = tematica;
        this.nroEjemplares = nroEjemplares;
    }

    public RecursoDTO(String id, String titulo, String tipoRecurso, String tematica, String disponible, LocalDate fechaprestamo, Long nroEjemplares, Long nroEjemplaresPrestados) {
        this.id = id;
        this.titulo = titulo;
        this.tipoRecurso = tipoRecurso;
        this.tematica = tematica;
        this.disponible = disponible;
        this.fechaprestamo = fechaprestamo;
        this.nroEjemplares = nroEjemplares;
        this.nroEjemplaresPrestados = nroEjemplaresPrestados;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public LocalDate getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(LocalDate fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public Long getNroEjemplares() {
        return nroEjemplares;
    }

    public void setNroEjemplares(Long nroEjemplares) {
        this.nroEjemplares = nroEjemplares;
    }

    public Long getNroEjemplaresPrestados() {
        return nroEjemplaresPrestados;
    }

    public void setNroEjemplaresPrestados(Long nroEjemplaresPrestados) {
        this.nroEjemplaresPrestados = nroEjemplaresPrestados;
    }
}
