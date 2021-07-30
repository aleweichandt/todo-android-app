package com.example.todo.todos.domain.repository

import com.example.todo.base.data.ResourceGroup
import com.example.todo.todos.data.ITodoApi
import com.example.todo.todos.data.ITodoCache
import com.example.todo.todos.domain.model.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val localSource: ITodoCache,
    private val remoteSource: ITodoApi
) {
    private val resourceGroup = ResourceGroup<Unit, Long, Todo>(
        { remoteSource.getAllTodos() },
        { localSource.getAllTodos() },
        localSource::storeAllTodos,
        { id, _ -> remoteSource.getTodoById(id) },
        { id, _ -> localSource.getTodoById(id) },
        localSource::storeTodo,
        localSource::deleteAllTodos
    )

    suspend fun getAllTodos(force: Boolean): Flow<List<Todo>?> =
        resourceGroup.query(Unit, force)

    suspend fun getTodoById(id: Long, force: Boolean): Flow<Todo?> =
        resourceGroup.queryByKey(id, Unit, force)

    suspend fun addTodo(todo: Todo): Boolean? =
        remoteSource.addTodo(todo).also { resourceGroup.evict() }

    suspend fun deleteTodo(todo: Todo): Boolean? =
        remoteSource.deleteTodo(todo).also { resourceGroup.evict() }

    suspend fun updateTodo(todo: Todo, update: Todo): Boolean? =
        remoteSource.updateTodo(todo, update).also { resourceGroup.evict() }
}
