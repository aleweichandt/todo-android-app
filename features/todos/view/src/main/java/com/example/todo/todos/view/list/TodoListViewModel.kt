package com.example.todo.todos.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.todo.base.domain.UseCase.Result
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.domain.usecase.GetAllTodosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase
) : ViewModel() , SwipeRefreshLayout.OnRefreshListener {
    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>>
        get() = _todos

    fun onViewCreated() {
        refresh()
    }

    override fun onRefresh() {
        refresh(true)
    }

    private fun refresh(force: Boolean = false) {
        viewModelScope.launch {
            when (val response = getAllTodosUseCase(force)) {
                is Result.Success -> _todos.value = response.result
                else -> { //TODO handle error
                }
            }
        }
    }
}
