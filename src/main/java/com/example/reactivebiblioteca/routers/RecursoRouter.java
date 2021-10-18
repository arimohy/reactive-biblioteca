package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.usecases.CrearRecursoUseCase;
import com.example.reactivebiblioteca.usecases.ListaRecursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> createQuestion(CrearRecursoUseCase crearRecursoUseCase) {
        return route(POST("/recursos/crear").and(accept(MediaType.APPLICATION_JSON)),
            request -> request.bodyToMono(RecursoDTO.class)
                    .flatMap(questionDTO -> crearRecursoUseCase.apply(questionDTO)
                            .flatMap(result -> ServerResponse.ok()
                                    .contentType(MediaType.TEXT_PLAIN)
                                    .bodyValue(result))
                    )
        );
    }
    @Bean
    public RouterFunction<ServerResponse> getAll(ListaRecursoUseCase listaRecursoUseCase) {
        return route(
                GET("/recursos/consultar").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listaRecursoUseCase.get(), RecursoDTO.class))
        );
    }
}
