package com.example.todo.todos.domain.usecase

import com.example.todo.base.domain.IExceptionHandler
import com.example.todo.base.domain.UseCase
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetAllTodosUseCase(
    private val repository: TodoRepository,
    private val handler: IExceptionHandler,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<Boolean, List<Todo>>(handler, dispatcher) {
    override suspend fun performAction(param: Boolean): Result<List<Todo>> =
        repository.getAllTodos()?.let { Result.Success(it) } ?: Result.Failure()
}
