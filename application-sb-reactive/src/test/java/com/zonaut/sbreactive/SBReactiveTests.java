package com.zonaut.sbreactive;

import com.zonaut.sbreactive.domain.Todo;
import com.zonaut.sbreactive.to.CreateTodo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.zonaut.sbreactive.TodoTestConstants.ANOTHER_TODO_TITLE;
import static com.zonaut.sbreactive.TodoTestConstants.anotherTodo;
import static com.zonaut.sbreactive.TodoTestConstants.todo;
import static com.zonaut.sbreactive.controllers.TodoController.API_V_1_TODOS;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SBReactiveTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testAllTodos() {
        WebTestClient.ListBodySpec<Todo> bodyList = webTestClient.get().uri(API_V_1_TODOS)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBodyList(Todo.class);
        bodyList.hasSize(1);
    }

    @Test
    public void testGetOnId() {
        UUID uuid = UUID.fromString("238dbbba-99de-4531-afe8-973120607330");

        webTestClient.get()
            .uri("/api/v1/todos/{id}", uuid.toString())
            .exchange()
            .expectStatus().isOk()
            .expectBody(Todo.class)
            .consumeWith(response -> {
                Assertions.assertThat(response.getResponseBody()).isNotNull();
                Assertions.assertThat(response.getResponseBody().getId()).isEqualTo(uuid);
            });
    }

    @Test
    public void testCreate() {
        CreateTodo createTodo = TodoTestConstants.createTodo();
        Todo todo = todo();
        Todo anotherTodo = anotherTodo();

        webTestClient.post().uri(API_V_1_TODOS)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(createTodo), CreateTodo.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Todo.class)
            .consumeWith(response -> {
                Assertions.assertThat(response.getResponseBody()).isNotNull();
                Assertions.assertThat(response.getResponseBody().getId()).isNotNull();
//                Assertions.assertThat(response.getResponseBody().getCreatedAt()).isNotNull();
                Assertions.assertThat(response.getResponseBody().getTitle()).isEqualTo(todo.getTitle());
            });

        webTestClient.post().uri(API_V_1_TODOS)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(TodoTestConstants.createTodoBuilder()
                .withTitle(ANOTHER_TODO_TITLE)
                .build()), CreateTodo.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.title").isEqualTo(anotherTodo.getTitle());
    }

    @Test
    public void testUpdate() {
        UUID uuid = UUID.fromString("238dbbba-99de-4531-afe8-973120607330");
        CreateTodo createTodo = TodoTestConstants.createTodoBuilder()
            .build();

        webTestClient.put()
            .uri("/api/v1/todos/{id}", uuid.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(createTodo), CreateTodo.class)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Todo.class)
            .consumeWith(response -> {
                Assertions.assertThat(response.getResponseBody()).isNotNull();
                Assertions.assertThat(response.getResponseBody().isDone()).isEqualTo(true);
            });
    }

    @Test
    public void testDeleteTodo() {
        UUID uuid = UUID.fromString("238dbbba-99de-4531-afe8-973120607330");

        webTestClient.delete()
            .uri("/api/v1/todos/{id}", uuid.toString())
            .exchange()
            .expectStatus().isOk();
    }

}
