package com.example.todo.todos.view.di

import com.example.todo.todos.domain.usecase.AddTodoUseCase
import com.example.todo.todos.domain.usecase.GetAllTodosUseCase
import com.example.todo.todos.domain.usecase.SetTodoCompletionUseCase
import com.example.todo.todos.view.form.TodoFormViewModel
import com.example.todo.todos.view.list.TodoListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(FragmentComponent::class)
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
}
