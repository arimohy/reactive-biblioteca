package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.collections.Recurso;
import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.repositories.Repositorio;
import com.example.reactivebiblioteca.usecases.MapperUtils;
import com.example.reactivebiblioteca.usecases.RecomendarTipoRecursoTematicaUseCase;
import com.example.reactivebiblioteca.usecases.RecomendarTipoRecursoUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RecomendarTipoyTematicaRoter.class, RecomendarTipoRecursoTematicaUseCase.class, MapperUtils.class})

class RecomendarTipoyTematicaRoterTest {
    @MockBean
    private Repositorio repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testRecomendarTipoRecurso() {
        Recurso recurso1 = new Recurso();
        recurso1.setTitulo("titulo1");
        recurso1.setTipoRecurso("libro");
        recurso1.setTematica("gatos");
        recurso1.setNroEjemplares(0L);
        recurso1.setNroEjemplaresPrestados(1L);

        Recurso recurso2 = new Recurso();
        recurso2.setTitulo("titulo2");
        recurso2.setTipoRecurso("revista");
        recurso2.setTematica("perros");
        recurso2.setNroEjemplares(0L);
        recurso2.setNroEjemplaresPrestados(1L);

        when(repositorio.findAll()).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos/recomendar/tipoytematica/{tiporecurso}/{tematica}", "revista","perros")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getTitulo()).isEqualTo(recurso2.getTitulo());
                            Assertions.assertThat(userResponse.get(0).getTipoRecurso()).isEqualTo(recurso2.getTipoRecurso());
                            Assertions.assertThat(userResponse.get(0).getTematica()).isEqualTo(recurso2.getTematica());
                            Assertions.assertThat(userResponse.get(0).getNroEjemplares()).isEqualTo(recurso2.getNroEjemplares());
                            Assertions.assertThat(userResponse.get(0).getNroEjemplaresPrestados()).isEqualTo(recurso2.getNroEjemplaresPrestados());

                        }
                );
    }

}