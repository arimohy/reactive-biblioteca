package com.example.reactivebiblioteca.routers;

import com.example.reactivebiblioteca.model.RecursoDTO;
import com.example.reactivebiblioteca.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> create(CrearRecursoUseCase crearRecursoUseCase) {
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
                GET("/recursos/modificar").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listaRecursoUseCase.get(), RecursoDTO.class))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> update(ActualizarRecursoUseCase actualizarRecursoUseCase) {
        return route(
                PUT("/recursos/actualizar").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class)
                        .flatMap(questionDTO -> actualizarRecursoUseCase.apply(questionDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.TEXT_PLAIN)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> delete(EliminarRecursoUseCase eliminarRecursoUseCase) {
        return route(
                DELETE("/recursos/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(eliminarRecursoUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }
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
    @Bean
    public RouterFunction<ServerResponse> RecomendarTipoRecurso(RecomendarTipoRecursoUseCase recomendarTipoRecursoUseCase) {
        return route(
                GET("/recursos/recomendar/tiporecurso/{tiporecurso}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters
                                .fromPublisher(recomendarTipoRecursoUseCase
                                        .apply(request.pathVariable("tiporecurso")), RecursoDTO.class))
        );
    }
}
