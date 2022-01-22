package com.zonaut.sbreactive;

import com.zonaut.sbreactive.domain.Todo;
import com.zonaut.sbreactive.domain.TodoData;
import com.zonaut.sbreactive.repositories.TodoRepository;
import com.zonaut.sbreactive.types.TodoPriority;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.UUID;

@Service
public class DataStubService implements CommandLineRunner {

    private static final Random RANDOM = new Random();

    private final TodoRepository todoRepository;

    public DataStubService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void run(String... args) {
        insertTodos();
    }

    private void insertTodos() {
        Flux.range(1, 100)
            .map(i -> Todo.newBuilder()
                .withId(UUID.randomUUID())
                .withTitle(UUID.randomUUID().toString())
                .withContent(UUID.randomUUID().toString())
                .withPriority(randomEnum(TodoPriority.class))
                .withData(TodoData.builder()
                    .title(UUID.randomUUID().toString())
                    .somethingElse(UUID.randomUUID().toString())
                    .build())
                .withDone(false)
                .build())
            .flatMap(this.todoRepository::save)
            .doOnComplete(() -> System.out.println("Inserted stub todos"))
            .subscribe();
    }

    private static <E extends Enum<E>> E randomEnum(final Class<E> enumClass) {
        final E[] constants = enumClass.getEnumConstants();
        return constants[RANDOM.nextInt(constants.length)];
    }

}
