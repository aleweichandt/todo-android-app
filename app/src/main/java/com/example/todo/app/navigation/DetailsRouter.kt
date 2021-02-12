package com.example.todo.app.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.app.R
import com.example.todo.todos.view.details.ITodoDetailsRouter
import com.example.todo.todos.view.details.TodoDetailsFragmentArgs
import com.example.todo.todos.view.details.TodoDetailsFragmentDirections
import com.example.todo.todos.view.details.dialog.IDeleteTodoRouter

class DetailsRouter : BaseRouter(), ITodoDetailsRouter, IDeleteTodoRouter {
    override val graphId = R.id.details_nav_graph

    companion object {
        private const val deleteTodoKey = "DeleteTodoDialog:ResultKey"
    }

    override fun args(instance: Fragment): ITodoDetailsRouter.TodoDetailsArgs =
        instance.navArgs<TodoDetailsFragmentArgs>().value.let {
            ITodoDetailsRouter.TodoDetailsArgs(it.uuid)
        }

    override fun pushForResult(
        instance: Fragment,
        route: ITodoDetailsRouter.TodoDetailsRoute,
        callback: (Boolean) -> Unit
    ) {
        with(instance.findNavController()) {
            observeResultForKey(instance, deleteTodoKey, callback)
            navigate(TodoDetailsFragmentDirections.actionToDeleteDialog())
        }
    }

    override fun popWithResult(instance: Fragment, result: Boolean) {
        with(instance.findNavController()) {
            postResult(deleteTodoKey, result)
            popBackStack()
        }
    }
}
