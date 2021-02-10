package com.example.todo.app.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.app.R
import com.example.todo.base.view.navigation.exception.IllegalRouteException
import com.example.todo.todos.view.details.ITodoDetailsRouter
import com.example.todo.todos.view.details.ITodoDetailsRouter.TodoDetailsArgs
import com.example.todo.todos.view.details.TodoDetailsFragmentArgs
import com.example.todo.todos.view.form.ITodoFormRouter
import com.example.todo.todos.view.list.ITodoListRouter
import com.example.todo.todos.view.list.ITodoListRouter.TodoListRoute
import com.example.todo.todos.view.list.TodoListFragmentDirections

class AppRouter : BaseRouter(), ITodoListRouter, ITodoDetailsRouter, ITodoFormRouter {
    override val graphId = R.id.app_nav_graph

    override fun push(instance: Fragment, route: TodoListRoute) {
        when (route) {
            is TodoListRoute.FormRoute -> TodoListFragmentDirections.actionToForm()
            is TodoListRoute.DetailsRoute -> TodoListFragmentDirections.actionToDetails(route.uuid)
            else -> null
        }?.let {
            instance.findNavController().navigate(it)
        } ?: throw IllegalRouteException(instance, route)
    }

    override fun args(instance: Fragment): TodoDetailsArgs =
        instance.navArgs<TodoDetailsFragmentArgs>().value.let {
            TodoDetailsArgs(it.uuid)
        }
}
