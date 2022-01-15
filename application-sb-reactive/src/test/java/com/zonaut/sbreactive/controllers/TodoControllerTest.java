package com.zonaut.sbreactive.controllers;

import com.zonaut.sbreactive.controllers.TransferObjects.CreateTodoTO;
import com.zonaut.sbreactive.controllers.TransferObjects.UpdateTodoTO;
import com.zonaut.sbreactive.domain.Todo;
import com.zonaut.sbreactive.extensions.FixedPortDatabaseTestContainerExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.zonaut.sbreactive.controllers.TodoController.API_V_1_TODOS;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(FixedPortDatabaseTestContainerExtension.class)
public class TodoControllerTest {

    public static final String API_V_1_TODOS_ON_ID = API_V_1_TODOS + "/{id}";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getAllTodos() {
        Todo createdTodo = createTodo(new CreateTodoTO(UUID.randomUUID().toString()));

        webTestClient.get().uri(API_V_1_TODOS)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBodyList(Todo.class)
            .contains(createdTodo);
    }

    @Test
    public void getTodoOnId() {
        Todo createdTodo = createTodo(new CreateTodoTO(UUID.randomUUID().toString()));

        webTestClient.get()
            .uri(API_V_1_TODOS_ON_ID, createdTodo.getId())
            .exchange()
            .expectStatus().isOk()
            .expectBody(Todo.class)
            .consumeWith(response -> {
                Assertions.assertThat(response.getResponseBody()).isNotNull();
                Assertions.assertThat(response.getResponseBody().getId()).isEqualTo(createdTodo.getId());
                Assertions.assertThat(response.getResponseBody().getTitle()).isEqualTo(createdTodo.getTitle());
            });
    }

    @Test
    public void updateTodo() {
        Todo createdTodo = createTodo(new CreateTodoTO(UUID.randomUUID().toString()));
        UpdateTodoTO updateTodoTO = new UpdateTodoTO(UUID.randomUUID().toString());

        webTestClient.put()
            .uri(API_V_1_TODOS_ON_ID, createdTodo.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(updateTodoTO), UpdateTodoTO.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Todo.class)
            .consumeWith(updateResponse -> {
                Assertions.assertThat(updateResponse.getResponseBody()).isNotNull();
                Assertions.assertThat(updateResponse.getResponseBody().isDone()).isEqualTo(true);
            });
    }

    @Test
    public void deleteTodo() {
        Todo createdTodo = createTodo(new CreateTodoTO(UUID.randomUUID().toString()));

        webTestClient.delete()
            .uri(API_V_1_TODOS_ON_ID, createdTodo.getId())
            .exchange()
            .expectStatus().isOk();
        webTestClient.get()
            .uri(API_V_1_TODOS_ON_ID, createdTodo.getId())
            .exchange()
            .expectStatus().isNotFound();
    }

    private Todo createTodo(CreateTodoTO createTodoTO) {
        return webTestClient.post().uri(API_V_1_TODOS)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(createTodoTO), CreateTodoTO.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Todo.class)
            .consumeWith(response -> {
                Assertions.assertThat(response.getResponseBody()).isNotNull();
                Assertions.assertThat(response.getResponseBody().getId()).isNotNull();
//                Assertions.assertThat(response.getResponseBody().getCreatedAt()).isNotNull();
                Assertions.assertThat(response.getResponseBody().getTitle()).isEqualTo(createTodoTO.title());
            })
            .returnResult()
            .getResponseBody();
    }

}
