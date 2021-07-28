package com.example.todo.base.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in TParam, out TResult>(
    private val exceptionHandler: IExceptionHandler,
    private val dispatcher: CoroutineDispatcher
) {
    sealed class Result<out TResultModel> {
        companion object {
            fun <TResultModel> fromNullable(result: TResultModel?) = when (result) {
                null -> Failure()
                else -> Success(result)
            }
        }

        data class Success<out TResultModel>(val result: TResultModel) : Result<TResultModel>()
        data class Failure(val error: Throwable? = null) : Result<Nothing>()
    }

    suspend operator fun invoke(param: TParam) = try {
        withContext(dispatcher) {
            performAction(param)
        }
    } catch (error: Throwable) {
        exceptionHandler.handle(error)
        Result.Failure(error)
    }

    protected abstract suspend fun performAction(param: TParam): Result<TResult>
}
