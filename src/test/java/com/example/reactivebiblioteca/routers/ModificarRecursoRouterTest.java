package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.collections.Recurso;
import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.repositories.Repositorio;
import com.example.reactivebiblioteca.usecases.ActualizarRecursoUseCase;
import com.example.reactivebiblioteca.usecases.CrearRecursoUseCase;
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
@ContextConfiguration(classes = {ModificarRecursoRouter.class, ActualizarRecursoUseCase.class, MapperUtils.class})
class ModificarRecursoRouterTest {
    @MockBean
    private Repositorio repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testUpdateRecurso() {

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
                "titulo2",
                recurso.getTipoRecurso(),
                recurso.getTematica(),
                recurso.getDisponible(),
                recurso.getFechaprestamo(),
                recurso.getNroEjemplares(),
                recurso.getNroEjemplaresPrestados()
        );
        Mono<Recurso> recursoMono = Mono.just(recurso);
        when(repositorio.save(any())).thenReturn(recursoMono);

        webTestClient.put()
                .uri("/recursos/modificar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEqualTo(recurso.getId());
                        }
                );
    }

}