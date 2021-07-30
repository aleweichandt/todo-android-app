package com.example.todo.todos.data.di

import com.example.todo.base.domain.IExceptionHandler
import com.example.todo.todos.data.ITodoApi
import com.example.todo.todos.data.ITodoCache
import com.example.todo.todos.data.local.TodosCache
import com.example.todo.todos.data.local.dao.TodoDao
import com.example.todo.todos.data.remote.MockTodosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoDataModule {
    @Provides
    @Singleton
    fun provideTodosApi(): ITodoApi = MockTodosApi()

    @Provides
    @Singleton
    fun provideTodosCache(dao: TodoDao, handler: IExceptionHandler): ITodoCache =
        TodosCache(dao, handler)
}
