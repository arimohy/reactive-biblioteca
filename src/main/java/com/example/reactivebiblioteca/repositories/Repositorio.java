package com.example.reactivebiblioteca.repositories;

import com.example.reactivebiblioteca.collections.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface Repositorio extends ReactiveMongoRepository<Recurso, String> {
}
