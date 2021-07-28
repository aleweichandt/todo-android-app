package com.example.todo.todos.domain.repository

import com.example.todo.todos.data.ITodoApi
import com.example.todo.todos.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TodoRepository(
    private val remoteSource: ITodoApi
) {

    suspend fun getAllTodos(): Flow<List<Todo>?> =
        flowOf(remoteSource.getAllTodos())

    suspend fun getTodoById(id: Long): Flow<Todo?> =
        flowOf(remoteSource.getTodoById(id))

    suspend fun addTodo(todo: Todo): Boolean? =
        remoteSource.addTodo(todo)

    suspend fun deleteTodo(todo: Todo): Boolean? =
        remoteSource.deleteTodo(todo)

    suspend fun updateTodo(todo: Todo, update: Todo): Boolean? =
        remoteSource.updateTodo(todo, update)

}
