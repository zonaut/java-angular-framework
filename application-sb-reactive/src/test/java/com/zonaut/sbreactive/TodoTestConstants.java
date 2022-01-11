package com.zonaut.sbreactive;

import com.zonaut.sbreactive.domain.Todo;
import com.zonaut.sbreactive.to.CreateTodo;

import java.time.Instant;
import java.util.UUID;

public class TodoTestConstants {

    public static final UUID TODO_ID = UUID.randomUUID();
    public static final String TODO_TITLE = "todo title";
    public static final String TODO_CONTENT = "todo content";

    public static final UUID ANOTHER_TODO_ID = UUID.randomUUID();
    public static final String ANOTHER_TODO_TITLE = "another todo title";
    public static final String ANOTHER_TODO_CONTENT = "another todo content";

    public static Todo todo() {
        return todoBuilder()
            .build();
    }

    public static Todo anotherTodo() {
        return todoBuilder()
            .withId(ANOTHER_TODO_ID)
            .withTitle(ANOTHER_TODO_TITLE)
            .withContent(ANOTHER_TODO_CONTENT)
            .build();
    }

    public static Todo.Builder todoBuilder() {
        return Todo.newBuilder()
            .withId(TODO_ID)
            .withTitle(TODO_TITLE)
            .withContent(TODO_CONTENT)
            .withDone(false);
    }

    public static CreateTodo createTodo() {
        return createTodoBuilder()
            .build();
    }

    public static CreateTodo.Builder createTodoBuilder() {
        return CreateTodo.newBuilder()
            .withTitle(TODO_TITLE);
    }

}
