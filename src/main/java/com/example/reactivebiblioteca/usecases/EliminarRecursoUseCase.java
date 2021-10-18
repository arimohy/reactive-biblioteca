package com.example.reactivebiblioteca.usecases;


import com.example.reactivebiblioteca.repositories.Repositorio;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class EliminarRecursoUseCase implements Function<String, Mono<Void>> {
    private final Repositorio repositorio;
    private final MapperUtils mapperUtils;


    public EliminarRecursoUseCase(MapperUtils mapperUtils, Repositorio repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "Id is requerido");
        return repositorio.deleteById(id)
                .switchIfEmpty(Mono.defer(() -> repositorio.deleteById(id)));
    }
}
