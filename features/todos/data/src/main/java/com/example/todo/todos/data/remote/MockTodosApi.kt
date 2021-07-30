package com.example.todo.todos.data.remote

import android.util.Log
import com.example.todo.todos.data.ITodoApi
import com.example.todo.todos.domain.model.Todo
import kotlinx.coroutines.delay

class MockTodosApi: ITodoApi {
    companion object {
        private const val tag = "REMOTE-SOURCE"
        private const val FAKE_DELAY_MS = 2000L
    }
    private val todos: MutableList<Todo> = mutableListOf(
        Todo("mock", "testt"),
        Todo("mock completed", "test", true)
    )

    override suspend fun getAllTodos(): List<Todo>? {
        Log.d(tag, "Get All Todos")
        delay(FAKE_DELAY_MS)
        return todos
    }

    override suspend fun getTodoById(id: Long): Todo? {
        Log.d(tag, "Get Todo $id")
        delay(FAKE_DELAY_MS)
        return todos.find { it.uuid == id }
    }

    override suspend fun addTodo(todo: Todo): Boolean? {
        Log.d(tag, "Add Todo ${todo.uuid}")
        return todos.add(todo)
    }

    override suspend fun deleteTodo(todo: Todo): Boolean? {
        Log.d(tag, "Delete Todo ${todo.uuid}")
        return todos.remove(todo)
    }

    override suspend fun updateTodo(todo: Todo, update: Todo): Boolean? {
        Log.d(tag, "Update Todo ${todo.uuid}")
        return when (val index = todos.indexOf(todo)) {
            -1 -> false
            else -> {
                todos.removeAt(index)
                todos.add(index, update)
                true
            }
        }
    }
}
