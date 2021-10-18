package com.example.reactivebiblioteca.usecases;

import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.repositories.Repositorio;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@Validated
public class RecomendarTipoRecursoTematicaUseCase implements BiFunction<String ,String, Flux<RecursoDTO>> {
    private final Repositorio repositorio;
    private final MapperUtils mapperUtils;

    public RecomendarTipoRecursoTematicaUseCase(MapperUtils mapperUtils, Repositorio repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> apply(String tiporecurso,String tematica) {
        return repositorio.findAll()
                .filter(recurso -> recurso.getTipoRecurso().equals(tiporecurso))
                .filter(recurso -> recurso.getTipoRecurso().equals(tematica))
                .map(mapperUtils.mapRecursoToDTO());
    }
}
