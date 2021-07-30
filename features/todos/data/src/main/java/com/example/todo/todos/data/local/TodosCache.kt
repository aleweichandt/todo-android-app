package com.example.todo.todos.data.local

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
    override suspend fun getAllTodos() = runQuery {
        dao.getAll().map { it.toDomain() }
    }

    override suspend fun getTodoById(id: Long) = runQuery {
        dao.getById(id)?.toDomain()
    }

    override suspend fun storeAllTodos(todos: List<Todo>) {
        runQuery {
            todos.map(Todo::toEntity).let {
                dao.replaceAll(*it.toTypedArray())
            }
        }
    }

    override suspend fun storeTodo(todo: Todo) {
        runQuery {
            dao.insert(todo.toEntity())
        }
    }

    override suspend fun deleteAllTodos() {
        runQuery {
            dao.deleteAll()
        }
    }
}
