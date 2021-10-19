package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.usecases.RecomendarTipoRecursoTematicaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecomendarTipoyTematicaRoter {
    @Bean
    public RouterFunction<ServerResponse> RecomendarTipoRecursoTematica(RecomendarTipoRecursoTematicaUseCase recomendarTipoRecursoTematicaUseCase) {

        return route(
                GET("/recursos/recomendar/tipoytematica/{tiporecurso}/{tematica}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromPublisher(recomendarTipoRecursoTematicaUseCase
                                        .apply(request.pathVariable("tiporecurso"),request.pathVariable("tematica")), RecursoDTO.class))
                        .onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }
}
