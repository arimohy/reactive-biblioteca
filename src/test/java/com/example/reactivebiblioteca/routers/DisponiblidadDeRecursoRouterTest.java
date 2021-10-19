package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.collections.Recurso;
import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.repositories.Repositorio;
import com.example.reactivebiblioteca.usecases.ConsultarDisponibilidadUseCase;
import com.example.reactivebiblioteca.usecases.EliminarRecursoUseCase;
import com.example.reactivebiblioteca.usecases.MapperUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@ContextConfiguration(classes = {DisponiblidadDeRecursoRouter.class, ConsultarDisponibilidadUseCase.class, MapperUtils.class})
class DisponiblidadDeRecursoRouterTest {
    @MockBean
    private Repositorio repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testDisponibilidadRecurso() {
        Recurso recurso = new Recurso();
        recurso.setId("xxxxxxx");
        recurso.setTitulo("titulo1");
        recurso.setTipoRecurso("libro");
        recurso.setTematica("gato");
        recurso.setDisponible("si");
        recurso.setFechaprestamo(LocalDate.now());
        recurso.setNroEjemplares(2L);
        recurso.setNroEjemplaresPrestados(1L);

        Mono<Recurso> recursoMono = Mono.just(recurso);
        when(repositorio.findById(recurso.getId())).thenReturn(recursoMono);


        webTestClient.get()
                .uri("/recursos/disponible/{id}", "xxxxxxx")
                .exchange()
                .expectStatus().isAccepted()
                .expectBody(String.class)
                .value(userResponse -> {
                    Assertions.assertThat(userResponse)
                            .isEqualTo("{mensaje: El recurso " + recurso.getTitulo() + " esta disponible}");
                        }
                );
    }
}