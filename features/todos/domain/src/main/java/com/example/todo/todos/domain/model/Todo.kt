package com.example.todo.todos.domain.model

import java.io.Serializable

data class Todo(val value: String = "", val completed: Boolean = false): Serializable
