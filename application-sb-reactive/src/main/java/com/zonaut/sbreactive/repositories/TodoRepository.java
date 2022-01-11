package com.zonaut.sbreactive.repositories;

import java.util.UUID;

import com.zonaut.sbreactive.domain.Todo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends R2dbcRepository<Todo, UUID> {
}
