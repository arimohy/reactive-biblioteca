package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.usecases.EliminarRecursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class EliminarRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> delete(EliminarRecursoUseCase eliminarRecursoUseCase) {
        return route(
                DELETE("/recursos/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(eliminarRecursoUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }
}
