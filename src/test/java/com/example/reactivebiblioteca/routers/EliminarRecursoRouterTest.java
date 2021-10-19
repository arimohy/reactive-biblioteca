package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.collections.Recurso;
import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.repositories.Repositorio;
import com.example.reactivebiblioteca.usecases.CrearRecursoUseCase;
import com.example.reactivebiblioteca.usecases.EliminarRecursoUseCase;
import com.example.reactivebiblioteca.usecases.MapperUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EliminarRecursoRouter.class, EliminarRecursoUseCase.class, MapperUtils.class})
class EliminarRecursoRouterTest {
    @MockBean
    private Repositorio repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testEliminarRecurso() {



        Mono<Void>  vacio= Mono.empty();

        when(repositorio.deleteById("1")).thenReturn(vacio);

        webTestClient.delete()
                .uri("/recursos/{id}", "1")
                .exchange()
                .expectStatus()
                .isAccepted();
    }
}