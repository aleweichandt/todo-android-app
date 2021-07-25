package com.example.todo.base.domain

import com.example.todo.base.domain.UseCase.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

abstract class FlowUseCase<in TParam, out TResult>(
    private val exceptionHandler: IExceptionHandler,
    private val dispatcher: CoroutineDispatcher
) {

    @ExperimentalCoroutinesApi
    @Suppress("TooGenericExceptionCaught")
    suspend operator fun invoke(param: TParam) =
        performAction(param)
            .catch { exception ->
                exceptionHandler.handle(exception)
                emit(Result.Failure(exception))
            }
            .flowOn(dispatcher)

    protected abstract suspend fun performAction(param: TParam): Flow<Result<TResult>>
}
