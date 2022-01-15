package com.zonaut.sbreactive.controllers;

import javax.validation.constraints.NotBlank;

public class TransferObjects {

    // Todos
    record CreateTodoTO(@NotBlank String title) {}
    record UpdateTodoTO(@NotBlank String title) {}

}
