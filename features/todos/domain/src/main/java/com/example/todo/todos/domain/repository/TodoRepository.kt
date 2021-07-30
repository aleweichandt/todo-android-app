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

    suspend fun getAllTodos(): Flow<List<Todo>?> =
        resourceGroup.query(Unit)

    suspend fun getTodoById(id: Long): Flow<Todo?> =
        resourceGroup.queryByKey(id, Unit)

    suspend fun addTodo(todo: Todo): Boolean? =
        remoteSource.addTodo(todo).also { resourceGroup.evict() }

    suspend fun deleteTodo(todo: Todo): Boolean? =
        remoteSource.deleteTodo(todo).also { resourceGroup.evict() }

    suspend fun updateTodo(todo: Todo, update: Todo): Boolean? =
        remoteSource.updateTodo(todo, update).also { resourceGroup.evict() }
}
