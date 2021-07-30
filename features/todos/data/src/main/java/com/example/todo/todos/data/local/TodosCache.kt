package com.example.todo.todos.data.local

import android.util.Log
import com.example.todo.base.data.cache.Cache
import com.example.todo.base.domain.IExceptionHandler
import com.example.todo.todos.data.ITodoCache
import com.example.todo.todos.data.local.dao.TodoDao
import com.example.todo.todos.data.local.dao.replaceAll
import com.example.todo.todos.domain.model.Todo

class TodosCache(
    private val dao: TodoDao,
    exceptionHandler: IExceptionHandler
) : Cache(exceptionHandler), ITodoCache {
    companion object {
        private const val tag = "LOCAL-SOURCE"
    }
    override suspend fun getAllTodos() = runQuery {
        Log.d(tag, "Get All Todos")
        dao.getAll().map { it.toDomain() }
    }

    override suspend fun getTodoById(id: Long) = runQuery {
        Log.d(tag, "Get Todo $id")
        dao.getById(id)?.toDomain()
    }

    override suspend fun storeAllTodos(todos: List<Todo>) {
        runQuery {
            Log.d(tag, "Add All Todos")
            todos.map(Todo::toEntity).let {
                dao.replaceAll(*it.toTypedArray())
            }
        }
    }

    override suspend fun storeTodo(todo: Todo) {
        runQuery {
            Log.d(tag, "Add Todo ${todo.uuid}")
            dao.insert(todo.toEntity())
        }
    }

    override suspend fun deleteAllTodos() {
        runQuery {
            Log.d(tag, "Delete All Todos")
            dao.deleteAll()
        }
    }
}
