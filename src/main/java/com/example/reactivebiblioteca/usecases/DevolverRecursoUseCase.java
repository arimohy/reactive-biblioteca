package com.example.reactivebiblioteca.usecases;

import com.example.reactivebiblioteca.repositories.Repositorio;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

public class DevolverRecursoUseCase implements Function<String, Mono<String>> {
    private final Repositorio repositorio;
    private final ActualizarRecursoUseCase actualizarRecursoUseCase;
    private final MapperUtils mapperUtils;

    public DevolverRecursoUseCase(ActualizarRecursoUseCase actualizarRecursoUseCase, MapperUtils mapperUtils,Repositorio resourceRepository) {
        this.repositorio = resourceRepository;
        this.actualizarRecursoUseCase = new ActualizarRecursoUseCase(mapperUtils, repositorio);
        this.mapperUtils = mapperUtils;
    }
    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "el id no puede ser nullo");
        return repositorio.findById(id).flatMap(
                recurso -> {
                    if (recurso.getNroEjemplaresPrestados() >= 1) {
                        recurso.setNroEjemplaresPrestados(recurso.getNroEjemplaresPrestados() - 1);
                        return actualizarRecursoUseCase.apply(mapperUtils.mapRecursoToDTO().apply(recurso))
                                .thenReturn("{ Mensaje : El recurso fue devuelto satisfactoriamente }");
                    }
                    return Mono.just("Mensaje : Recurso no puede ser devuelto por que no fue prestado");
                }
        );
    }
}
