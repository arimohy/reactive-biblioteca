package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.collections.Recurso;
import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.repositories.Repositorio;
import com.example.reactivebiblioteca.usecases.MapperUtils;
import com.example.reactivebiblioteca.usecases.PrestarRecursoUseCase;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PrestarRecursoRouter.class, PrestarRecursoUseCase.class, MapperUtils.class})

class PrestarRecursoRouterTest {
    @MockBean
    private Repositorio repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testPrestarRecurso() {
        Recurso recurso = new Recurso();
        recurso.setId("xxxxxxx");
        recurso.setTitulo("titulo1");
        recurso.setTipoRecurso("libro");
        recurso.setTematica("gato");
        recurso.setDisponible("si");
        recurso.setFechaprestamo(LocalDate.now());
        recurso.setNroEjemplares(2L);
        recurso.setNroEjemplaresPrestados(1L);
        RecursoDTO recursoDTO = new RecursoDTO(
                recurso.getId(),
                recurso.getTitulo(),
                recurso.getTipoRecurso(),
                recurso.getTematica(),
                recurso.getDisponible(),
                recurso.getFechaprestamo(),
                recurso.getNroEjemplares(),
                recurso.getNroEjemplaresPrestados()
        );

        Mono<Recurso> recursoMono = Mono.just(recurso);
        when(repositorio.findById(recurso.getId())).thenReturn(recursoMono);
        when(repositorio.save(any())).thenReturn(recursoMono);

        webTestClient.get()
                .uri("/recursos/prestar/{id}", "xxxxxxx")
                .exchange()
                .expectStatus().isAccepted()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEqualTo("{mensaje: recurso prestado satisfactoriamente}");
                        }
                );
    }

}