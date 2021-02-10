package com.example.todo.base.view.navigation.router

import androidx.fragment.app.Fragment
import com.example.todo.base.view.navigation.route.IRoute

interface IRouter {
    fun pop(instance: Fragment)
}
