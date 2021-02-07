package com.example.todo.todos.view.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.base.domain.UseCase.Result
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.domain.usecase.AddTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoFormViewModel @Inject constructor(
    private val addTodoUseCase: AddTodoUseCase
) : ViewModel() {
    private val _readyToSubmit = MutableLiveData(true)
    val readyToSubmit: LiveData<Boolean>
        get() = _readyToSubmit

    val title = MutableLiveData("")

    val body = MutableLiveData("")

    fun onSubmit() {
        viewModelScope.launch {
            _readyToSubmit.value = false
            when (val response = addTodoUseCase(Todo(title.value!!, body.value!!))) {
                is Result.Success -> Unit// TODO navigate back
                is Result.Failure -> Unit // TODO handle error
            }
            _readyToSubmit.value = true
        }
    }
}
