package com.zonaut.sbreactive.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zonaut.sbreactive.common.ViewObject;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Table(Todo.TABLE_NAME)
@Getter
public class Todo extends ViewObject implements Persistable<UUID> {

    public static final String TABLE_NAME = "todos";

    public static final String ID = "id";
    public static final String CREATED_AT = "created_at";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String DONE = "done";

    @Id
    @Column(ID)
    private UUID id;

    @Column(CREATED_AT)
    private Instant createdAt;

    @Column(TITLE)
    private String title;

    @Column(CONTENT)
    private String content;

    @Column(DONE)
    private boolean done;

    private Todo() {
    }

    private Todo(Builder builder) {
        id = builder.id;
        title = builder.title;
        content = builder.content;
        done = builder.done;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return this.createdAt == null;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Todo todo = (Todo) o;
        return id.equals(todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final class Builder {
        private UUID id;
        private String title;
        private String content;
        private boolean done;

        private Builder() {
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withDone(boolean done) {
            this.done = done;
            return this;
        }

        public Todo build() {
            return new Todo(this);
        }
    }

    @Override
    public String toString() {
        return "Todo{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", done=" + done +
            '}';
    }
}
