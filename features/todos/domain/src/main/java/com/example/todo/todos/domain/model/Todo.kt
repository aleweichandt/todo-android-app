package com.example.todo.todos.domain.model

import java.io.Serializable

data class Todo(
    val value: String = "",
    val completed: Boolean = false,
    val uuid: Long = nextUuid
) : Serializable {
    companion object {
        private var uuid: Long = 0
        val nextUuid: Long
            get() = uuid.also { uuid = it + 1 }
    }
}
