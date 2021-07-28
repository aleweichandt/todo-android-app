package com.example.todo.app.di

import com.example.todo.base.domain.IExceptionHandler
import com.example.todo.todos.data.di.TodoDataModule
import com.example.todo.todos.domain.di.TodoUseCaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [TodoDataModule::class, TodoUseCaseModule::class])
@InstallIn(SingletonComponent::class)
object AggregatorModule {
    @Provides
    @Singleton
    fun provideExceptionHandle() = object : IExceptionHandler {
        override fun handle(t: Throwable) {}
    }
}
