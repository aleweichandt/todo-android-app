package com.example.todo.todos.domain.di

import com.example.todo.base.domain.IExceptionHandler
import com.example.todo.todos.domain.repository.TodoRepository
import com.example.todo.todos.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TodoUseCaseModule {
    @Provides
    @Singleton
    fun provideTodosRepository() = TodoRepository()

    @Provides
    fun provideGetAllTodosUseCase(repository: TodoRepository, handler: IExceptionHandler) =
        GetAllTodosUseCase(repository, handler)

    @Provides
    fun provideGetTodoByIdUseCase(repository: TodoRepository, handler: IExceptionHandler) =
        GetTodoByIdUseCase(repository, handler)

    @Provides
    fun provideAddTodoUseCase(repository: TodoRepository, handler: IExceptionHandler) =
        AddTodoUseCase(repository, handler)

    @Provides
    fun provideDeleteTodoUseCase(repository: TodoRepository, handler: IExceptionHandler) =
        DeleteTodoUseCase(repository, handler)

    @Provides
    fun provideSetTodoCompletionUseCase(repository: TodoRepository, handler: IExceptionHandler) =
        SetTodoCompletionUseCase(repository, handler)
}
