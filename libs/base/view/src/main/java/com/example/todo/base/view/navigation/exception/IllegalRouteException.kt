package com.example.todo.base.view.navigation.exception

import androidx.fragment.app.Fragment
import com.example.todo.base.view.navigation.route.IRoute

class IllegalRouteException(instance: Fragment, route: IRoute) : IllegalStateException(
    "unknown route: ${route.javaClass.simpleName} for instance: ${instance.javaClass.simpleName}"
)
