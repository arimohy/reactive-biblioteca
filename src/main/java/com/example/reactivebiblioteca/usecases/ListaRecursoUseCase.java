package com.example.reactivebiblioteca.usecases;

import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.repositories.Repositorio;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListaRecursoUseCase implements Supplier<Flux<RecursoDTO>> {
    private final Repositorio repositorio;
    private final MapperUtils mapperUtils;

    public ListaRecursoUseCase(MapperUtils mapperUtils, Repositorio repositorio) {
        this.repositorio = repositorio;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return repositorio.findAll().map(mapperUtils.mapDatoToDTO());
    }
}
