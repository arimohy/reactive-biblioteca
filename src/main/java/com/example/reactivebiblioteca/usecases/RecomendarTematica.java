package com.example.reactivebiblioteca.usecases;

import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.repositories.Repositorio;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class RecomendarTematica implements Function<String , Flux<RecursoDTO>> {
    private final Repositorio repositorio;
    private final MapperUtils mapperUtils;

    public RecomendarTematica(MapperUtils mapperUtils, Repositorio repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> apply(String tematica) {
        return repositorio.findAll().filter(recurso -> recurso
                        .getTipoRecurso().equals(tematica))
                .map(mapperUtils.mapRecursoToDTO());
    }
}
