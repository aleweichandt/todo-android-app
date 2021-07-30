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
) : FlowUseCase<GetTodoByIdUseCase.Request, Todo>(handler, dispatcher) {
    data class Request(
        val uuid: Long,
        val force: Boolean = false
    )
    override suspend fun performAction(param: Request) =
        repository.getTodoById(param.uuid, param.force).map { Result.fromNullable(it) }
}
