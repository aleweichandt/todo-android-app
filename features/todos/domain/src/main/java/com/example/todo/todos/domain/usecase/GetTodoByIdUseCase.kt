package com.example.todo.todos.domain.usecase

import com.example.todo.base.domain.IExceptionHandler
import com.example.todo.base.domain.UseCase
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetTodoByIdUseCase(
    private val repository: TodoRepository,
    handler: IExceptionHandler,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<Long, Todo>(handler, dispatcher) {
    override suspend fun performAction(param: Long) =
        repository.getTodoById(param)?.let { Result.Success(it) } ?: Result.Failure()
}
