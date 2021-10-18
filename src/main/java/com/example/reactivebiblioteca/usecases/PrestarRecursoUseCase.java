package com.example.reactivebiblioteca.usecases;

import com.example.reactivebiblioteca.repositories.Repositorio;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class PrestarRecursoUseCase implements Function<String, Mono<String>> {
    private final Repositorio repositorio;
    private final ActualizarRecursoUseCase actualizarRecursoUseCase;
    private final MapperUtils mapperUtils;

    public PrestarRecursoUseCase(MapperUtils mapperUtils,Repositorio resourceRepository) {
        this.repositorio = resourceRepository;
        this.actualizarRecursoUseCase = new ActualizarRecursoUseCase(mapperUtils, repositorio);
        this.mapperUtils = mapperUtils;
    }
    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "el id es requerido");
        return repositorio.findById(id).flatMap(
                recurso -> {
                    if (recurso.getNroEjemplaresPrestados() < recurso.getNroEjemplares()) {
                        recurso.setFechaprestamo(LocalDate.now());
                        recurso.setNroEjemplaresPrestados(recurso.getNroEjemplaresPrestados() + 1l);
                        return actualizarRecursoUseCase.apply(mapperUtils.mapRecursoToDTO().apply(recurso))
                                .thenReturn("{mensaje: recurso prestado satisfactoriamente}");
                    }
                    return Mono.just("{mensaje: No hay Ejemplares suficientes para realizar el prestamo}");
                }
        );
    }
}
