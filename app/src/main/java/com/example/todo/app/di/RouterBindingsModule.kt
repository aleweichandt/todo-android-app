package com.example.todo.app.di

import com.example.todo.app.navigation.AppRouter
import com.example.todo.todos.view.details.ITodoDetailsRouter
import com.example.todo.todos.view.form.ITodoFormRouter
import com.example.todo.todos.view.list.ITodoListRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RouterBindingsModule {
    @Binds
    abstract fun bindTodoListRouter(appRouter: AppRouter): ITodoListRouter

    @Binds
    abstract fun bindTodoFormRouter(appRouter: AppRouter): ITodoFormRouter

    @Binds
    abstract fun bindTodoDetailsRouter(appRouter: AppRouter): ITodoDetailsRouter

}
