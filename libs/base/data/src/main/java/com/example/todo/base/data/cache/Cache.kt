package com.example.todo.base.data.cache

import com.example.todo.base.domain.IExceptionHandler

abstract class Cache(
    private val exceptionHandler: IExceptionHandler
) {
    protected suspend fun <T> runQuery(query: suspend () -> T) =
        query.runCatching { invoke() }
            .onFailure { exceptionHandler.handle(it) }
            .getOrNull()
}
