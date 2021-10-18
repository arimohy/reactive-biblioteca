package com.example.reactivebiblioteca.usecases;

import com.example.reactivebiblioteca.collections.Recurso;
import com.example.reactivebiblioteca.model.RecursoDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {
    public Function<RecursoDTO, Recurso> mapperToDato(String id) {
        return updateRecurso -> {
            var recurso = new Recurso();
            recurso.setId(id);
            recurso.setTitulo(updateRecurso.getTitulo());
            recurso.setTipoRecurso(updateRecurso.getTipoRecurso());
            recurso.setTematica(updateRecurso.getTematica());
            recurso.setDisponible(updateRecurso.getDisponible());
            recurso.setFechaprestamo(updateRecurso.getFechaprestamo());
            recurso.setNroEjemplares(updateRecurso.getNroEjemplares());
            recurso.setNroEjemplaresPrestados(updateRecurso.getNroEjemplaresPrestados());
            return recurso;
        };
    }
    public Function<Recurso, RecursoDTO> mapDatoToDTO() {
        return entity -> new RecursoDTO(
                entity.getId(),
                entity.getTitulo(),
                entity.getTipoRecurso(),
                entity.getTematica(),
                entity.getDisponible(),
                entity.getFechaprestamo(),
                entity.getNroEjemplares(),
                entity.getNroEjemplaresPrestados());
    }
}
