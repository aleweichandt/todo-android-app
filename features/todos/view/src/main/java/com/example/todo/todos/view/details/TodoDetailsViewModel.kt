package com.example.todo.todos.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.todo.base.domain.UseCase.Result
import com.example.todo.base.view.navigation.fragment.IRoutedViewModel
import com.example.todo.base.view.navigation.fragment.RoutedViewModelDelegate
import com.example.todo.base.view.navigation.route.IRoute
import com.example.todo.todos.domain.model.Todo
import com.example.todo.todos.domain.usecase.DeleteTodoUseCase
import com.example.todo.todos.domain.usecase.GetTodoByIdUseCase
import com.example.todo.todos.view.details.ITodoDetailsRouter.TodoDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoDetailsViewModel(
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val routerDelegate: RoutedViewModelDelegate<IRoute> = RoutedViewModelDelegate()
) : ViewModel(), SwipeRefreshLayout.OnRefreshListener, IRoutedViewModel by routerDelegate {
    @Inject
    constructor(
        getTodoByIdUseCase: GetTodoByIdUseCase,
        deleteTodoUseCase: DeleteTodoUseCase
    ) : this(getTodoByIdUseCase, deleteTodoUseCase, RoutedViewModelDelegate())

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
        routerDelegate.pushRoute(TodoDetailsRoute.DeleteTodoDialog)
    }

    fun onDeleteResult(result: Boolean) {
        if (result) {
            viewModelScope.launch {
                when (val response = deleteTodoUseCase(_todo.value!!)) {
                    is Result.Success -> routerDelegate.popRoute()
                    else -> Unit // TODO display error
                }
            }
        }
    }

    private fun loadTodo() {
        viewModelScope.launch {
            uuid?.apply {
                getTodoByIdUseCase(this)
                    .onStart {
                        _loadingTodo.value = true
                        _readyToDelete.value = false
                    }
                    .onCompletion { _loadingTodo.value = false }
                    .collect { response ->
                        when (response) {
                            is Result.Success -> {
                                _todo.value = response.result
                                _readyToDelete.value = true
                            }
                            else -> Unit // TODO display error
                        }
                    }
            }
        }
    }
}
