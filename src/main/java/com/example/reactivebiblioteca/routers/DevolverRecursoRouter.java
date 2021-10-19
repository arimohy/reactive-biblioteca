package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.usecases.DevolverRecursoUseCase;
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
public class DevolverRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> Devolver(DevolverRecursoUseCase devolverRecursoUseCase) {
        return route(
                GET("/recursos/devolver/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromPublisher(devolverRecursoUseCase
                                        .apply(request.pathVariable("id")), String.class))
        );
    }
}
