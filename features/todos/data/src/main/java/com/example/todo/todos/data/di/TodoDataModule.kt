package com.example.todo.todos.data.di

import com.example.todo.todos.data.ITodoApi
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
}
