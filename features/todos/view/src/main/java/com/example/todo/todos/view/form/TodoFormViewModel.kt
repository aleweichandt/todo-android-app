package com.example.todo.todos.view.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.base.domain.UseCase.Result
import com.example.todo.base.view.navigation.fragment.IRoutedViewModel
import com.example.todo.base.view.navigation.fragment.RoutedViewModelDelegate
import com.example.todo.base.view.navigation.route.IRoute
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.domain.usecase.AddTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoFormViewModel(
    private val addTodoUseCase: AddTodoUseCase,
    private val routerDelegate: RoutedViewModelDelegate<IRoute> = RoutedViewModelDelegate()
) : ViewModel(), IRoutedViewModel by routerDelegate {
    @Inject
    constructor(
        addTodoUseCase: AddTodoUseCase
    ) : this(addTodoUseCase, RoutedViewModelDelegate())

    private val _readyToSubmit = MutableLiveData(true)
    val readyToSubmit: LiveData<Boolean>
        get() = _readyToSubmit

    val title = MutableLiveData("")

    val body = MutableLiveData("")

    fun onSubmit() {
        viewModelScope.launch {
            _readyToSubmit.value = false
            when (val response = addTodoUseCase(Todo(title.value!!, body.value!!))) {
                is Result.Success -> routerDelegate.popRoute()
                is Result.Failure -> Unit // TODO handle error
            }
            _readyToSubmit.value = true
        }
    }
}
