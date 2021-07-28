package com.example.todo.todos.domain.usecase

import com.example.todo.base.domain.FlowUseCase
import com.example.todo.base.domain.IExceptionHandler
import com.example.todo.base.domain.UseCase.Result
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllTodosUseCase(
    private val repository: TodoRepository,
    handler: IExceptionHandler,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FlowUseCase<Boolean, List<Todo>>(handler, dispatcher) {
    override suspend fun performAction(param: Boolean): Flow<Result<List<Todo>>> =
        repository.getAllTodos().map { Result.fromNullable(it) }
}
