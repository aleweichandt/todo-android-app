package com.example.todo.todos.data.di

import com.example.todo.todos.data.ITodoApi
import com.example.todo.todos.data.MockTodosApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TodoDataModule {
    @Provides
    @Singleton
    fun provideTodosApi(): ITodoApi = MockTodosApi()
}
