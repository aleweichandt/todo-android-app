package com.example.todo.todos.data.local

import com.example.todo.todos.data.local.entity.TodoEntity
import com.example.todo.todos.domain.model.Todo

fun Todo.toEntity() = TodoEntity(
    uuid, value, body, completed
)

fun TodoEntity.toDomain() = Todo(
    value, body, completed, uuid
)
