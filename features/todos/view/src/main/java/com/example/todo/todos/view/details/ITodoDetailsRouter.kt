package com.example.todo.todos.view.details

import androidx.fragment.app.Fragment
import com.example.todo.base.view.navigation.route.IRoute
import com.example.todo.base.view.navigation.router.IArgsRouter
import com.example.todo.base.view.navigation.router.IRouter
import java.io.Serializable

interface ITodoDetailsRouter : IRouter, IArgsRouter<ITodoDetailsRouter.TodoDetailsArgs> {
    sealed class TodoDetailsRoute : IRoute {
        object DeleteTodoDialog : TodoDetailsRoute()
    }

    data class TodoDetailsArgs(val uuid: Long) : Serializable

    fun pushForResult(instance: Fragment, route: TodoDetailsRoute, callback: (Boolean) -> Unit)
}
