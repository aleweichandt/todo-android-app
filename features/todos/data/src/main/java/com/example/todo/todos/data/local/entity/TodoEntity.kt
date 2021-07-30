package com.example.todo.todos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey val uuid: Long,
    val value: String,
    val body: String,
    val completed: Boolean,
)
