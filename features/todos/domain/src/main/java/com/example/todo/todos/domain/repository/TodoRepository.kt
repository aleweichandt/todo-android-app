package com.example.todo.todos.domain.repository

import com.example.todo.todos.data.ITodoApi
import com.example.todo.todos.domain.model.Todo

class TodoRepository(
    private val remoteSource: ITodoApi
) {

    suspend fun getAllTodos(): List<Todo>? =
        remoteSource.getAllTodos()

    suspend fun getTodoById(id: Long): Todo? =
        remoteSource.getTodoById(id)

    suspend fun addTodo(todo: Todo): Boolean? =
        remoteSource.addTodo(todo)

    suspend fun deleteTodo(todo: Todo): Boolean? =
        remoteSource.deleteTodo(todo)

    suspend fun updateTodo(todo: Todo, update: Todo): Boolean? =
        remoteSource.updateTodo(todo, update)

}
