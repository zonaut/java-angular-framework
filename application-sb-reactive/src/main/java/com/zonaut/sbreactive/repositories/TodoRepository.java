package com.zonaut.sbreactive.repositories;

import com.zonaut.sbreactive.domain.Todo;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TodoRepository extends ReactiveSortingRepository<Todo, UUID> {
}
