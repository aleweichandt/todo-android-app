package com.example.todo.todos.domain.usecase

import com.example.todo.base.domain.IExceptionHandler
import com.example.todo.base.domain.UseCase
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class SetTodoCompletionUseCase(
    private val repository: TodoRepository,
    handler: IExceptionHandler,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<SetTodoCompletionUseCase.Request, Boolean>(handler, dispatcher) {
    data class Request(
        val item: Todo,
        val completion: Boolean
    )

    override suspend fun performAction(param: Request) =
        repository.updateTodo(param.item, param.item.copy(completed = param.completion))
            ?.let { Result.Success(it) } ?: Result.Failure()

}
