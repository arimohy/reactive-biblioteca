package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.usecases.ConsultarDisponibilidadUseCase;
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
public class DisponiblidadDeRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> Disponiblibilidad(ConsultarDisponibilidadUseCase consultarDisponibilidadUseCase) {
        return route(
                GET("/recursos/disponible/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromPublisher(consultarDisponibilidadUseCase
                                        .apply(request.pathVariable("id")), String.class))
        );
    }
}
