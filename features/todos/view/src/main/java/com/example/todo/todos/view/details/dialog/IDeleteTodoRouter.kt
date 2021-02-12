package com.example.todo.todos.view.details.dialog

import androidx.fragment.app.Fragment

interface IDeleteTodoRouter {
    fun popWithResult(instance: Fragment, result: Boolean)
}
