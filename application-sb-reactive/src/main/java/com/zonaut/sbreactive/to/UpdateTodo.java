package com.zonaut.sbreactive.to;

public class UpdateTodo {

    private String title;

    private UpdateTodo() {
    }

    private UpdateTodo(Builder builder) {
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

        public UpdateTodo build() {
            return new UpdateTodo(this);
        }
    }
}
