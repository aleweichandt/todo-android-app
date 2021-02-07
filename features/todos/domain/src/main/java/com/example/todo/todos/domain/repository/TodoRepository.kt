package com.example.todo.todos.domain.repository

import com.example.todo.todos.domain.model.Todo

class TodoRepository {
    // TODO remove mock
    private val todos: MutableList<Todo> = mutableListOf(
        Todo("mock", "testt"),
        Todo("mock completed", "test", true)
    )

    suspend fun getAllTodos(): List<Todo>? = todos

    suspend fun getTodoById(id: Long): Todo? = todos.find { it.uuid == id }

    suspend fun addTodo(todo: Todo): Boolean? =
        todos.add(todo)

    suspend fun deleteTodo(todo: Todo): Boolean? =
        todos.remove(todo)

    suspend fun updateTodo(todo: Todo, update: Todo): Boolean? =
        when (val index = todos.indexOf(todo)) {
            -1 -> false
            else -> {
                todos.removeAt(index)
                todos.add(index, update)
                true
            }
        }

}
