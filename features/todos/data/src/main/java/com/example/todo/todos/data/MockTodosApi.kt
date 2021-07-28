package com.example.todo.todos.data

import com.example.todo.todos.domain.model.Todo

class MockTodosApi: ITodoApi {
    private val todos: MutableList<Todo> = mutableListOf(
        Todo("mock", "testt"),
        Todo("mock completed", "test", true)
    )

    override suspend fun getAllTodos(): List<Todo>? = todos

    override suspend fun getTodoById(id: Long): Todo? = todos.find { it.uuid == id }

    override suspend fun addTodo(todo: Todo): Boolean? =
        todos.add(todo)

    override suspend fun deleteTodo(todo: Todo): Boolean? =
        todos.remove(todo)

    override suspend fun updateTodo(todo: Todo, update: Todo): Boolean? =
        when (val index = todos.indexOf(todo)) {
            -1 -> false
            else -> {
                todos.removeAt(index)
                todos.add(index, update)
                true
            }
        }
}
