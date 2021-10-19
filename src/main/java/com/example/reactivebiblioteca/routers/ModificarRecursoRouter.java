package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.usecases.ActualizarRecursoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
public class ModificarRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> update(ActualizarRecursoUseCase actualizarRecursoUseCase) {
        return route(
                PUT("/recursos/modificar").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class)
                        .flatMap(questionDTO -> actualizarRecursoUseCase.apply(questionDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.TEXT_PLAIN)
                                        .bodyValue(result))
                        )
        );
    }
}
