package com.example.todo.todos.data.di

import com.example.todo.todos.data.ITodoApi
import com.example.todo.todos.data.MockTodosApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TodoDataModule {
    @Provides
    @Singleton
    fun provideTodosApi(): ITodoApi = MockTodosApi()
}
