package com.example.reactivebiblioteca.usecases;

import com.example.reactivebiblioteca.model.RecursoDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface GuardarRecurso {
    Mono<String> apply(@Valid RecursoDTO recursoDTO);
}
