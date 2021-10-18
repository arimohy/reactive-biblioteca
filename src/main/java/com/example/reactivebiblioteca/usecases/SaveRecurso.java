package com.example.reactivebiblioteca.usecases;

import com.example.reactivebiblioteca.model.RecursoDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveRecurso {
    Mono<String> apply(@Valid RecursoDTO recursoDTO);
}
