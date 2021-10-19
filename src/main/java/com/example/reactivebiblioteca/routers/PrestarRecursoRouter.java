package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.usecases.PrestarRecursoUseCase;
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
public class PrestarRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> Prestar(PrestarRecursoUseCase prestarRecursoUseCase) {
        return route(
                GET("/recursos/prestar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromPublisher(prestarRecursoUseCase
                                        .apply(request.pathVariable("id")), String.class))
        );
    }
}
