package com.example.reactivebiblioteca.usecases;

import com.example.reactivebiblioteca.collections.Recurso;
import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.repositories.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CrearRecursoUseCase implements GuardarRecurso {
    private final Repositorio repositorio;
    private final MapperUtils mapperUtils;

    @Autowired
    public CrearRecursoUseCase(MapperUtils mapperUtils, Repositorio repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(RecursoDTO recursoDTO) {
        return repositorio.save(mapperUtils.mapperToRecurso(null).apply(recursoDTO)).map(Recurso::getId);
    }
}
