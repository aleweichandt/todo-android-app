package com.example.todo.todos.view.details

import com.example.todo.base.view.navigation.router.IArgsRouter
import com.example.todo.base.view.navigation.router.IRouter
import java.io.Serializable

interface ITodoDetailsRouter : IRouter, IArgsRouter<ITodoDetailsRouter.TodoDetailsArgs> {
    data class TodoDetailsArgs(val uuid: Long) : Serializable
}
