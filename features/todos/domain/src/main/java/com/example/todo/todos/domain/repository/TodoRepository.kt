package com.example.todo.todos.domain.repository

import com.example.todo.todos.domain.model.Todo

class TodoRepository {
    // TODO remove mock
    private val todos: MutableList<Todo> = mutableListOf(
        Todo("mock"),
        Todo("mock completed", true)
    )

    suspend fun getAllTodos(): List<Todo>? = todos

    suspend fun addTodo(todo: Todo): Boolean? =
        todos.add(todo)

    suspend fun deleteTodo(todo: Todo): Boolean? =
        todos.remove(todo)
}
