package com.example.reactivebiblioteca.usecases;

import com.example.reactivebiblioteca.collections.Recurso;
import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.repositories.Repositorio;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class ActualizarRecursoUseCase  implements GuardarRecurso{
    private final Repositorio repositorio;
    private final MapperUtils mapperUtils;

    public ActualizarRecursoUseCase(MapperUtils mapperUtils, Repositorio repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(RecursoDTO recursoDTO) {
        Objects.requireNonNull(recursoDTO.getId(), "El id del recurso es requerido");
        return repositorio.save(mapperUtils.mapperToRecurso(recursoDTO.getId()).apply(recursoDTO))
                .map(Recurso::getId);
    }
}
