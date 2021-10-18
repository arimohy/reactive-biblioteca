package com.example.reactivebiblioteca.usecases;

import com.example.reactivebiblioteca.repositories.Repositorio;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class ConsultarDisponibilidadUseCase implements Function<String, Mono<String>> {
    private final Repositorio repositorio;
    public ConsultarDisponibilidadUseCase(Repositorio repositorio) {
        this.repositorio = repositorio;
    }


    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "El id no puede ser nulo");
        return repositorio.findById(id)
                .map(recurso ->
                        recurso.getNroEjemplaresPrestados() < recurso.getNroEjemplares()
                                ? "{mensaje: El recurso " + recurso.getTitulo() + " esta disponible}"
                                : "{mensaje: El recurso " + recurso.getTitulo() + " no esta disponible, fechaprestamo: " + recurso.getFechaprestamo() + "}"
                );
    }
}
