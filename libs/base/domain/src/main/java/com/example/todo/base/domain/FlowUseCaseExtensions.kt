package com.example.todo.base.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList


@ExperimentalCoroutinesApi
suspend operator fun <R> FlowUseCase<Unit, R>.invoke() = this(Unit)

suspend fun <TResult> Flow<UseCase.Result<TResult>>.resolve() =
    latest { it is UseCase.Result.Success } ?: first()

suspend fun <T> Flow<T?>.latest(isValid: (T?) -> Boolean = { it != null }): T? =
    filter { isValid(it) }.toList().lastOrNull()
