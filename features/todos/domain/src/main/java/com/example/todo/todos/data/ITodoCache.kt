package com.example.todo.todos.data

import com.example.todo.todos.domain.model.Todo

interface ITodoCache {
    suspend fun getAllTodos(): List<Todo>?
    suspend fun getTodoById(id: Long): Todo?
    suspend fun storeAllTodos(todos: List<Todo>)
    suspend fun storeTodo(todo: Todo)
    suspend fun deleteAllTodos()
}
