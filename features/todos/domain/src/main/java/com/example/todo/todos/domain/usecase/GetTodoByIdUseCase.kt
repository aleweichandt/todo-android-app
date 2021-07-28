package com.example.todo.todos.domain.usecase

import com.example.todo.base.domain.FlowUseCase
import com.example.todo.base.domain.IExceptionHandler
import com.example.todo.base.domain.UseCase.Result
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map

class GetTodoByIdUseCase(
    private val repository: TodoRepository,
    handler: IExceptionHandler,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FlowUseCase<Long, Todo>(handler, dispatcher) {
    override suspend fun performAction(param: Long) =
        repository.getTodoById(param).map { Result.fromNullable(it) }
}
