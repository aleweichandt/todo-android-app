package com.example.todo.todos.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.todo.base.domain.UseCase.Result
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.domain.usecase.DeleteTodoUseCase
import com.example.todo.todos.domain.usecase.GetTodoByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoDetailsViewModel @Inject constructor(
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel(), SwipeRefreshLayout.OnRefreshListener {

    private var uuid: Long? = null

    private val _loadingTodo = MutableLiveData(true)
    val loadingTodo: LiveData<Boolean>
        get() = _loadingTodo

    private val _readyToDelete = MutableLiveData(false)
    val readyToDelete: LiveData<Boolean>
        get() = _readyToDelete

    private val _todo = MutableLiveData<Todo>()
    val todo: LiveData<Todo>
        get() = _todo

    fun onViewCreated(todoUuid: Long) {
        uuid = todoUuid
        loadTodo()
    }

    override fun onRefresh() {
        loadTodo()
    }

    fun onDelete() {
        viewModelScope.launch {
            when (val response = deleteTodoUseCase(_todo.value!!)) {
                is Result.Success -> Unit //TODO navigate back
                else -> Unit // TODO display error
            }
        }
    }

    private fun loadTodo() {
        viewModelScope.launch {
            uuid?.apply {
                _loadingTodo.value = true
                _readyToDelete.value = false
                when (val response = getTodoByIdUseCase(this)) {
                    is Result.Success -> {
                        _todo.value = response.result
                        _readyToDelete.value = true
                    }
                    else -> Unit // TODO display error
                }
                _loadingTodo.value = false
            }
        }
    }
}
