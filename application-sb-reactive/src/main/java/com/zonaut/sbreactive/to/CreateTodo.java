package com.zonaut.sbreactive.to;

public class CreateTodo {

    private String title;

    private CreateTodo() {
    }

    private CreateTodo(Builder builder) {
        title = builder.title;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public static final class Builder {
        private String title;

        private Builder() {
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public CreateTodo build() {
            return new CreateTodo(this);
        }
    }
}
