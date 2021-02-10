package com.example.todo.todos.view.list

import androidx.fragment.app.Fragment
import com.example.todo.base.view.navigation.route.IRoute
import com.example.todo.base.view.navigation.router.IRouter

interface ITodoListRouter : IRouter {
    sealed class TodoListRoute : IRoute {
        data class DetailsRoute(val uuid: Long) : TodoListRoute()
        object FormRoute : TodoListRoute()
    }

    fun push(instance: Fragment, route: TodoListRoute)
}
