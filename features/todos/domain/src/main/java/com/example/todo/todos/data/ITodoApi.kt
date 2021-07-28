package com.example.todo.todos.data

import com.example.todo.todos.domain.model.Todo

interface ITodoApi {
    suspend fun getAllTodos(): List<Todo>?
    suspend fun getTodoById(id: Long): Todo?
    suspend fun addTodo(todo: Todo): Boolean?
    suspend fun deleteTodo(todo: Todo): Boolean?
    suspend fun updateTodo(todo: Todo, update: Todo): Boolean?
}
