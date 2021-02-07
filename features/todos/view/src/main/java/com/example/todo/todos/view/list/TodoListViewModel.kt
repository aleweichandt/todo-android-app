package com.example.todo.todos.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.todo.base.domain.UseCase.Result
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.domain.usecase.GetAllTodosUseCase
import com.example.todo.todos.domain.usecase.SetTodoCompletionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val setTodoCompletionUseCase: SetTodoCompletionUseCase
) : ViewModel(), SwipeRefreshLayout.OnRefreshListener, TodoListAdapter.TodoItemCallback {
    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>>
        get() = _todos

    private val _refreshing = MutableLiveData<Boolean>(false)
    val refreshing: LiveData<Boolean>
        get() = _refreshing

    fun onViewCreated() {
        refresh()
    }

    override fun onRefresh() {
        refresh(true)
    }

    override fun onItemSelected(item: Todo) {
        // TODO navigate to details
    }

    override fun onItemCompletionChanged(item: Todo, completion: Boolean) {
        viewModelScope.launch {
            when (val response = setTodoCompletionUseCase(
                SetTodoCompletionUseCase.Request(item, completion)
            )) {
                is Result.Success -> refresh()
                else -> Unit // TODO handle error
            }
        }
    }

    private fun refresh(force: Boolean = false) {
        viewModelScope.launch {
            _refreshing.value = true
            when (val response = getAllTodosUseCase(force)) {
                is Result.Success -> _todos.value = response.result
                else -> Unit //TODO handle error
            }
            _refreshing.value = false
        }
    }
}
