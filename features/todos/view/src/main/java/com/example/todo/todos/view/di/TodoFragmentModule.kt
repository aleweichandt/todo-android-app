package com.example.todo.todos.view.di

import com.example.todo.todos.domain.usecase.*
import com.example.todo.todos.view.details.TodoDetailsViewModel
import com.example.todo.todos.view.form.TodoFormViewModel
import com.example.todo.todos.view.list.TodoListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TodoFragmentModule {
    @Provides
    @ViewModelScoped
    fun bindsTodoListViewModel(
        getAllTodosUseCase: GetAllTodosUseCase,
        setTodoCompletionUseCase: SetTodoCompletionUseCase
    ) = TodoListViewModel(getAllTodosUseCase, setTodoCompletionUseCase)

    @Provides
    @ViewModelScoped
    fun bindTodoFormViewModel(
        addTodoUseCase: AddTodoUseCase
    ) = TodoFormViewModel(addTodoUseCase)

    @Provides
    @ViewModelScoped
    fun bind(
        getTodoByIdUseCase: GetTodoByIdUseCase,
        deleteTodoUseCase: DeleteTodoUseCase
    ) = TodoDetailsViewModel(getTodoByIdUseCase, deleteTodoUseCase)
}
