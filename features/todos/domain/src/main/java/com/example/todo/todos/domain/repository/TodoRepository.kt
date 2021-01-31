package com.example.todo.todos.domain.repository

import com.example.todo.todos.domain.model.Todo

class TodoRepository {
    // TODO remove mock
    suspend fun getAllTodos(): List<Todo>? =
        listOf(Todo("mock"), Todo("mock completed", true))
}
